package org.example.service;

import org.example.model.Message;
import org.example.model.MessageSource;

import java.io.IOException;

public class MessageService {


    public String messageToString(Message message){

        if (message.getSource() == MessageSource.USER){
            return "{" + message.getText() + "|" + message.getUserName() + "|" + message.getSource().name() + "}";
        }
        return "{" + message.getText() + "|" + message.getSource().toString() + "}";
    }

    public Message messageFromString(String data) throws IOException {
        if (data.length() < 5){
            throw new IOException("Ошибка преобразования пользователя (проверте форматирование в базе данных)");
        }
        data = data.substring(1, data.length() -1);
        String[] messageData = data.split("\\|");
        if (messageData.length == 3){
            return new Message(messageData[0], messageData[1]);
        }
        return new Message(messageData[0], MessageSource.valueOf(messageData[1]));
    }


}
