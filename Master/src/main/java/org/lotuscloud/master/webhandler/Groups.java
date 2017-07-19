package org.lotuscloud.master.webhandler;

import org.lotuscloud.master.main.Master;
import org.lotuscloud.master.web.WebHandler;

import java.util.HashMap;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class Groups extends WebHandler {

    @Override
    public String process(HashMap<String, String> request, String ip) {
        String user = Master.instance.webServer.user.get(ip);
        return "<div class='box'>" +
                "<h2>" + Master.instance.language.get("groups") + "</h2>" +
                "<div class='group'>" +
                "<p>Test</p>" +
                "<button>" + Master.instance.language.get("edit") + "</button>" +
                "</div>" +
                "</div>";
    }
}
