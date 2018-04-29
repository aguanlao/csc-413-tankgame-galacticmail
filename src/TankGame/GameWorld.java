package TankGame;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class GameWorld implements Observer, Runnable{

    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 1000;
    private static final int TANK1_START_X = 200;
    private static final int TANK1_START_Y = 200;
    private static final int TANK2_START_X = 200;
    private static final int TANK2_START_Y = 300;   
    private static final int FIRING_DELAY = 1000;
    private static final int NEAR_DISTANCE = 100;
    private static final String TANK_IMAGE1 = "resources" + File.separator + "Tank_blue_heavy_strip60.png";
    private static final String TANK_IMAGE2 = "resources" + File.separator + "Tank_red_heavy_strip60.png";
    
    private final TankListener keyListener;
    public static Tank playerOne, playerTwo;
    private final ArrayList<GameObject> objects;
    private ArrayList<Shot> shotsFired;

    public GameWorld(TankListener listener) {
        objects = new ArrayList<GameObject>();
        shotsFired = new ArrayList<Shot>();
        playerOne = new Tank(TANK1_START_X, TANK1_START_Y, TANK_IMAGE1);
        playerTwo = new Tank(TANK2_START_X, TANK2_START_Y, TANK_IMAGE2);
        keyListener = listener;
        
        keyListener.addTanks(playerOne, playerTwo);
        objects.add(playerOne);
        objects.add(playerTwo);
        buildLevel();
    }
    
    public void createShot(Tank tankThatShot) {
    	Shot newShot = new Shot(tankThatShot);
    	shotsFired.add(newShot);
//    	tankThatShot.setShooting(false);
    }
    
    private void buildLevel() {
        int wallX, wallY, wallWidth, wallHeight;
        wallX = wallY = 0;
        IndestructibleWall wall = new IndestructibleWall(wallX, wallY);
        objects.add(wall);
        
        wallWidth = wall.getWidth();
        wallHeight = wall.getHeight();
        for(wallX = wallWidth; wallX < WORLD_WIDTH; wallX+=wallWidth) {
           wall = new IndestructibleWall(wallX, 0);
           objects.add(wall);
           wall = new IndestructibleWall(wallX, WORLD_HEIGHT - wallHeight);
           objects.add(wall);
        }
        
        for(wallY = wallHeight; wallY < WORLD_HEIGHT; wallY+=wallHeight) {
            wall = new IndestructibleWall(0, wallY);
            objects.add(wall);
            wall = new IndestructibleWall(WORLD_WIDTH - wallWidth, wallY);
            objects.add(wall);
        }
    }
    
    private boolean isNear(GameObject one, GameObject two) {
        return (one.calculateDistance(two) < NEAR_DISTANCE);
    }
    
    //TODO: Might need to create thread for running collision checks to improve performance
    @Override
    public void run() {
        
    }
    
    @Override
    public void update(Observable observed, Object arg) {
        //On clock tick, check collisions, firing
        
        if (((GameClock)observed).getFrame() % FIRING_DELAY == 0) {
            if (playerOne.getShootState()) {
                createShot(playerOne);
            }
            if(playerTwo.getShootState()) {
                createShot(playerTwo);
            }
        }
        for (int i = 0; i < objects.size(); i++) {
        	if (!playerOne.isLiveNow()) {
        		playerOne.respawn();
        	}
        	
            if (objects.get(i) instanceof CollidableObject) {
                CollidableObject collider = (CollidableObject)objects.get(i);
                if (isNear(collider, playerOne) && collider != playerOne && playerOne.collides(collider)) {
                    playerOne.setColliding(true);
                }
                
                if(isNear(collider, playerTwo) && collider != playerTwo && playerTwo.collides(collider)) {
                    playerTwo.setColliding(true);
                }
                
                for(int j = 0; j < shotsFired.size(); j++) {
                    Shot thisShot = shotsFired.get(j);
                    if (isNear(collider, thisShot) && thisShot.getSource() != collider && thisShot.collides(collider)){
                        Explosion newBoom = new Explosion((int)thisShot.getX(), (int)thisShot.getY());
                        objects.add(newBoom);
                        shotsFired.remove(thisShot);
                        if (collider instanceof Tank)
                        {
                        	((Tank) collider).tookDamage(20);
	                        System.out.println("A shot collided with something!");
	                        System.out.println("HP: " + ((Tank) collider).getHp());
                        }
                    }
                }
                
            }
            else if(objects.get(i) instanceof Explosion) {
                if(((Explosion)objects.get(i)).isFinished()) {
                    objects.remove(i);
                }
            }
            

        }
    }
    
    public ArrayList<GameObject> getObjects() {
        return objects;
    }
    
    public ArrayList<Shot> getShots() {
    	return shotsFired;
    }
}
