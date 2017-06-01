package de.iotus.cloud.master.command;

import de.iotus.cloud.api.console.ConsoleCommand;
import de.iotus.cloud.master.main.Master;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class InfoCommand extends ConsoleCommand {
    @Override
    public void process(String command, String[] args) {
        Master.instance.logger.log("Command: " + command + "\nArgumente: " + args.length);
    }
}
