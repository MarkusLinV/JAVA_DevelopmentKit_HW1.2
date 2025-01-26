package org.example.controller;

import org.example.model.Message;
import org.example.model.MessageSource;
import org.example.model.Server;
import org.example.model.impl.Observer;
import org.example.model.impl.Subject;
import org.example.service.ServerService;
import org.example.view.ClientChatWindow;
import org.example.view.ServerWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerController implements Subject {

    private Server server;
    private ServerService serverService = new ServerService();
    private ServerWindow serverWindow;
    private ArrayList<ClientController> clientControllers = new ArrayList<>();

    public ServerController(Server server, ServerWindow serverWindow) {
        this.server = server;
        this.serverWindow = serverWindow;
    }


    public void serverStart(){
        if(!server.getServerWorking()){
            serverService.serverOn(server);
            MessegesToTextArea(new Message("Сервер запущен", MessageSource.SERVER));
        }
    }

    public void serverStop(){
        if(server.getServerWorking()){
            serverService.serverOff(server);
            MessegesToTextArea(new Message("Сервер остановлен", MessageSource.SERVER));
        }
    }

    public void MessegesToTextArea(Message message, Server server){
        try {
            serverService.messageToServer(server, message);
            serverWindow.textToTextArea(server.getMessages());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void MessegesToTextArea(Message message){
        try {
            serverService.messageToServer(server, message);
            serverWindow.textToTextArea(server.getMessages());
            notifyObservers(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void MessegesToTextArea(){
        try {
            serverService.messageToServer(server);
            serverWindow.textToTextArea(server.getMessages());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }

    public void update(){
        MessegesToTextArea();
    }

    public void setClientControllers(ClientChatWindow clientChatWindow){
        clientControllers.add(clientChatWindow.getClientController());
    }

    @Override
    public void registerObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyObservers(Message message) {
        for (ClientController controller : clientControllers){
            controller.updateObserver(message);
        }
    }
}
