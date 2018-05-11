package galacticmail;

import common.GameClock;

public class GalacticMail {

    public static void main(String[] args) {
        final GameClock clock = new GameClock();
        final GalacticListener listener = new GalacticListener();
        final GalacticWorld world = new GalacticWorld(listener);
        final GalacticWindow window = new GalacticWindow();
    }
    
}
