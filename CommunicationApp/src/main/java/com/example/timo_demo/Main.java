package com.example.timo_demo;

import com.example.timo_demo.web_socket.Client;
import com.example.timo_demo.web_socket.Message;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws AWTException, ExecutionException, InterruptedException {
        Gui gui = new Gui();
        gui.gui();

        // test for connection to socket
        Client client = new Client("timo");
       // client.sendMessage(new Message("timo", "hi"));
       // client.disconnectUser("timo");
    }
}

