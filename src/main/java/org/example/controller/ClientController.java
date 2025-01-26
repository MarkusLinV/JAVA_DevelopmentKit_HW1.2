package org.example.controller;

import org.example.model.*;
import org.example.model.impl.Observer;
import org.example.service.*;
import org.example.view.ClientChatWindow;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class ClientController implements Observer {

    private ClientChatWindow clientChatWindow;
    private Client client;
    private ClientService clientService = new ClientService();
    private DBService dbService = new DBService();
    private ChatListPanelServise chatListPanelServise;
    private UserService userService = new UserService();
    private ServerService serverService = new ServerService();
    private ServerController serverController;

    public ClientController(Client client, ClientChatWindow clientChatWindow) {
        this.client = client;
        this.clientChatWindow = clientChatWindow;
    }

    public void FocusDefoltText(JTextField textField, String defoltText) {
        clientService.FocusDefoltText(textField, defoltText);
    }

    public void ClientConnectAndUserLogin(String name, String password, String port) {
        if (!clientService.isConnectToServer(client)) {
            if (serverService.ClientConnectToServer(clientChatWindow.getServerWindow().getServer(), client, port)){
                //метод отправки сообщений в чат, что всё подключилось
                messageToTextArea(new Message("Подключение к серверу удалось.", MessageSource.SERVICE));
                UserLogin(name, password);
            } else {
                //метод отправки сообщений в чат, что всё не подключилось, проверь порт или сервер не запущен
                messageToTextArea(new Message("Подключение к серверу не удалось, проверьте корректно ли указан порт или сервер выключен", MessageSource.SERVICE));
            }
        } else{
            UserLogin(name, password);
        }
    }

    private void UserLogin(String name, String password){
        User user = userService.cteateUser(name, password);
        try {
            if (dbService.userContains(user)) {
                if (userService.paswordEquals(dbService.userFromDBbyName(name), password)){
                    clientService.userToClient(client, user);
                    //метод отправки сообщений на сервер о подключении юсера
                    messageToServerFromClient(new Message("Пользователь " + name + " успешно подключился", MessageSource.SERVICE));
                    messageToTextArea(new Message("Пользователь " + name + " успешно подключился", MessageSource.SERVICE));
                } else {
                    messageToTextArea(new Message("Подключение пользователя не удалось, проверьте пароль.", MessageSource.SERVICE));
                }
            } else {
                dbService.addUserToDB(user);
                clientService.userToClient(client, user);
                messageToTextArea(new Message("Пользователь " + name + " успешно подключился", MessageSource.SERVICE));
                messageToServerFromClient(new Message("Пользователь " + name + " успешно подключился", MessageSource.SERVICE));
                //метод отправки сообщений в чат и сервер о подключении нового юсера
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        update();
    }

    public void update() {
        try {
            chatListPanelServise = new ChatListPanelServise(clientChatWindow.getChatListPanel().getUserModel());
            chatListPanelServise.updateUser(dbService.getUserFromDB());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void messageToTextArea(Message message){
        JTextArea textArea = clientChatWindow.getTextArea();
        if (message.getSource() == MessageSource.USER || message.getSource() == MessageSource.SERVICE){
            textArea.append(message.toString());
        }
    }

    public void messageToServerFromClient(Message message) {
        try {
            clientService.messageToServerFromClient(client, message, clientChatWindow.getServerWindow().getServer());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serverController = clientChatWindow.getServerWindow().getServerController();
        serverController.update();
    }

    @Override
    public void updateObserver(Message message) {
        messageToTextArea(message);
    }
}