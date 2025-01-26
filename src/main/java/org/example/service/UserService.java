package org.example.service;

import org.example.model.User;

import java.io.IOException;

public class UserService {

    public User cteateUser(String name, String password){
        return new User(name, password);
    }

    public String userToString(User user){
        return "{" + user.getName() + "|" + user.getPassword() + "}";
    }

    public User userFromString(String data) throws IOException {
        if (data.length() < 5){
            throw new IOException("Ошибка преобразования пользователя (проверте форматирование в базе данных)");
        }
        data = data.substring(1, data.length() -1);
        String[] userData = data.split("\\|");
        return new User(userData[0], userData[1]);
    }

    public boolean paswordEquals(User user, String pasword){
        return user.getPassword().equals(pasword);
    }
}