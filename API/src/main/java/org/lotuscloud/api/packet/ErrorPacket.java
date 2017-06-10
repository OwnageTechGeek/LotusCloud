package org.lotuscloud.api.packet;

import org.lotuscloud.api.network.Packet;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class ErrorPacket extends Packet {

    private String error;

    public ErrorPacket(String error) {
        super("error");
        this.error = error;
    }

    public String getError() {
        return error;
    }
}