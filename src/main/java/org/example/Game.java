package org.example;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private Field field;
    private Ball ball;
    private ChatHandler chatHandler;
    private ScoreDecorator scoreDecorator;
    private Publisher publisher;
    private DataRepository dataRepository;

    public Game() {
    }
