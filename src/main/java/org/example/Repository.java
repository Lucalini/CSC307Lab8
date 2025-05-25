/**
 * repository for classes to pull and push data from and to
 *
 * @author Logan Lumetta
 */

package org.example;

import java.util.Observable;

public class Repository extends Observable {
    private int x = 0;
    private int y = 0;
    private static Repository instance = null;

    private Repository() {}

    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public int getX() { return x; }

    public void setX(int x) {
        this.x = x;
        setChanged();
        notifyObservers("x changed");
    }

    public int getY() { return y; }

    public void setY(int y) {
        this.y = y;
        setChanged();
        notifyObservers("y changed");
    }
}
