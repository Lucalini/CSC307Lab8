package org.example;

import javax.swing.*;
import java.awt.*;

public class Field extends JFrame {
    public static void main(String[] arg){
        Field field = new Field();
        field.setSize(800, 600);
        field.setTitle("My Paint App");
        field.setVisible(true);
        field.setResizable(false);
        field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Field(){
        JPanel p1 = new JPanel();
        BorderLayout b = new BorderLayout();
        GridLayout g = new GridLayout();
        setLayout(b);
        add(p1);

        p1.setBackground(new Color(120, 200, 120));

    }
}
