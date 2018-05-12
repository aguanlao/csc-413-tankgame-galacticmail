package galacticmail;

import java.io.*;
import java.util.*;
import java.awt.Point;

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
        
        buildLevel();
    } 

    private boolean isNear(GameObject one, GameObject two) {
        return (one.calculateDistance(two) < 100);
    }
    
    private Point randomPosition() {
        int x, y;
        
        x = (int)(Math.random() * WORLD_WIDTH);
        y = (int)(Math.random() * WORLD_HEIGHT);
        return new Point(x, y);
    }
    
    private void spawnAsteroid() {
        int direction;
        double speed, rotateSpeed;
        
        Point start = randomPosition();        
        direction = (int)(Math.random() * 360);
        speed = Math.random() + 0.5;
        rotateSpeed = Math.random() * 2;
        
        asteroids.add(new Asteroid(start.x, start.y, direction, speed, rotateSpeed));
    }
    
    private void spawnPlanet() {
        Point position = randomPosition();
        objects.add(new Planet(position.x, position.y));
    }
    
    private void checkPosition(GameObject object) {
        int newX, newY;
        
        if(object.getX() <= 0) {
            object.setX(WORLD_WIDTH);
        }
        else if(object.getX() >= WORLD_WIDTH) {
            object.setX(0);
        }
    
        if(object.getY() <= 0) {
            object.setY(WORLD_HEIGHT);
        }
        else if(object.getY() >= WORLD_HEIGHT) {
            object.setY(0);
        }
    }

    public void buildLevel() {
        // add ~10 asteroids and ~5 bases
        for(int i = 0; i < 10; i++) {
            if(i < 5) {
                spawnPlanet();
            }
            spawnAsteroid();
        }
        
    }

    public List<GameObject> getObjects() {
        return objects;
    }
    
    public List<Asteroid> getAsteroids() {
        return asteroids;
    }  

    @Override
    public void update(Observable observed, Object arg) {
        if (player.isLiveNow() == false) {
            GameOver = true;
        }

        if (!GameOver) {
            // check if player collides with a moon or an asteroid
            // player will clip onto the moon or explode via asteroid
            for (int i = 0; i < objects.size(); i++) {

                if (objects.get(i) instanceof Planet) {
                    Planet base = (Planet) objects.get(i);
                    if (isNear(base, player) && player.collides(base)) {
                        player.setLanding(objects.get(i));
                    }
                } else if (objects.get(i) instanceof Asteroid) {
                    Asteroid asteroid = (Asteroid) objects.get(i);
                    if (isNear(asteroid, player) && player.collides(asteroid)) {
                        Explosion newBoom = new Explosion((int) player.getX(), (int) player.getY(), 
                                EXPLOSION_IMAGE, 9);
                        objects.add(newBoom);
                        player.setLive(false);
                    }
                } else if (objects.get(i) instanceof Explosion) {
                    if (((Explosion) objects.get(i)).isFinished()) {
                        objects.remove(i);
                    }
                }
                
                checkPosition(objects.get(i));
            } // for int i
        } // (!GameOver)
    }

}
