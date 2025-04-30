package com.example.timo_demo;

import com.example.timo_demo.web_socket.Message;

import javax.swing.*;
import java.util.ArrayList;

public interface MessageListener {
    public JFrame frame = new JFrame();
    void onMessageRecieve(Message message);
    void onActiveUsersUpdated(ArrayList<String> users);
}