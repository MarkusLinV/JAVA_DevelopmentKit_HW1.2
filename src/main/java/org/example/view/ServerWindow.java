package org.example.view;

import org.example.controller.ClientController;
import org.example.controller.ServerController;
import org.example.model.Message;
import org.example.model.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ServerWindow extends JFrame {

    private Server server = new Server();
    private ServerController serverController = new ServerController(server, this);
    private JTextArea textArea;

    public void setClientControllers(ClientChatWindow clientChatWindow){
        serverController.setClientControllers(clientChatWindow);
    }

    public ServerWindow(){

        TextZone();
        BtnZone();
        BaseOpoins();

    }

    private void BaseOpoins(){

        int WINDOWS_HEIGHT = 400; // Определение постоянных значений для высоты окна
        int WINDOWS_WIDTH = 500; // Определение постоянных значений для ширины окна
        int WINDOWS_POSX = 800; // Определение постоянных значений для координаты X окна
        int WINDOWS_POSY = 300; // Определение постоянных значений для координаты Y окна

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOWS_POSX, WINDOWS_POSY);
        setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        setTitle("Сервер");
        setResizable(false);

        setVisible(true);
    }

    private void TextZone(){

        textArea = new JTextArea();
        textArea.setEnabled(false);
        textArea.setBackground(Color.black);
        serverController.MessegesToTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void BtnZone(){

        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        JButton btnStart = new JButton("СТАРТ");
        JButton btnStop = new JButton("СТОП");

        add(btnPanel, BorderLayout.SOUTH);
        btnPanel.add(btnStart);
        btnPanel.add(btnStop);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.serverStart();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.serverStop();
            }
        });

    }

    public JTextArea getTextArea(ArrayList<Message> messages) {
        return textArea;
    }

    public void textToTextArea(ArrayList<Message> messages){
        textArea.setText("");
        for (Message message : messages){
            textArea.append(message.toString());
        }

    }

    public Server getServer() {
        return server;
    }

    public ServerController getServerController() {
        return serverController;
    }

    public void notifyObservers(Message message) {
        if (server.getServerWorking()){
            serverController.notifyObservers(message);
        }

    }
}
