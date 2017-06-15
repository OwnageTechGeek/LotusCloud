package org.lotuscloud.api.packet;

import org.lotuscloud.api.network.Packet;

import java.security.PublicKey;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class RegisterPacket extends Packet {

    public int port;
    public PublicKey key;

    public RegisterPacket(int port, PublicKey key) {
        super("register");
        this.port = port;
        this.key = key;
    }
}