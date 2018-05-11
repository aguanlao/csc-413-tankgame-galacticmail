package galacticmail;

import java.io.*;
import java.util.*;

import common.CollidableObject;
import common.GameObject;
import tankgame.Shot;

public class GalacticWorld implements Observer {

	static boolean GameOver, endScreen;
	
    public static final int WORLD_WIDTH = 960;
    public static final int WORLD_HEIGHT = 960;
	
	private final List<Asteroid> asteroids;
    private final GalacticListener keyListener;
    private final Ship player;
    private final List<GameObject> objects;
    private final List<Shot> shotsFired;
    private final List<String> level;
    
	public GalacticWorld(GalacticListener listener) {
        objects = new ArrayList<>();
        shotsFired = new ArrayList<>();
        level = new ArrayList<>();
        asteroids = new ArrayList<>();
        player = new Ship(WORLD_WIDTH/2, WORLD_HEIGHT/2);
        keyListener = listener;

        GameOver = false;
        endScreen = false;
        
        keyListener.addShip(player);
        objects.add(player);
        //readLevel();
        //buildLevel();
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}
	
    private boolean isNear(GameObject one, GameObject two) {
        return (one.calculateDistance(two) < 100);
    }
    
	@Override
	public void update(Observable arg0, Object arg1) {
		if (player.isLiveNow() == false) {
			GameOver = true;
		}
		
		if (!GameOver) {
			// check if player collides with a moon or an asteroid
			for (int i = 0; i < objects.size(); i++) {
				if (objects.get(i) instanceof Asteroid) {
                    CollidableObject collider = (CollidableObject) objects.get(i);
                    if (isNear(collider, player) && collider != player && player.collides(collider)) {
                        player.setLanding((CollidableObject)objects.get(i));
                    }
				}
			}
			
		}
	}

}
