package org.example.model;

public enum MessageSource {
    SERVER,    // Сообщение от сервера
    USER,      // Сообщение от пользователя
    SERVICE;// Сервисное сообщение

    private String messageSourceName;


//    private MessageSource(String messageSourceName){
//        this.messageSourceName = messageSourceName;
//    }
//
//    @Override
//    public String toString() {
//        return messageSourceName;
//    }
}