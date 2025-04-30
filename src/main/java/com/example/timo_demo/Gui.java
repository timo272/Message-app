package com.example.timo_demo;

import com.example.timo_demo.web_socket.Client;
import com.example.timo_demo.web_socket.Message;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * this is a gui class
 */

public class Gui implements MessageListener{
    String user;
    private JTextField textField;
    private JPanel chatPanel;
    private JFrame frame;
    // create a main panel
    private JPanel messagePanel;
    // create a user
    private Client client;
    // create a gui by this public class
    public void gui() throws AWTException {
        guiComponents();
    }

    private void guiComponents() throws AWTException {
        // create a frame
        frame = new JFrame();

        // set up a frame
        // close on exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the size
        frame.setBounds(1000, 1000, 1000, 800);
        // put a frame at the center
        frame.setLocationRelativeTo(null);
        // make a black background of the frame
        frame.getContentPane().setBackground(Utilities.SOFTBLACK);
        // make a window not resizable
        frame.setResizable(false);

        // display a window
        String user = JOptionPane.showInputDialog(frame, "Enter your user name");
        frame.setTitle(user);

        addChatComponents(frame, user);

        // create a user
        try {
            client = new Client(this, user);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    // this method is used to display a message from the text field
    private JPanel createChatMessageComponent(Message message){
        JPanel chatMessage = new JPanel();
        chatMessage.setBackground(Utilities.SOFTBLACK);
        chatMessage.setLayout(new BoxLayout(chatMessage, BoxLayout.Y_AXIS));
        chatMessage.setBorder(addPaddind(20, 20, 10, 20));

        JLabel usernameLabel = new JLabel(message.getUser());
        usernameLabel.setFont(new Font("Inter", Font.BOLD, 18));
        usernameLabel.setForeground(Utilities.SOFTWHITE);
        chatMessage.add(usernameLabel);

        JLabel messageLabel = new JLabel(message.getMessage());
        messageLabel.setFont(new Font("Inter", Font.BOLD, 18));
        messageLabel.setForeground(Utilities.SOFTWHITE);
        chatMessage.add(messageLabel);

        return chatMessage;
    }

    private void addChatComponents(JFrame frame, String user){
        // create a chat panel (main panel)
        chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.setBackground(Utilities.SOFTBLACK);

        // create a message panel (to display messages)
        messagePanel = new JPanel();
        messagePanel.setBackground(Utilities.SOFTBLACK);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        chatPanel.add(messagePanel, BorderLayout.CENTER);

        // create a typing field and make round corners
        textField = new RoundJTextField(10);

        // set up a text field to type messages
        // when enter is pressed send a message
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    String message = textField.getText();

                    if(message.isBlank()) return;

                    textField.setText("");

                    Client.sendMessage(new Message(user, message));
                }
            }
        });
        // set the size and location
        textField.setBounds(400, 700, 300, 40);
        // set a color
        textField.setBackground(Utilities.BLACK);
        // set a color of letters
        textField.setForeground(Utilities.SOFTWHITE);
        // set a size and a color of letters
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        // delete sides of the text field
        textField.setBorder(BorderFactory.createEmptyBorder());
        // make a cursor white
        textField.setCaretColor(Utilities.SOFTWHITE);
        // create a hint "message" to write it
        textField.setUI(new HintTextFieldUI("Message", false));

        // add a text field to the frame
        frame.add(textField);

        // add a main panel to the frame
        frame.add(chatPanel, BorderLayout.CENTER);
    }

    private EmptyBorder addPaddind(int top, int left, int bottom, int right) {
        return new EmptyBorder(top, left, bottom, right);
    }

    @Override
    public void onMessageRecieve(Message message) {
        messagePanel.add(createChatMessageComponent(message));
        messagePanel.revalidate();
    }

    @Override
    public void onActiveUsersUpdated(ArrayList<String> users) {
    }
}