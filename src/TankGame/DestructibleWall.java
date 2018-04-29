package TankGame;

import java.io.File;

public class DestructibleWall extends CollidableObject{
    private static final String WALL_IMAGE = "resources" + File.separator + "wall.png"; 
    public static final int IMAGE_LENGTH = 32;
    private static final int BASE_HITPOINTS = 50;
    
    private int hitpoints;
    
    public DestructibleWall(int x, int y) {
        super(x, y, WALL_IMAGE, 1);
        hitpoints = BASE_HITPOINTS;
        isLive = true;
    }
    
    public void tookDamage(int damage) {
        hitpoints -= damage;
        if(hitpoints <= 0) {
            isLive = false;
        }
    }
    
    public int getHP() {
        if(isLive) {
            return hitpoints;
        }
        return 0;
    }    
}
