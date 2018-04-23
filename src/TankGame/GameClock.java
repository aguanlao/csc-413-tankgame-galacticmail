package TankGame;

import java.util.Observable;

public class GameClock extends Observable{
    private int frame;
    
    public GameClock() {
        frame = 0;
    }
    
    public void tickClock() {
        frame++;
        this.setChanged();
        this.notifyObservers();
    }
    
    public int getFrame() {
        return this.frame;
    }
    
}