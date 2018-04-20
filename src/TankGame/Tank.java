package TankGame;

import java.math.*;
import java.io.IOException;

/*  Direction defined as an angle within range of [0-359]
    inclusive. 0 degrees begins facing directly RIGHT and
    increases going counter-clockwise.
*/

public class Tank extends CollidableObject {
    private static final int TANK_HEALTH = 100;
    private float direction;
    private int speed;
    private int health;
    
    public Tank(int x, int y, String image) throws IOException{
        super(image);
        this.x = x;
        this.y = y;
        health = TANK_HEALTH;
        direction = 0;
        speed = 0;
        isLive = true;
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            isLive = false;
        }
    }
}
