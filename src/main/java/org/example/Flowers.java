package org.example;

import java.awt.*;

/**
 * Flowers decorator that draws flower shapes around the score area.
 * Represents decorative flowers for celebrations.
 *
 * @author Bryce Uota
 */
public class Flowers extends ScoreDecorator {
    private int flowerCount;
    private int petalSize;

    /**
     * Creates flowers decorator with specified number of flowers.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param color The color of the flowers
     * @param flowerCount The number of flowers to draw
     */
    public Flowers(int x, int y, Color color, int flowerCount) {
        super(x, y, color);
        this.flowerCount = flowerCount;
        this.petalSize = 6;
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < flowerCount; i++) {
            int flowerX = x + (i * 30);
            int flowerY = y;

            drawSingleFlower(g, flowerX, flowerY);
        }
    }

    /**
     * Draws a single flower with petals and center.
     *
     * @param g The graphics context
     * @param flowerX The x position of the flower
     * @param flowerY The y position of the flower
     */
    private void drawSingleFlower(Graphics g, int flowerX, int flowerY) {
        // Draw petals (5 petals around center)
        g.setColor(color);

        // Top petal
        g.fillOval(flowerX + petalSize, flowerY, petalSize, petalSize);
        // Bottom petal
        g.fillOval(flowerX + petalSize, flowerY + petalSize * 2, petalSize, petalSize);
        // Left petal
        g.fillOval(flowerX, flowerY + petalSize, petalSize, petalSize);
        // Right petal
        g.fillOval(flowerX + petalSize * 2, flowerY + petalSize, petalSize, petalSize);

        // Draw center
        g.setColor(Color.YELLOW);
        g.fillOval(flowerX + petalSize, flowerY + petalSize, petalSize, petalSize);

        // Draw stem
        g.setColor(Color.GREEN);
        g.drawLine(flowerX + petalSize + (petalSize/2), flowerY + petalSize * 3,
                flowerX + petalSize + (petalSize/2), flowerY + petalSize * 5);

        // Draw leaf
        g.drawOval(flowerX + petalSize + 2, flowerY + petalSize * 4, 4, 6);
    }

    /**
     * Updates the number of flowers to display.
     *
     * @param newFlowerCount The new number of flowers
     */
    public void setFlowerCount(int newFlowerCount) {
        this.flowerCount = newFlowerCount;
    }

    /**
     * Updates the size of the flower petals.
     *
     * @param newPetalSize The new petal size
     */
    public void setPetalSize(int newPetalSize) {
        this.petalSize = newPetalSize;
    }
}