package TankGame;

public class DestructibleWall extends CollidableObject{
    private static final int BASE_HITPOINTS = 50;
    private int hitpoints;
    
    public DestructibleWall() {
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
