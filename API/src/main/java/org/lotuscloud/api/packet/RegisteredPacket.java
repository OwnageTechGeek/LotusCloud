package org.lotuscloud.api.packet;

import org.lotuscloud.api.network.Packet;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class RegisteredPacket extends Packet {

    public boolean success;
    public String error;

    public RegisteredPacket(boolean success, String error) {
        super("registered");
        this.success = success;
        this.error = error;
    }
}
