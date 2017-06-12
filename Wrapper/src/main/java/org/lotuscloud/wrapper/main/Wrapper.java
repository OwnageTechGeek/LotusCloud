package org.lotuscloud.wrapper.main;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.lotuscloud.api.console.ConsoleReader;
import org.lotuscloud.api.logging.LogLevel;
import org.lotuscloud.api.logging.Logger;
import org.lotuscloud.api.network.PacketClient;
import org.lotuscloud.api.network.PacketServer;
import org.lotuscloud.api.packet.RegisterPacket;
import org.lotuscloud.api.packet.RegisteredPacket;
import org.lotuscloud.wrapper.handler.StartServerHandler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class Wrapper {

    public static Wrapper instance;
    public Logger logger;
    public JsonObject config;
    public ConsoleReader console;
    public PacketServer server;
    public String masterHost;
    public int masterPort;

    public Wrapper() {
        instance = this;
        try {
            File file = new File("config.json");
            if (!file.exists())
                Files.write(Paths.get("config.json"), "{}".getBytes());
            config = Json.parse(new String(Files.readAllBytes(Paths.get("config.json")), StandardCharsets.UTF_8)).asObject().get("wrapper").asObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logger = new Logger(LogLevel.DEBUG);
        console = new ConsoleReader();
        server = new PacketServer(config.getInt("port", 1250));
        server.bind();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
        server.registerHandler("startserver", new StartServerHandler());
        masterHost = config.getString("master-host", "127.0.0.1");
        masterPort = config.getInt("master-port", 1241);
        RegisteredPacket registeredPacket = (RegisteredPacket) PacketClient.request(masterHost, masterPort, new RegisterPacket(server.getPort()));
        if (registeredPacket.success)
            logger.log("Wrapper registriert", LogLevel.INFO);
        else
            logger.log("Wrapper konnte nicht registriert werden: " + registeredPacket.error);
        server.acceptIP(masterHost);
        logger.log("Wrapper erfolgreich gestartet", LogLevel.INFO);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("    __          __             ________                __\n   / /   ____  / /___  _______/ ____/ /___  __  ______/ /\n  / /   / __ \\/ __/ / / / ___/ /   / / __ \\/ / / / __  / \n / /___/ /_/ / /_/ /_/ (__  ) /___/ / /_/ / /_/ / /_/ /  \n/_____/\\____/\\__/\\__,_/____/\\____/_/\\____/\\__,_/\\__,_/");
        System.out.println("Wrapper - Copyright (c) 2017 Lennart Heinrich");
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
                System.out.println("Stoppe Wrapper...");
                System.exit(0);
            }
        }
        System.out.println("Starte Wrapper...");
        new Wrapper();
    }
}