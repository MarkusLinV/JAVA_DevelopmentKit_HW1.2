package org.example.service;

import org.example.model.DB.FilesNames;
import org.example.model.Message;
import org.example.model.MessageSource;
import org.example.model.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class DBService {

    UserService userService = new UserService();
    MessageService messageService = new MessageService();

    public boolean userContains(User user) throws IOException {
        if (getUserFromDB().contains(user)){
            return true;
        }
        return false;
    }

    public void addUserToDB(User user) throws IOException {
        String userString = userService.userToString(user) + "\n";
        writeToFile(FilesNames.LIST_OF_USERS, userString);
    }

    public ArrayList<User> getUserFromDB() throws IOException {
        ArrayList<User> result = new ArrayList<>();
        String usersData = readFromFile(FilesNames.LIST_OF_USERS);

        if (usersData.length() != 0) {
            String[] usersArray = usersData.split("\n");
            for (String s : usersArray) {
                result.add(userService.userFromString(s));
            }
        }
        return result;
    }

    public void addMessageToDB(Message message) throws IOException {
        String messageString = messageService.messageToString(message) + "\n";
        writeToFile(FilesNames.MESSAGE_HISTORY, messageString);
    }

    public ArrayList<Message> getMessageFromDB() throws IOException {
        ArrayList<Message> result = new ArrayList<>();
        String messageData = readFromFile(FilesNames.MESSAGE_HISTORY);
        if (messageData.length() !=0){
            String [] messageArray = messageData.split("\n");
            for (String s : messageArray){
                result.add(messageService.messageFromString(s));
            }
        }
        return result;
    }

    public String readFromFile(FilesNames fileName) throws IOException {
        Path pathToFile = Paths.get("src", "main", "java", "org", "example", "model", "DB",fileName.getFailName());
        if (!Files.exists(pathToFile)){
            throw new IOException("Ошибка: файл не найден");
        }

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(pathToFile)){
            String line;
            while ((line = reader.readLine()) != null){
                result.append(line).append("\n");
            }
        } catch (IOException e){
            throw new IOException("Ошибка чтения файла", e);
        }
        return  result.toString();
    }

    public void writeToFile(FilesNames fileName, String text) throws IOException {
        Path pathToFile = Paths.get("src", "main", "java", "org", "example", "model", "DB",fileName.getFailName());
        if (!Files.exists(pathToFile)){
            throw new IOException("Ошибка: файл не найден");
        }
        try(BufferedWriter writer = Files.newBufferedWriter(pathToFile,StandardOpenOption.APPEND)){
            writer.write(text);
        } catch (IOException e){
            throw new IOException("Ошибка записи в файл", e);
        }
    }

    public User userFromDBbyName(String userName) throws IOException {
        ArrayList<User> users = getUserFromDB();
        for (User user : users){
            if(user.getName().equals(userName)){
                return user;
            }
        }
        throw new IOException("Пользователь с именем " + userName + " не найден в базе данных.");
    }

}