package TankGame;

import java.io.File;

public class DestructibleWall extends CollidableObject{
    private static final String WALL_IMAGE = "resources" + File.separator + "wall.png";
    private static final int BASE_HITPOINTS = 50;
    private int hitpoints;
    
    public DestructibleWall(int x, int y) {
        super(x, y, WALL_IMAGE, 1);
        hitpoints = BASE_HITPOINTS;
        isLive = true;
    }
    
    public void takeDamage(int damage) {
        hitpoints -= damage;
        if(hitpoints <= 0) {
            isLive = false;
        }
    }
    
}
