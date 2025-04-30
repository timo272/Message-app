package com.example.timo_demo;

import com.example.timo_demo.web_socket.Message;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * this is a gui class
 */

public class Gui {
    // create a main panel
    private JPanel messagePanel;

    // create a gui by this public class
    public void gui() throws AWTException {
        guiComponents();
    }

    private void guiComponents() throws AWTException {
        // create a frame
        JFrame frame = new JFrame("Communication Panel");

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

        // create a chat panel (main panel)
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        chatPanel.setBackground(Utilities.SOFTBLACK);
        
        // create a message panel (to display messages)
        messagePanel = new JPanel();
        messagePanel.setBackground(Utilities.SOFTBLACK);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        chatPanel.add(messagePanel, BorderLayout.CENTER);

        // create a label
        JLabel chatLabel = new JLabel("Welcome to Timo App");
        chatLabel.setBounds(500, 400, 100, 30);
        chatLabel.setBackground(Utilities.SOFTWHITE);

        // create a typing field and make round corners
        JTextField textField = new RoundJTextField(10);

        // set up a text field to type messages
        // when enter is pressed send a message
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    String message = textField.getText();

                    if(message.isBlank()) return;

                    textField.setText("");

                    messagePanel.add(createChatMessageComponent(new Message("Timo", message)));
                    frame.repaint();
                    frame.revalidate();
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

        // display a window
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

    private EmptyBorder addPaddind(int top, int left, int bottom, int right) {
        return new EmptyBorder(top, left, bottom, right);
    }
}