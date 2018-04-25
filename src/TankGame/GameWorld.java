package TankGame;

import java.io.*;
import java.util.*; 

public class GameWorld implements Observer{
    private static final int WORLD_WIDTH = 1600;
    private static final int WORLD_HEIGHT = 1600;
    private static final int TANK1_START_X = 200;
    private static final int TANK1_START_Y = 200;
    private static final int TANK2_START_X = 500;
    private static final int TANK2_START_Y = 500;    
    private static final String TANK_IMAGE1 = "resources" + File.separator + "Tank_blue_heavy_strip60.png";
    private static final String TANK_IMAGE2 = "resources" + File.separator + "Tank_red_base_strip60.png";
    
    private final TankListener keyListener;
    private final Tank playerOne, playerTwo;
    private ArrayList<GameObject> objects;
    
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
    
    private void buildLevel() {
        //TODO: Create indestructible walls of border, level
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
    
    @Override
    public void update(Observable observed, Object arg) {
        
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }
}
