package org.lotuscloud.api.packet;

import org.lotuscloud.api.network.Packet;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class RegisterPacket extends Packet {

    public int port;

    public RegisterPacket(int port) {
        super("register");
        this.port = port;
    }
}