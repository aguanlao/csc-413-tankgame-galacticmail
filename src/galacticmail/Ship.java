package galacticmail;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

import common.CollidableObject;

public class Ship extends CollidableObject {
    private static final String SHIP_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Flying_strip72.png";  
	
    private double startX, startY, speed, lastX, lastY, turnSpeed;
    private int startDirection, direction, lastDirection;
    private boolean isForward, isBackwards, isLeft, isRight, isColliding, isShooting;
    
    private final int HitBoxTrim = 9;
    
	public Ship(int x, int y) {

		super(x, y, SHIP_IMAGE);
	}

	public Ship(int x, int y, int direction) {
        super(x, y, SHIP_IMAGE);
        
        this.startX = x;
        this.startY = y;
        
        this.direction = direction % 360;
        startDirection = this.direction;
        
        speed = 1;
        turnSpeed = 1;
        isLive = true;
        
        trimHitbox(HitBoxTrim, -HitBoxTrim+2, HitBoxTrim, -HitBoxTrim-1);
    }
	
    public void addAngle(double angle) {
        // prevents out of bounds, allow complete 360 turns
        if (angle < 0) {
            angle += 360;
        }
        this.direction = (this.direction + (int)angle) % 360;
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
    
    public void setColliding(boolean flag) {
        isColliding = flag;
    }
    
    public void setShooting(boolean flag) {
    	isShooting = flag;
    }
    
    public boolean getShootState() {
    	return isShooting;
    }
    
    public int getDirection() {
    	return this.direction;
    }
    
    public Point getHitboxCenter() {
        return hitbox.getCenter();
    }
    
    public void setLanding(CollidableObject obj) {
    	// set player's x, y, speed, and direction the same as the base obj
    	
    }

    public void checkPosition() {
        double oldX, oldY;
        double rads, dx, dy;
        rads = Math.toRadians(this.direction);
        dx = Math.cos(rads) * this.speed;
        dy = Math.sin(rads) * this.speed;            
        oldX = this.x;
        oldY = this.y;
        
        if(!this.isColliding) {
            lastX = this.x;
            lastY = this.y;
            lastDirection = this.direction;
            
            if (this.isForward && !this.isBackwards) {
                this.x += 1.2*dx;
                this.y -= 1.2*dy;
                hitbox.translate(((int)this.x - (int)oldX), ((int)this.y - (int)oldY));
            } 
            else if (this.isBackwards && !this.isForward) {
                this.x -= 1.2*dx;
                this.y += 1.2*dy;
                hitbox.translate(((int)this.x - (int)oldX), ((int)this.y - (int)oldY));
            }

            if (this.isLeft && !this.isRight) {
                this.addAngle(this.turnSpeed);
                hitbox.rotate(this.direction);
            }
            else if (this.isRight && !this.isLeft) {
                this.addAngle(-this.turnSpeed);
                hitbox.rotate(this.direction);
            }
        }
        else {
            this.x = lastX;
            this.y = lastY;
            this.direction = lastDirection;
            hitbox.translate(((int)this.x - (int)oldX), ((int)this.y - (int)oldY));
            hitbox.rotate(this.direction);
            setColliding(false);
        }
    }
    
    @Override
    public void repaint(Graphics graphics) {
        checkPosition();
        graphics.drawImage(sprite.getImage((int)direction/6), (int) this.x, (int) this.y, null);     
    }
}
