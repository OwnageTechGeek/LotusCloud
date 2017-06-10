package org.lotuscloud.master.main;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.lotuscloud.api.console.ConsoleReader;
import org.lotuscloud.api.database.DatabaseManager;
import org.lotuscloud.api.logging.LogLevel;
import org.lotuscloud.api.logging.Logger;
import org.lotuscloud.api.network.Handler;
import org.lotuscloud.api.network.Packet;
import org.lotuscloud.api.network.PacketServer;
import org.lotuscloud.api.packet.RegisterPacket;
import org.lotuscloud.api.packet.RegisteredPacket;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class Master {

    public static Master instance;
    public Logger logger;
    public DatabaseManager databaseManager;
    public JsonObject config;
    public ConsoleReader console;
    public PacketServer server;
    public HashMap<String, Integer> wrapper = new HashMap<>();

    public Master() {
        instance = this;
        try {
            File file = new File("config.json");
            if (!file.exists())
                Files.write(Paths.get("config.json"), "{}".getBytes());
            config = Json.parse(new String(Files.readAllBytes(Paths.get("config.json")), StandardCharsets.UTF_8)).asObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logger = new Logger(LogLevel.DEBUG);
        JsonObject mongoConfig = config.get("mongodb").asObject();
        logger.log("Verbinde zur Datenbank", LogLevel.INFO);
        databaseManager = new DatabaseManager(mongoConfig.getString("host", "127.0.0.1"), mongoConfig.getInt("port", 27017), mongoConfig.getString("database", ""), mongoConfig.getString("user", ""), mongoConfig.getString("password", ""));
        logger.log("Mit der Datenbank verbunden", LogLevel.INFO);
        console = new ConsoleReader();
        server = new PacketServer(1241);
        server.bind();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
        registerHandler();
        logger.log("Master erfolgreich gestartet");
    }

    public static void main(String[] args) throws IOException {
        System.out.println("    ____      __             ________                __\n   /  _/___  / /___  _______/ ____/ /___  __  ______/ /\n   / // __ \\/ __/ / / / ___/ /   / / __ \\/ / / / __  / \n _/ // /_/ / /_/ /_/ (__  ) /___/ / /_/ / /_/ / /_/ /  \n/___/\\____/\\__/\\__,_/____/\\____/_/\\____/\\__,_/\\__,_/");
        System.out.println("Master - Copyright (c) 2017 Lennart Heinrich");
        System.out.println("Lizenziert unter der Apache Lizenz, Version 2");
        if (!Files.exists(Paths.get("license-terms.txt")) || !new String(Files.readAllBytes(Paths.get("license-terms.txt"))).replace(" ", "").equalsIgnoreCase("accepted=true")) {
            System.out.print("Akzeptierst du die Bedingungen? [j/N]: ");
            if (new Scanner(System.in).nextLine().equalsIgnoreCase("j")) {
                try {
                    Files.write(Paths.get("license-terms.txt"), "accepted=true".getBytes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Stoppe Master...");
                System.exit(0);
            }
        }
        System.out.println("Starte Master...");
        new Master();
    }

    private void registerHandler() {
        server.registerHandler("register", new Handler() {
            @Override
            public Packet handle(Packet packet, String client) {
                RegisterPacket registerPacket = (RegisterPacket) packet;
                server.acceptIP(client);
                wrapper.put(client, registerPacket.port);
                logger.log("Neuer Wrapper registriert: " + client + " an Port " + registerPacket.port, LogLevel.WARNING);
                return new RegisteredPacket(true, null);
            }
        });
    }
}