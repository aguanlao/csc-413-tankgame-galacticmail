package TankGame;

import java.awt.Graphics;

public class Tank extends CollidableObject {
    private static final int TANK_HEALTH = 100;
    private static final double BASE_SPEED = 1.0;
    
    private int health;    
    double startX, startY, speed, direction;
    boolean isForward, isBackwards, isLeft, isRight;
    
    public Tank(int x, int y, String image) {
        this(x, y, 0, image);
    } 
    
    public Tank(int x, int y, int direction, String image) {

        super(image);
        
        this.startX = x;
        this.startY = y;
        
        this.x = x;
        this.y = y;
        this.direction = direction % 360;
        health = TANK_HEALTH;
        speed = BASE_SPEED;
        isLive = true;
        direction = 0;
    }
    
    public void addAngle(double a) {
        // prevents out of bounds, allow complete 360 turns
        if (a < 0) {
            a += 360;
        }

        this.direction = (this.direction + a) % 360;
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
        double rads, dx, dy;
        rads = Math.toRadians(this.direction);
        dx = Math.cos(rads) * this.speed;
        dy = Math.sin(rads) * this.speed;

        if (this.isForward && !this.isBackwards) {
            this.x += dx;
            this.y -= dy;
        } else if (this.isBackwards && !this.isForward) {
            this.x -= dx;
            this.y += dy;
        }

        if (this.isLeft && !this.isRight) {
            this.addAngle(this.speed);
        } else if (this.isRight && !this.isLeft) {
            this.addAngle(-this.speed);
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
    }
}
