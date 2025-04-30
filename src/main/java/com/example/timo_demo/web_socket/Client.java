package com.example.timo_demo.web_socket;

import com.example.timo_demo.MessageListener;
import com.example.timo_demo.StompHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Client {
    private static StompSession session;
    private String username;

    public Client(MessageListener messageListener, String username) throws ExecutionException, InterruptedException {
        this.username = username;

        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new StompHandler(messageListener, username);
        String url = "https://message-app-ru87.onrender.com/ws"; // Use ws:// for WebSocket

        session = stompClient.connectAsync(url, sessionHandler).get();
    }

    public static void sendMessage(Message message) {
        try {
            session.send("/app/message", message);
            System.out.println("Message Sent: " + message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectUser(String username) {
        session.send("/app/disconnect", username);
        System.out.println("Disconnect User: " + username);
    }
}

