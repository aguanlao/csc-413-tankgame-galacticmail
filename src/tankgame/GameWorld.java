package tankgame;

import common.Explosion;
import common.GameClock;
import common.CollidableObject;
import common.GameObject;

import java.io.*;
import java.util.*;

public class GameWorld implements Observer {
    static boolean isGameOver, player1Won, player2Won;
    
    public static final int WORLD_WIDTH = 960;
    public static final int WORLD_HEIGHT = 960;
    private static final int TANK1_START_X = 200;
    private static final int TANK1_START_Y = 200;
    private static final int TANK2_DIRECTION = 180;
    private static final int TANK2_START_X = 700;
    private static final int TANK2_START_Y = 700;
    private static final int FIRING_DELAY = 1000;
    private static final int NEAR_DISTANCE = 100;
    private static final String TANK_IMAGE1 = "tankgame" + File.separator + "resources" + File.separator + "Tank_blue_heavy_strip60.png";
    private static final String TANK_IMAGE2 = "tankgame" + File.separator + "resources" + File.separator + "Tank_red_heavy_strip60.png";
    private static final String LEVEL_FILE = "tankgame" + File.separator + "resources" + File.separator + "Level30x30.txt";
    private static final String EXPLOSION_IMAGE = "tankgame" + File.separator + "resources" + File.separator + "Explosion_small_strip6.png";
    
    private final TankListener keyListener;
    private final Tank playerOne, playerTwo;
    private final List<GameObject> objects;
    private final List<Shot> shotsFired;
    private final List<String> level;

    public GameWorld(TankListener listener) {
        objects = new ArrayList<>();
        shotsFired = new ArrayList<>();
        level = new ArrayList<>();
        playerOne = new Tank(TANK1_START_X, TANK1_START_Y, TANK_IMAGE1);
        playerTwo = new Tank(TANK2_START_X, TANK2_START_Y, TANK2_DIRECTION, TANK_IMAGE2);
        keyListener = listener;
        player1Won = false;
        player2Won = false;

        keyListener.addTanks(playerOne, playerTwo);
        objects.add(playerOne);
        objects.add(playerTwo);
        readLevel();
        buildLevel();
    }

    public void createShot(Tank tankThatShot) {
        Shot newShot = new Shot(tankThatShot);
        shotsFired.add(newShot);
    }

    private void readLevel() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(LEVEL_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                level.add(line);
            }
        } catch (IOException exception) {}
    }

    private void buildLevel() {
        IndestructibleWall newWall;

        int length = IndestructibleWall.IMAGE_LENGTH;
        for (int row = 0; row < level.size(); row++) {
            for (int col = 0; col < level.get(row).length(); col++) {
                if (level.get(row).charAt(col) == 'X') {
                    newWall = new IndestructibleWall(row * length, col * length);
                    objects.add(newWall);
                }
            }
        }
        buildDestructibles();
    }

    public void buildDestructibles() {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof DestructibleWall) {
                objects.remove(objects.get(i));
            }
        }

        DestructibleWall newWall;

        int length = DestructibleWall.IMAGE_LENGTH;
        for (int row = 0; row < level.size(); row++) {
            for (int col = 0; col < level.get(row).length(); col++) {
                if (level.get(row).charAt(col) == 'B') {
                    newWall = new DestructibleWall(row * length, col * length);
                    objects.add(newWall);
                }
            }
        }
    }

    private boolean isNear(GameObject one, GameObject two) {
        return (one.calculateDistance(two) < NEAR_DISTANCE);
    }
    
    private void worldReset() {
        buildDestructibles();
        shotsFired.clear();
        playerOne.respawn();
        playerTwo.respawn();
    }

    @Override
    public void update(Observable observed, Object arg) {
        //On clock tick, check collisions, firing
        if (playerOne.getLivesLeft() <= 0) {
            player2Won = true;
            isGameOver = true;
        } else if (playerTwo.getLivesLeft() <= 0) {
            player1Won = true;
            isGameOver = true;
        }

        if (!isGameOver) {
            if (((GameClock) observed).getFrame() % FIRING_DELAY == 0) {
                if (playerOne.getShootState()) {
                    createShot(playerOne);
                }
                if (playerTwo.getShootState()) {
                    createShot(playerTwo);
                }
            }
            
            if (!playerOne.isLiveNow() || !playerTwo.isLiveNow()) {
                worldReset();
            }
            
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i) instanceof CollidableObject) {
                    CollidableObject collider = (CollidableObject) objects.get(i);
                    if (isNear(collider, playerOne) && collider != playerOne && playerOne.collides(collider)) {
                        playerOne.setColliding(true);
                    }

                    if (isNear(collider, playerTwo) && collider != playerTwo && playerTwo.collides(collider)) {
                        playerTwo.setColliding(true);
                    }

                    for (int j = 0; j < shotsFired.size(); j++) {
                        Shot thisShot = shotsFired.get(j);
                        if (isNear(collider, thisShot) && thisShot.getSource() != collider && thisShot.collides(collider)) {
                            Explosion newBoom = new Explosion((int) thisShot.getX(), (int) thisShot.getY(), 
                                EXPLOSION_IMAGE, 6);
                            objects.add(newBoom);

                            if (collider instanceof Tank) {
                                ((Tank) collider).tookDamage(thisShot.getDamage());
                            } else if (collider instanceof DestructibleWall) {
                                ((DestructibleWall) collider).tookDamage(thisShot.getDamage());
                                if (!((DestructibleWall) collider).isLiveNow()) {
                                    objects.remove(i);
                                }
                            }
                            shotsFired.remove(thisShot);
                        }
                    }
                } else if (objects.get(i) instanceof Explosion) {
                    if (((Explosion) objects.get(i)).isFinished()) {
                        objects.remove(i);
                    }
                }
            }
        }
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public List<Shot> getShots() {
        return shotsFired;
    }

    public List<Tank> getTanks() {
        List<Tank> tanks = new ArrayList<>();
        tanks.add(playerOne);
        tanks.add(playerTwo);
        return tanks;
    }
}
