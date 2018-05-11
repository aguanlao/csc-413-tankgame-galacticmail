package galacticmail;

import java.io.*;
import java.util.*;

import common.GameObject;
import common.Explosion;

public class GalacticWorld implements Observer {

    public static final int WORLD_WIDTH = 960;
    public static final int WORLD_HEIGHT = 960;
    private static final String EXPLOSION_IMAGE = "galacticmail" + File.separator
            + "resources" + File.separator + "Explosion_strip9.png";

    private final GalacticListener keyListener;
    private final Ship player;
    private final List<GameObject> objects;
    private final List<Asteroid> asteroids;
    private final List<String> level;
    static boolean GameOver, endScreen;

    public GalacticWorld(GalacticListener listener) {
        objects = new ArrayList<>();
        level = new ArrayList<>();
        asteroids = new ArrayList<>();
        player = new Ship(WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
        keyListener = listener;

        GameOver = false;
        endScreen = false;

        keyListener.setShip(player);
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

    public void buildLevel() {
        Asteroid newAsteroid;
        // add ~10 asteroids and ~5 bases
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (player.isLiveNow() == false) {
            GameOver = true;
        }

        if (!GameOver) {
            // check if player collides with a moon or an asteroid
            // player will clip onto the moon or explode via asteroid
            for (int i = 0; i < objects.size(); i++) {

                if (objects.get(i) instanceof Planet) {
                    Planet collider = (Planet) objects.get(i);
                    if (isNear(collider, player) && player.collides(collider)) {
                        player.setLanding(objects.get(i));
                    }
                } else if (objects.get(i) instanceof Asteroid) {
                    Asteroid collider = (Asteroid) objects.get(i);
                    if (isNear(collider, player) && player.collides(collider)) {
                        Explosion newBoom = new Explosion((int) player.getX(), (int) player.getY(), 
                                EXPLOSION_IMAGE, 9);
                        objects.add(newBoom);
                    }
                } else if (objects.get(i) instanceof Explosion) {
                    if (((Explosion) objects.get(i)).isFinished()) {
                        objects.remove(i);
                    }
                }
            } // for int i
        } // (!GameOver)
    }

}
