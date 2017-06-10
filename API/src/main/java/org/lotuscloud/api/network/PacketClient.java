package org.lotuscloud.api.network;

import org.lotuscloud.api.packet.ErrorPacket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class PacketClient {

    public static Packet request(String host, int port, Packet packet) {
        try {
            Socket socket = new Socket(host, port);
            if (socket.isClosed())
                return new ErrorPacket("socket closed");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(packet);
            out.flush();
            Packet response = (Packet) in.readObject();
            in.close();
            out.close();
            socket.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorPacket(ex.getMessage());
        }
    }
}