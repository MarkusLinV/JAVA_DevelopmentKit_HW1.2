package org.example;

import org.example.view.ClientChatWindow;
import org.example.view.ServerWindow;

public class Main {
    public static void main(String[] args) {

     ServerWindow serverWindow = new ServerWindow();

     ClientChatWindow clientChatWindow1 = new ClientChatWindow(serverWindow);
     ClientChatWindow clientChatWindow2 = new ClientChatWindow(serverWindow);
    }
}