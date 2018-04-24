package TankGame;

import java.math.*;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*  Direction defined as an angle within range of [0-359]
    inclusive. 0 degrees begins facing directly RIGHT and
    increases going counter-clockwise.
*/

public class Tank extends CollidableObject {
    private static final int TANK_HEALTH = 100;
    private int health;
    
    public Tank(int x, int y, String image) {
        super(image);
        
        this.x = x;
        this.y = y;
        health = TANK_HEALTH;
        isLive = true;
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            isLive = false;
        }
    }
    
    @Override
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage((int)direction), this.x, this.y, null);
        this.direction = (this.direction + 1) % sprite.totalFrames;
    }
}
