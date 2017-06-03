package de.iotus.cloud.api.network;

import de.iotus.cloud.api.packet.ErrorPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (c) 2017 Lennart Heinrich
 * www.lennarth.com
 */
public class PacketServer {

    private boolean closed = true;
    private int port;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private HashMap<String, Handler> handlers = new HashMap<>();
    private ArrayList<String> acceptIP = new ArrayList<>();

    public PacketServer(int port) {
        this.port = port;
    }

    public void bind() {
        close();
        try {
            serverSocket = new ServerSocket(port);
            closed = false;
            new Thread(() -> {
                while (!closed) {
                    try {
                        Socket socket = serverSocket.accept();
                        executor.submit(() -> {
                            try {
                                String client = socket.getInetAddress().getHostAddress();
                                if (!acceptIP.contains(client)) {
                                    socket.close();
                                    System.out.println("unallowed request from " + client + " closed");
                                    return;
                                }
                                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                                Packet packet = (Packet) in.readObject();
                                if (handlers.containsKey(packet.getPacketName()))
                                    out.writeObject(handlers.get(packet.getPacketName()).handle(packet));
                                else
                                    out.writeObject(new ErrorPacket("handler not found"));
                                out.flush();
                                in.close();
                                out.close();
                                socket.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            serverSocket = null;
            closed = true;
        }
    }

    public void registerHandler(String packetName, Handler handler) {
        unregisterHandler(packetName);
        handlers.put(packetName, handler);
    }

    public void unregisterHandler(String packetName) {
        if (handlers.containsKey(packetName))
            handlers.remove(packetName);
    }

    public void acceptIP(String ip) {
        if (!acceptIP.contains(ip))
            acceptIP.add(ip);
    }

    public void removeIP(String ip) {
        if (acceptIP.contains(ip))
            acceptIP.remove(ip);
    }
}