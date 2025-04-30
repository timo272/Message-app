package com.example.timo_demo;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws AWTException, ExecutionException, InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Gui gui = new Gui();
                    gui.gui();
                } catch (AWTException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

