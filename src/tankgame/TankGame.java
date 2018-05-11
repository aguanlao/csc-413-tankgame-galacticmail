package tankgame;

import common.GameClock;

public class TankGame {

    public static void main(String[] args) {
        final GameClock clock = new GameClock();
        final TankListener keys = new TankListener();
        final GameWorld world = new GameWorld(keys);
        final Window window = new Window(world);
        window.addKeyListener(keys);
        clock.addObserver(world);
        clock.addObserver(window);
        
        Thread clockThread = new Thread(clock);
        clockThread.start();
        
    }
    
}
