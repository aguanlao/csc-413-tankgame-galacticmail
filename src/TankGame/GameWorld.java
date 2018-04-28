package TankGame;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class GameWorld implements Observer, Runnable{

    public static final int WORLD_WIDTH = 900;
    public static final int WORLD_HEIGHT = 900;
    private static final int TANK1_START_X = 200;
    private static final int TANK1_START_Y = 200;
    private static final int TANK2_START_X = 500;
    private static final int TANK2_START_Y = 500;    
    private static final String TANK_IMAGE1 = "resources" + File.separator + "Tank_blue_heavy_strip60.png";
    private static final String TANK_IMAGE2 = "resources" + File.separator + "Tank_red_heavy_strip60.png";
    
    private final TankListener keyListener;
    private final Tank playerOne, playerTwo;
    private final ArrayList<GameObject> objects;
    private ArrayList<Shot> shotsFired;

    public GameWorld(TankListener listener) {
        objects = new ArrayList<GameObject>();
        playerOne = new Tank(TANK1_START_X, TANK1_START_Y, TANK_IMAGE1);
        playerTwo = new Tank(TANK2_START_X, TANK2_START_Y, TANK_IMAGE2);
        keyListener = listener;
        
        keyListener.addTanks(playerOne, playerTwo);
        objects.add(playerOne);
        objects.add(playerTwo);
        buildLevel();
    }
    
    public void createShot(Tank tankThatShot) throws IOException {
    	Point tanksCoord = new Point((int)tankThatShot.getX(), (int)tankThatShot.getY());
    	Shot newShot = new Shot(tanksCoord, tankThatShot.getDirection());
    	shotsFired.add(newShot);
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
    
    //TODO: Might need to create thread for running collision checks to improve performance
    @Override
    public void run() {
        
    }
    
    @Override
    public void update(Observable observed, Object arg) {
        //On clock tick, check collisions, firing
        for(int i = 0; i < objects.size(); i++) {
            if(objects.get(i) instanceof CollidableObject) {
                if(objects.get(i) != playerOne && playerOne.collides((CollidableObject)objects.get(i))) {      
                    System.out.println("Collided with " + objects.get(i).toString());
                    playerOne.setColliding(true);
                }
                
                if(objects.get(i) != playerTwo && playerTwo.collides((CollidableObject)objects.get(i))) {
                    playerTwo.setColliding(true);
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
}
