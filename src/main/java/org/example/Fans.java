package org.example;

import java.awt.*;

/**
 * Fans decorator that draws fan shapes around the score area.
 * Represents cheering fans in the stadium.
 *
 * @author Bryce Uota
 */
public class Fans extends ScoreDecorator {
    private int fanCount;

    /**
     * Creates fans decorator with specified number of fans.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param color The color of the fans
     * @param fanCount The number of fans to draw
     */
    public Fans(int x, int y, Color color, int fanCount) {
        super(x, y, color);
        this.fanCount = fanCount;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        // Draw multiple fan figures
        for (int i = 0; i < fanCount; i++) {
            int fanX = x + (i * 25);
            int fanY = y;

            drawSingleFan(g, fanX, fanY);
        }
    }

    /**
     * Draws a single fan figure (stick figure with raised arms).
     *
     * @param g The graphics context
     * @param fanX The x position of the fan
     * @param fanY The y position of the fan
     */
    private void drawSingleFan(Graphics g, int fanX, int fanY) {
        // Draw head
        g.drawOval(fanX, fanY, 8, 8);

        // Draw body
        g.drawLine(fanX + 4, fanY + 8, fanX + 4, fanY + 20);

        // Draw raised arms (cheering pose)
        g.drawLine(fanX + 4, fanY + 12, fanX - 2, fanY + 6);  // Left arm up
        g.drawLine(fanX + 4, fanY + 12, fanX + 10, fanY + 6); // Right arm up

        // Draw legs
        g.drawLine(fanX + 4, fanY + 20, fanX, fanY + 28);     // Left leg
        g.drawLine(fanX + 4, fanY + 20, fanX + 8, fanY + 28); // Right leg
    }

    /**
     * Updates the number of fans to display.
     *
     * @param newFanCount The new number of fans
     */
    public void setFanCount(int newFanCount) {
        this.fanCount = newFanCount;
    }
}