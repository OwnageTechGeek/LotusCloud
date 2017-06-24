package org.lotuscloud.master.webhandler;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.lotuscloud.api.crypt.Crypter;
import org.lotuscloud.api.database.DBTools;
import org.lotuscloud.master.main.Master;
import org.lotuscloud.master.web.WebHandler;

import java.util.HashMap;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class MainWebHandler extends WebHandler {

    @Override
    public String process(HashMap<String, String> request, String ip) {
        if (request.containsKey("name") && request.containsKey("password")) {
            MongoCollection collection = Master.instance.databaseManager.getDatabase().getCollection("cp_user");
            DBObject ref = new BasicDBObject();
            ref.put("name", DBTools.noCase(request.get("name")));
            ref.put("password", DBTools.noCase(Crypter.hash("SHA-512", request.get("password"))));
            FindIterable iterable = collection.find((Bson) ref);
            if (iterable.first() == null)
                return "<h3>Benutzername oder Passwort nicht gefunden</h3>";
            else {
                Master.instance.webServer.user.put(ip, ((Document) iterable.first()).getString("name"));
                return "<meta http-equiv=\"refresh\" content=\"0; url=dashboard\" />";
            }
        }
        return "<form method='get'>" +
                "<div class='login'>" +
                "<input placeholder='Benutzername' name='name' required>" +
                "<input placeholder='Passwort' name='password' required>" +
                "<button type='submit'>Login</button>" +
                "</div>" +
                "</form>";
    }
}