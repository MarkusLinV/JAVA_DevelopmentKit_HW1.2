package org.example.model;

import java.util.ArrayList;

public class Server {
    private final String port = "8189";
    private boolean serverStatus;
    private ArrayList<Message> Messages;

    private ArrayList<Client> clients;
    private ArrayList<User> users;

    public Server(){
        clients = new ArrayList<>();
        this.serverStatus = false;
    }

    public String getPort() {
        return port;
    }

    public boolean getServerWorking() {
        return serverStatus;
    }

    public void setServerWorking(boolean serverWorking) {
        serverStatus = serverWorking;
    }

    public ArrayList<Message> getMessages() {
        return Messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        Messages = messages;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}