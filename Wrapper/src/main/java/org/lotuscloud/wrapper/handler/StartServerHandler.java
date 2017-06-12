package org.lotuscloud.wrapper.handler;

import org.lotuscloud.api.logging.LogLevel;
import org.lotuscloud.api.network.Handler;
import org.lotuscloud.api.network.Packet;
import org.lotuscloud.api.packet.ErrorPacket;
import org.lotuscloud.api.packet.OkPacket;
import org.lotuscloud.api.packet.StartServerPacket;
import org.lotuscloud.wrapper.main.Wrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class StartServerHandler extends Handler {

    @Override
    public Packet handle(Packet packet, String client) {
        StartServerPacket startServerPacket = (StartServerPacket) packet;
        File templateDir = new File("templates/" + startServerPacket.getTemplate());

        if (!templateDir.exists())
            return new ErrorPacket("Template nicht gefunden");

        File tempDir = new File("temp");
        if (!tempDir.exists())
            tempDir.mkdir();

        File dir;
        for (int i = 1; true; i++) {
            if ((dir = new File("temp/" + startServerPacket.getTemplate() + i)).exists())
                continue;
            Wrapper.instance.logger.log("Kopiere " + templateDir.getPath() + " nach " + dir.getPath(), LogLevel.DEBUG);
            break;
        }

        try {
            Files.copy(templateDir.toPath(), dir.toPath());
            for (File file : templateDir.listFiles())
                Files.copy(file.toPath(), Paths.get(dir.getPath() + "/" + file.getName()));
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ErrorPacket(ex.getMessage());
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(dir);
        processBuilder.command("java", "-jar", startServerPacket.getJarName());
        Process process;
        try {
            process = processBuilder.start();
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ErrorPacket(ex.getMessage());
        }
        new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            while (process.isAlive())
                try {
                    String line = reader.readLine();
                    if (line != null)
                        System.out.println(reader.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }).start();
        return OkPacket.init;
    }
}
