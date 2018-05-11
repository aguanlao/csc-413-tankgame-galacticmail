package galacticmail;

import common.GameClock;

public class GalacticGame {
    public GalacticGame() {
    	
    }

    public static void main(String[] args) {
        final GameClock clock = new GameClock();
        final GalacticListener keys = new GalacticListener();
        final GalacticWorld world = new GalacticWorld(keys);
        final GalacticWindow window = new GalacticWindow(world);
        window.addKeyListener(keys);
        clock.addObserver(world);
        clock.addObserver(window);
        
        Thread clockThread = new Thread(clock);
        clockThread.start();
        
    }
}