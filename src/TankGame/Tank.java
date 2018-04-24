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
    private int health, direction;
    
    public Tank(int x, int y, String image) {
        this(x, y, 0, image);
    } 
    
    public Tank(int x, int y, int direction, String image) {
        super(image);
        
        this.x = x;
        this.y = y;
        this.direction = direction % 360;
        health = TANK_HEALTH;
        isLive = true;
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            isLive = false;
        }
    }
    
    public void rotateClockwise() {
        direction = (direction - 6) % 360;
    }
    
    public void rotateCounterclockwise() {
        direction = (direction + 6) % 360;
    }
    
    public void moveForward() {
        double angle = Math.toRadians(direction);
//        double dx, dy;
        
//        dx = Math.cos(angle);
//        dy = -Math.sin(angle);
        
        move(Math.cos(angle), -Math.sin(angle));
    }
    
    public void moveBackward() {
        double angle = Math.toRadians(direction);
//        double dx, dy;
//        
//        dx = -Math.cos(angle);
//        dy = Math.sin(angle);
        
        move(-Math.cos(angle), Math.sin(angle));
    }
    
    public void move(double dx, double dy) {
        x+=dx;
        y+=dy;
    }
    
    @Override
    public void repaint(Graphics graphics) {
        moveForward();
        graphics.drawImage(sprite.getImage(direction / 6), (int)this.x, (int)this.y, null);
        //direction = (int)(direction + 1) % sprite.totalFrames;
    }
}
