package TankGame;

import java.io.IOException;
import java.awt.Graphics;

public class Tank extends CollidableObject {
    private static final int TANK_HEALTH = 100;
    private int health;
    
    public Tank(double x, double y, String image) throws IOException{
        super(image);
        
        this.x = x;
        this.y = y;
        health = TANK_HEALTH;
        isLive = true;
        speed = 1;
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
    }
}
