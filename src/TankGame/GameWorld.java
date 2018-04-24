package TankGame;

import java.io.*;
import java.util.*; 

public class GameWorld implements Observer{
    private static final int WORLD_LENGTH = 3000;
    private static final int WORLD_HEIGHT = 3000;
    private static final int TANK1_START_X = 50;
    private static final int TANK1_START_Y = 50;
    private static final int TANK2_START_X = 500;
    private static final int TANK2_START_Y = 500;    
    private static final String TANK_IMAGE1 = "resources" + File.separator + "Tank_blue_heavy_strip60.png";
    private static final String TANK_IMAGE2 = "resources" + File.separator + "Tank_red_base_strip60.png";
    
    private final Tank playerOne, playerTwo;
    private ArrayList<GameObject> objects;
    private ArrayList<IndestructibleWall> walls;
    
    public GameWorld() {
        objects = new ArrayList<GameObject>();
        walls = new ArrayList<IndestructibleWall>();
        playerOne = new Tank(TANK1_START_X, TANK1_START_Y, TANK_IMAGE1);
        playerTwo = new Tank(TANK2_START_X, TANK2_START_Y, TANK_IMAGE2);
        
        objects.add(playerOne);
        objects.add(playerTwo);
        buildLevel();
    }
    
    private void buildLevel() {
        
    }
    
    @Override
    public void update(Observable observed, Object arg) {

    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }
}
