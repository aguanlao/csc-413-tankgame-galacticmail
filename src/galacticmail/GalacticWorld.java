package galacticmail;

import java.io.*;
import java.util.*;

public class GalacticWorld implements Observer {

	static boolean GameOver, endScreen;
	
    public static final int WORLD_WIDTH = 960;
    public static final int WORLD_HEIGHT = 960;
	
	private final List<Asteroid> asteroids;
    private final GalacticListener keyListener;
    private final Ship playerOne;
    private final List<GameObject> objects;
    private final List<Shot> shotsFired;
    private final List<String> level;
    
	public GalacticWorld(GalacticListener listener) {
        objects = new ArrayList<>();
        shotsFired = new ArrayList<>();
        level = new ArrayList<>();
        playerOne = new Ship(WORLD_WIDTH/2, WORLD_HEIGHT/2);
        keyListener = listener;

        keyListener.addShip(playerOne);
        objects.add(playerOne);
        readLevel();
        buildLevel();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
