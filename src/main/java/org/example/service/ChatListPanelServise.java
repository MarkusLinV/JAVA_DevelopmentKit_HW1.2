package org.example.service;

import org.example.model.User;

import javax.swing.*;
import java.util.ArrayList;

public class ChatListPanelServise {
    private DefaultListModel<User> userModel;

    public ChatListPanelServise(DefaultListModel<User> userModel) {
        this.userModel = userModel;
    }

    public void updateUser(ArrayList<User> users){
        SwingUtilities.invokeLater(() -> {
            userModel.removeAllElements();
            for (User user : users) {
                userModel.addElement(user);
            }
        });
    }
}