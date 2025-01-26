package org.example.service;

import org.example.model.Client;
import org.example.model.Message;
import org.example.model.Server;

import java.io.IOException;
import java.util.ArrayList;

public class ServerService {

    private DBService dbService = new DBService();


    public void serverOn(Server server){
        server.setServerWorking(true);
    }

    public void serverOff(Server server){
        server.setServerWorking(false);
    }

    public void messageToServer(Server server, Message message) throws IOException {
        if (server.getServerWorking()){
            dbService.addMessageToDB(message);
            server.setMessages(dbService.getMessageFromDB());
        }
    }

    public void messageToServer(Server server) throws IOException {
        server.setMessages(dbService.getMessageFromDB());
    }

    public boolean ClientConnectToServer(Server server, Client client, String port){
        if (port.equals(server.getPort()) && server.getServerWorking()){
            ArrayList<Client> newClients = server.getClients();
            newClients.add(client);
            server.setClients(newClients);
            client.setServer(server);
            client.setConnectToServer(true);
            return true;
        }
        return false;
    }
}
