package org.example.model;

public class Message {

    private String text;
    private MessageSource source;
    private String userName;

    public Message(String text, MessageSource source) {
        this.source = source;
        this.text = text;
    }

    public Message(String text, String userName) {
        this.userName = userName;
        this.source = MessageSource.USER;
        this.text = text;
    }

    @Override
    public String toString() {
        if (this.getSource() == MessageSource.USER){
            return userName + ": " + text + "\n";
        }
        return text + "\n";
    }

    public String getText() {
        return text;
    }

    public MessageSource getSource() {
        return source;
    }

    public String getUserName() {
        return userName;
    }
}