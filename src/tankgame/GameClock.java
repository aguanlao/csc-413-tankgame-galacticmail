package tankgame;

import java.util.Observable;
import static java.lang.Thread.sleep;

public class GameClock extends Observable implements Runnable{
    private static final int SLEEP_TIME = 0;
    
    private int frame;
    private boolean ticking;
    
    public GameClock() {
        frame = 0;
        ticking = true;
    }
    
    public void tickClock() {
        frame++;
        this.setChanged();
        this.notifyObservers();
    }
    
    public int getFrame() {
        return this.frame;
    }
    
    public boolean isTicking() {
        return ticking;
    }
    
    public void setTicking(boolean flag) {
        ticking = flag;
    }
    
    @Override
    public void run() {
        while(ticking) {
            tickClock();
            try {
                sleep(SLEEP_TIME);
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
    
}