package TankGame;

import java.io.IOException;
import java.awt.Graphics;

public class Tank extends CollidableObject {
    private static final int TANK_HEALTH = 100;
    
    private int health;    
    double startX, startY, speed, direction;
    boolean isForward, isBackwards, isLeft, isRight, isReset;
    
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
        isLive = true;
        speed = .25;
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
        if(health <= 0) {
            isLive = false;
        }
    }
    
//<<<<<<< HEAD
//    public void rotateClockwise() {
//        direction = (direction - 6) % 360;
//    }
//    
//    public void rotateCounterclockwise() {
//        direction = (direction + 6) % 360;
//    }
//    
//    public void moveForward() {
//        double angle = Math.toRadians(direction);
////        double dx, dy;
//        
////        dx = Math.cos(angle);
////        dy = -Math.sin(angle);
//        
//        move(Math.cos(angle), -Math.sin(angle));
//    }
//    
//    public void moveBackward() {
//        double angle = Math.toRadians(direction);
////        double dx, dy;
////        
////        dx = -Math.cos(angle);
////        dy = Math.sin(angle);
//        
//        move(-Math.cos(angle), Math.sin(angle));
//    }
//    
//    public void move(double dx, double dy) {
//        x+=dx;
//        y+=dy;
//=======
    public void resetPositions() {
    	this.x = this.startX;
    	this.y = this.startY;
    	this.direction = 0;
    	this.isReset = false;
    }
    
    public void checkPosition() {
    	double rads, dx, dy;
		rads = Math.toRadians(this.direction);
		dx = Math.cos(rads) * this.speed;
		dy = Math.sin(rads) * this.speed;
		
    	if (this.isForward)
    	{
    		this.x += dx;
    		this.y -= dy;
    	}
    	
    	else if (this.isBackwards)
    	{
    		this.x -= dx;
    		this.y += dy;
    	}
    	
    	if (this.isLeft)
    	{
    		this.addAngle(this.speed);
    	}
    	
    	else if (this.isRight)
    	{
    		this.addAngle(-this.speed);
    	}
    	
    	if (this.isReset)
    		resetPositions();
    }
    
    @Override
    public void repaint(Graphics graphics) {
        checkPosition();
        graphics.drawImage(sprite.getImage((int)direction/6), (int) this.x, (int) this.y, null);
    }
}
