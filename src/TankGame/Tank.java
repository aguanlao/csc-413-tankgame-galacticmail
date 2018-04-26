package TankGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Tank extends CollidableObject {
    private static final int HITBOX_TRIM = 10;
    private static final int TANK_HEALTH = 100;

    private static final double BASE_SPEED = 1.0;
    
    private int health;    
    double startX, startY, speed;
    int direction;
    boolean isForward, isBackwards, isLeft, isRight;
    
    public Tank(int x, int y, String image) {
        this(x, y, 0, image);
    } 
    
    public Tank(int x, int y, int direction, String image) {
        super(x, y, image);
        
        this.startX = x;
        this.startY = y;
        
        this.direction = direction % 360;
        health = TANK_HEALTH;
        speed = BASE_SPEED;
        isLive = true;
        direction = 0;
        
        trimHitbox();
    }
    
    private void trimHitbox() {
        hitbox.getXPoints()[0] += HITBOX_TRIM;
        hitbox.getXPoints()[1] -= HITBOX_TRIM;
        hitbox.getXPoints()[2] -= HITBOX_TRIM;
        hitbox.getXPoints()[3] += HITBOX_TRIM;
        
        hitbox.getYPoints()[0] += HITBOX_TRIM;
        hitbox.getYPoints()[1] += HITBOX_TRIM;
        hitbox.getYPoints()[2] -= HITBOX_TRIM;
        hitbox.getYPoints()[3] -= HITBOX_TRIM;
        
        hitbox.updatePoints();
    }
    
    public void addAngle(double angle) {
        // prevents out of bounds, allow complete 360 turns
        if (angle < 0) {
            angle += 360;
        }
        this.direction = (this.direction + (int)angle) % 360;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            isLive = false;
        }
    }

    public void resetPosition() {
        this.x = this.startX;
        this.y = this.startY;
        this.direction = 0;
    }

    public void checkPosition() {
        double oldX, oldY;
        double rads, dx, dy;
        rads = Math.toRadians(this.direction);
        dx = Math.cos(rads) * this.speed;
        dy = Math.sin(rads) * this.speed;            
        oldX = this.x;
        oldY = this.y;

        if (this.isForward && !this.isBackwards) {
            this.x += dx;
            this.y -= dy;
            hitbox.translate(((int)this.x - (int)oldX), ((int)this.y - (int)oldY));

        } else if (this.isBackwards && !this.isForward) {
            this.x -= dx;
            this.y += dy;
            hitbox.translate(((int)this.x - (int)oldX), ((int)this.y - (int)oldY));
        }

        if (this.isLeft && !this.isRight) {
            this.addAngle(this.speed);
            hitbox.rotate(this.direction);
        } else if (this.isRight && !this.isLeft) {
            this.addAngle(-this.speed);
            hitbox.rotate(this.direction);
        }
    }
    
    public void setForward(boolean flag) {
        isForward = flag;
    }
    
    public void setBackwards(boolean flag) {
        isBackwards = flag;
    }
    
    public void setLeft(boolean flag) {
        isLeft = flag;
    }
    
    public void setRight(boolean flag) {
        isRight = flag;
    }
    
    @Override
    public void repaint(Graphics graphics) {
        checkPosition();
        graphics.drawImage(sprite.getImage((int)direction/6), (int) this.x, (int) this.y, null);        
        
        drawHitbox(graphics);
        
        Graphics g2D = (Graphics2D) graphics.create();
        
        g2D.setColor(Color.RED);
        g2D.drawOval((int)this.x, (int)this.y, 3, 3);
        
        
        g2D.dispose();
    }
}
