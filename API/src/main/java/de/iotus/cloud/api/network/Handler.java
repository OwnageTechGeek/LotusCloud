package de.iotus.cloud.api.network;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public abstract class Handler {
    public abstract Packet handle(Packet packet);
}