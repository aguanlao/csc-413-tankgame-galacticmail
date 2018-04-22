package TankGame;

import java.math.*;
import java.io.IOException;
import java.awt.Graphics;

/*  Direction defined as an angle within range of [0-359]
    inclusive. 0 degrees begins facing directly RIGHT and
    increases going counter-clockwise.
*/

public class Tank extends CollidableObject {
    private static final int TANK_HEALTH = 100;
    private int health;
    
    public Tank(int x, int y, String image) throws IOException{
        super(image);
        
        this.x = x;
        this.y = y;
        health = TANK_HEALTH;
        isLive = true;
        speed = 5;
        angle = 0;
        direction = 0;
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            isLive = false;
        }
    }
    
    @Override
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage((int) angle/6), (int) this.x, (int) this.y, null);
        //this.direction = (this.direction + 1) % sprite.totalFrames;
    }
}
