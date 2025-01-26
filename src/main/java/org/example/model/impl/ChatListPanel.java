package org.example.model.impl;

import org.example.model.User;

import javax.swing.*;
import java.awt.*;

public class ChatListPanel extends JPanel{

    private JList<User> userlist;
    private DefaultListModel<User> userModel;

    public ChatListPanel(){
        userModel = new DefaultListModel<>();
        userlist = new JList<>(userModel);
        setPreferredSize(new Dimension(100, 0));
        add(userlist, BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }

    public DefaultListModel<User> getUserModel() {
        return userModel;
    }
}