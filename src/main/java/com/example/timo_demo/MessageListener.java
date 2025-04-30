package com.example.timo_demo;

import com.example.timo_demo.web_socket.Message;

import java.util.ArrayList;

public interface MessageListener {
    void onMessageRecieve(Message message);
    void onActiveUsersUpdated(ArrayList<String> users);
}