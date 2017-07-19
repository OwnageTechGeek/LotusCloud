package org.lotuscloud.master.webhandler;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
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
        MongoCollection col = Master.instance.databaseManager.getDatabase().getCollection("groups");
        FindIterable<Document> find = col.find();
        StringBuilder builder = new StringBuilder();
        for (Document doc : find) {
            String name = doc.getString("name");
            if (name != null && !name.replace(" ", "").equalsIgnoreCase(""))
                builder.append("<div class='group'><p>" + doc.getString("name") + "</p><a href='?edit=" + doc.getString("name") + "'><button>" + Master.instance.language.get("edit") + "</button></a></div>");
        }
        return "<div class='box'><h2>" + Master.instance.language.get("groups") + "</h2>" + builder.toString() + "</div>";
    }
}