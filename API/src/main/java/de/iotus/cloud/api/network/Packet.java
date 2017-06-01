package de.iotus.cloud.api.network;

import java.io.Serializable;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class Packet implements Serializable {

    private String packetName;

    Packet(String packetName) {
        this.packetName = packetName;
    }

    public String getPacketName() {
        return packetName;
    }
}