package tankgame;

import java.io.File;

import common.CollidableObject;

public class DestructibleWall extends CollidableObject{
    private static final String WALL_IMAGE = "resources" + File.separator + "wall.png"; 
    public static final int IMAGE_LENGTH = 32;
    private static final int BASE_HITPOINTS = 10;
    
    protected int health;
    
    public DestructibleWall(int x, int y) {
        super(x, y, WALL_IMAGE, 1);
        health = BASE_HITPOINTS;
        isLive = true;
    }
    
    public void tookDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            isLive = false;
        }
    }

    public int getHealth() {
    	return health;
    }
}
