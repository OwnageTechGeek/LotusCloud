package de.iotus.cloud.api.console;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public abstract class ConsoleCommand {
    public abstract void process(String command, String[] args);
}