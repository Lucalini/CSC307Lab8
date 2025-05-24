package org.example;

import java.awt.*;

/**
 * Abstract decorator class for adding visual elements to the score display.
 * Uses the Decorator pattern to add various graphical elements.
 *
 * @author Bryce Uota
 */
public abstract class ScoreDecorator {
    protected int x, y;
    protected Color color;

    /**
     * Creates a new score decorator at the specified position.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param color The color to draw with
     */
    public ScoreDecorator(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Draws the decorator element on the screen.
     *
     * @param g The graphics context
     */
    public abstract void draw(Graphics g);

    /**
     * Updates the position of the decorator.
     *
     * @param newX The new x coordinate
     * @param newY The new y coordinate
     */
    public void updatePosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Updates the color of the decorator.
     *
     * @param newColor The new color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }
}