package org.example;

import javax.swing.*;
import java.awt.*;

public class Field extends JFrame {
    private Game game;

    public Field(Game game) {
        this.game = game;
        setTitle("Pong Game");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel(game);
        panel.setBackground(new Color(149, 229, 149));
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}

