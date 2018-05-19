package galacticmail;

import java.io.*;
import java.util.*;
import java.awt.Point;

import common.GameObject;
import common.CollidableObject;
import common.Explosion;
import common.GameClock;

public class GalacticWorld implements Observer {

    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 600;
    private static final int NEAR_DISTANCE = 100;
    private static final int SPAWN_MARGIN = 200;
    private static final int SCORE_DELAY = 30000;
    private static final int CLOCK_DELAY = 4000;
    private static final int BASE_REWARD = 50;
    private static final String EXPLOSION_IMAGE = "galacticmail" + File.separator
            + "resources" + File.separator + "Explosion_strip9.png";

    private final GalacticListener keyListener;
    private final Ship player;
    private final List<GameObject> objects;
    private final List<Asteroid> asteroids;
    private int score, deliveryCount;
    private boolean gameOver, endScreen;

    public GalacticWorld(GalacticListener listener) {
        objects = new ArrayList<>();
        asteroids = new ArrayList<>();
        player = new Ship(0, 0);
        keyListener = listener;
        score = 0;
        deliveryCount = 0;

        gameOver = false;
        endScreen = false;

        keyListener.setShip(player);

        buildLevel();
        player.moveTo(WORLD_WIDTH/2, WORLD_HEIGHT/2);
    }

    private boolean isNear(GameObject one, GameObject two) {
        return (one.calculateDistance(two) < NEAR_DISTANCE);
    }

    private Point randomPosition() {
        int x, y;

        x = (int) (Math.random() * (WORLD_WIDTH - SPAWN_MARGIN)) + (SPAWN_MARGIN / 2);
        y = (int) (Math.random() * (WORLD_HEIGHT - SPAWN_MARGIN)) + (SPAWN_MARGIN / 2);
        return new Point(x, y);
    }

    private Asteroid spawnAsteroid() {
        int direction;
        double speed, rotateSpeed;

        Point start = randomPosition();
        direction = (int) (Math.random() * 360);
        speed = (Math.random() * 0.5) + 0.2;
        rotateSpeed = Math.random() * 0.5;

        return new Asteroid(start.x, start.y, direction, speed, rotateSpeed);
    }

    private Planet spawnPlanet() {
        Point position = randomPosition();
        Planet newPlanet = new Planet(position.x, position.y);

        for (int i = 0; i < objects.size(); i++) {
            if (isNear(newPlanet, objects.get(i)) || isNear(newPlanet, player)) {
                i = 0;
            } else {
                continue;
            }
            position = randomPosition();
            newPlanet = new Planet(position.x, position.y);
        }
        
        return newPlanet;
    }

    private void checkPosition(GameObject object) {
        if (object.getX() + object.getWidth() < 0 || object.getX() > WORLD_WIDTH
                || object.getY() + object.getHeight() < 0 || object.getY() > WORLD_HEIGHT) {
            double newX, newY;

            if (object.getX() + object.getWidth() < 0) {
                newX = WORLD_WIDTH;
            } else if (object.getX() > WORLD_WIDTH) {
                newX = -object.getWidth() + 1;
            } else {
                newX = object.getX();
            }

            if (object.getY() + object.getHeight() < 0) {
                newY = WORLD_HEIGHT;
            } else if (object.getY() > WORLD_HEIGHT) {
                newY = -object.getHeight() + 1;
            } else {
                newY = object.getY();
            }

            if (object instanceof CollidableObject) {
                ((CollidableObject) object).moveTo((int) newX, (int) newY);
            } else {
                object.setX(newX);
                object.setY(newY);
            }
        }
    }

    public void buildLevel() {
        for (int i = 0; i < 10; i++) {
            if (i < 7) {
                objects.add(spawnPlanet());
            }
            objects.add(spawnAsteroid());
        }
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public Ship getShip() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public boolean getGameOverState() {
        return gameOver;
    }

    public boolean getEndScreenState() {
        return endScreen;
    }

    @Override
    public void update(Observable observed, Object arg) {
        if (!player.isLiveNow()) {
            gameOver = true;
            endScreen = true;
        }

        if (((GameClock) observed).getFrame() % CLOCK_DELAY == 0) {
            // check if player collides with a moon or an asteroid
            // player will clip onto the moon or explode via asteroid
            if (player.getLandedState()) {
                if (score > 0 && ((GameClock) observed).getFrame() % SCORE_DELAY == 0) {
                    score--;
                }
            }

            ListIterator<GameObject> objIterator = objects.listIterator();
            while (objIterator.hasNext()) {
                GameObject obj = objIterator.next();
                if (!gameOver) {
                    if (obj instanceof Planet) {
                        Planet base = (Planet) obj;

                        if (isNear(base, player)) {
                            if (base.isLandedOn()) {
                                if (!player.getLandedState()) {
                                    objIterator.remove();
                                    objIterator.add(spawnPlanet());
                                }
                            } else if (!player.getLandedState() && player.collides(base)) {
                                player.landOn(base);
                                base.markDelivered();
                                score += BASE_REWARD;
                                deliveryCount++;
                                if (deliveryCount % 5 == 0) {
                                    objIterator.add(spawnAsteroid());
                                }
                            }
                        }

                    } else if (obj instanceof Asteroid) {
                        Asteroid asteroid = (Asteroid) obj;
                        if (isNear(asteroid, player) && !player.getLandedState() && player.collides(asteroid)) {
                            Explosion newBoom = new Explosion((int) player.getX(), (int) player.getY(),
                                    EXPLOSION_IMAGE, 9);
                            objIterator.add(newBoom);
                            player.setLive(false);
                        }
                    } else if (obj instanceof Explosion) {
                        if (((Explosion) obj).isFinished()) {
                            objIterator.remove();
                        }
                    }
                }
                checkPosition(obj);
            }
            checkPosition(player);
        }
    }
}
