package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    private static final int GAME_SPEED = 10; // milliseconds between updates

    private Field field;
    private Ball ball;
    private ChatHandler chatHandler;
    private ScoreDecorator scoreDecorator;
    private Publisher publisher;
    private Repository repository;
    private Timer timer;

    public Game() {
        repository = Repository.getInstance();
        publisher = new Publisher();
        ball = new Ball();  // This should have position, velocity, and move logic
        chatHandler = new ChatHandler();
        scoreDecorator = new ScoreDecorator();
        field = new Field(this);

        // Start the game loop
        timer = new Timer(GAME_SPEED, this);
        timer.start();
    }

    public Ball getBall() {
        return ball;
    }

    public Repository getRepository() {
        return repository;
    }

    public void actionPerformed(ActionEvent e) {
        updateGame();
        field.repaint();
    }

    private void updateGame() {
        ball.move();

        if (ball.getY() <= 0 || ball.getY() >= Field.HEIGHT - ball.getSize()) {
            ball.reverseY();
        }

        if (ball.getX() <= 0 || ball.getX() >= Field.WIDTH - ball.getSize()) {
            resetBall();
        }

        // TODO: Paddle collision detection and score updates
    }

    private void resetBall() {
        ball.resetPosition();
        ball.randomizeDirection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}
