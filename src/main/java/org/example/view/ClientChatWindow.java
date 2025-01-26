package org.example.view;

import org.example.controller.ClientController;
import org.example.model.Client;
import org.example.model.Message;
import org.example.model.impl.ChatListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class ClientChatWindow extends JFrame{

    private ServerWindow serverWindow;
    private ChatListPanel chatListPanel;
    private JTextArea textArea;

    private Client client = new Client();
    private ClientController clientController = new ClientController(client, this);
    private Consumer<Boolean> updateInputZoneStatus;




    public ClientChatWindow(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        serverWindow.setClientControllers(this);

        ChatZone();
        DataZone();
        InputZone();
        ListZone();

        BaseOpoins();
    }

    private void BaseOpoins(){

        int WINDOWS_HEIGHT = 300;// Определение постоянных значений для высоты окна
        int WINDOWS_WIDTH = 400;// Определение постоянных значений для ширины окна
        int WINDOWS_POSX = 800;// Определение постоянных значений для координаты X окна
        int WINDOWS_POSY = 300;// Определение постоянных значений для координаты Y окна

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocation(WINDOWS_POSX, WINDOWS_POSY);
        setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);

        setTitle("ГУИРЧАТ_пилим вместе");

        setVisible(true);
    }
    private void DataZone(){

        JPanel dataPanel = new JPanel(new GridLayout(2, 3));
        JTextField dataIp = new JTextField();
        JTextField dataPort = new JTextField();
        JTextField dataLogin = new JTextField();
        JTextField dataPassword = new JTextField();
        JButton dataBtnLogin = new JButton("Login");

        dataPanel.setBackground(Color.GRAY);

        add(dataPanel, BorderLayout.NORTH);

        dataPanel.add(dataIp);
        dataIp.setText("127.0.0.1");
        dataPanel.add(dataPort);
        dataPort.setText("8189");
        dataPanel.add(dataLogin);
        clientController.FocusDefoltText(dataLogin, "Логин");
        dataPanel.add(dataPassword);
        clientController.FocusDefoltText(dataPassword, "Пароль");
        dataPanel.add(dataBtnLogin);

        dataBtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String port = dataPort.getText();
                String name = dataLogin.getText();
                String password = dataPassword.getText();
                clientController.ClientConnectAndUserLogin(name, password, port);
                updateInputZoneStatus.accept(client.isConnectToServer());
            }
        });
    }
    private  void ChatZone(){
        textArea = new JTextArea();

        textArea.setEnabled(true);
        textArea.setForeground(Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false
        );


        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    private void ListZone(){
        chatListPanel = new ChatListPanel();
        chatListPanel.setPreferredSize(new Dimension(100, 0));
        add(new JScrollPane(chatListPanel), BorderLayout.WEST);
        clientController.update();
    }
    private void InputZone(){
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField inputText = new JTextField(20);
        JButton inputBtn = new JButton("Отправить");

        add(inputPanel, BorderLayout.SOUTH);
        inputPanel.add(inputText,BorderLayout.CENTER);
        inputPanel.add(inputBtn, BorderLayout.EAST);

        updateInputZoneStatus = (status) -> {
            inputText.setEnabled(client.isConnectToServer());
            inputBtn.setEnabled(client.isConnectToServer());
        };
        updateInputZoneStatus.accept(client.isConnectToServer());


        inputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputText.getText();
                Message message = new Message(text, client.getUser().getName());
                //clientController.messageToTextArea(message);
                clientController.messageToServerFromClient(message);
                serverWindow.notifyObservers(message);
                inputText.setText("");
            }
        });
    }

    public ChatListPanel getChatListPanel() {
        return chatListPanel;
    }

    public ServerWindow getServerWindow() {
        return serverWindow;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void setUpdateInputZoneStatus(){
        updateInputZoneStatus.accept(client.isConnectToServer());
    }

    public ClientController getClientController() {
        return clientController;
    }
}
