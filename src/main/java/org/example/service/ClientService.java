package org.example.service;

import org.example.controller.ClientController;
import org.example.model.Client;
import org.example.model.Message;
import org.example.model.Server;
import org.example.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class ClientService {

    ServerService serverService = new ServerService();

    public void FocusDefoltText(JTextField textField, String defoltText){
        textField.setText(defoltText);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(defoltText)){
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().isEmpty()){
                    textField.setText(defoltText);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    public boolean isConnectToServer(Client client){
        return client.isConnectToServer();
    }

    public void userToClient(Client client, User user){
        client.setUser(user);
    }

    public void messageToServerFromClient(Client client, Message message, Server server) throws IOException {
        if(isConnectToServer(client)){
            serverService.messageToServer(server, message);
        } else {
            throw new IOException("Клиент не соединён с сервером");
        }
    }


}
