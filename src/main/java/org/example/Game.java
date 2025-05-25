/**
 * this class is the main functionality for the pong gameplay
 *
 * @author Logan Lumetta
 */

package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    private static final int GAME_SPEED = 1;

    private Field field;
    private Ball ball;
    private ChatHandler chatHandler;
    private ScoreDecorator scoreDecorator;
    private Publisher publisher;
    private Repository repository;
    private Timer timer;

    public Game() {
        repository = Repository.getInstance();
        publisher = new Publisher("PongGamePublisher_" + System.currentTimeMillis());
        publisher.connect();

        ball = new Ball();
        chatHandler = new ChatHandler();
        scoreDecorator = new ScoreDecorator();

        field = new Field(this);

        timer = new Timer(GAME_SPEED, this);
        timer.start();
    }

    public Ball getBall() {
        return ball;
    }

    public Field getField() {
        return field;
    }

    public Repository getRepository() {
        return repository;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        field.repaint();
    }

    private void updateGame() {
        ball.move();

        if (ball.getY() <= 0 || ball.getY() >= Field.HEIGHT - ball.getSize()) {
            ball.reverseY();
        }

        if (ball.getBounds().intersects(field.getLeftPaddle().getBounds())) {
            ball.setX(field.getLeftPaddle().getX() + field.getLeftPaddle().getWidth());
            ball.reverseX();
        }

        if (ball.getBounds().intersects(field.getRightPaddle().getBounds())) {
            ball.setX(field.getRightPaddle().getX() - ball.getSize());
            ball.reverseX();
        }

        if (ball.getX() <= 0) {
            repository.incrementRightScore();
            publisher.publishScoreChange("RightPlayer", repository.getRightScore());
            resetBall();
        } else if (ball.getX() >= Field.WIDTH - ball.getSize()) {
            repository.incrementLeftScore();
            publisher.publishScoreChange("LeftPlayer", repository.getLeftScore());
            resetBall();
        }
    }

    private void resetBall() {
        ball.resetPosition();
        ball.randomizeDirection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}
