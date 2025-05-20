package org.example;

import java.util.Observable;

public class Repository extends Observable {
    private int x = 0;
    private int y = 0;
    private static Repository instance = null;

    private Repository() {
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }

        return instance;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
        this.setChanged();
        this.notifyObservers();
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
        this.setChanged();
        this.notifyObservers();
    }
}
