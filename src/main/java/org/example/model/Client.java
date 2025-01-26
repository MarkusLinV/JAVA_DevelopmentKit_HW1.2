package org.example.model;

public class Client {
        private boolean isConnectToServer;
        private Server server;
        private User user;

    public Client() {
        this.isConnectToServer = false;
    }

    public boolean isConnectToServer() {
        return isConnectToServer;
    }

    public User getUser() {
        return user;
    }

    public Server getServer() {
        return server;
    }

    public void setConnectToServer(boolean connectToServer) {
        isConnectToServer = connectToServer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
