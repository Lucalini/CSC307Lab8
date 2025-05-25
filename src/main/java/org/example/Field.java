/**
 * displays a green screen for the game to be set on
 *
 * @author Logan Lumetta
 */

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


        this.setBackground(new Color(149, 229, 149));
        setLayout(new BorderLayout());
        add(this, BorderLayout.CENTER);

        setVisible(true);
    }
}

