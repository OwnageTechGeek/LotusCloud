package org.lotuscloud.api.packet;

import org.lotuscloud.api.network.Packet;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class StartServerPacket extends Packet {

    private String template;
    private String jarName;

    public StartServerPacket(String template, String jarName) {
        super("startserver");
        this.template = template;
        this.jarName = jarName;
    }

    public String getTemplate() {
        return template;
    }

    public String getJarName() {
        return jarName;
    }
}