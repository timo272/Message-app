package com.example.timo_demo.web_socket;

public class Message {
    private String user;
    private String message;

    public Message(){}
    public Message(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public String getUser() {
        return user;
    }
    public String getMessage() {
        return message;
    }
}
