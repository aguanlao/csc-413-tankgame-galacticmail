package TankGame;

import java.io.IOException;
import java.awt.Graphics;

public class Tank extends CollidableObject {
    private static final int TANK_HEALTH = 100;
    private int health;
    double startX, startY;
    
	boolean isForward, isBackwards, isLeft, isRight, isReset;
	
    public Tank(double x, double y, String image) throws IOException{
        super(image);
        
        this.startX = x;
        this.startY = y;
        
        this.x = x;
        this.y = y;
        health = TANK_HEALTH;
        isLive = true;
        speed = .25;
        angle = 0;
        direction = 0;
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            isLive = false;
        }
    }
    
    public void resetPositions() {
    	this.x = this.startX;
    	this.y = this.startY;
    	this.angle = 0;
    	this.isReset = false;
    }
    
    public void checkPosition() {
    	double rads, dx, dy;
		rads = Math.toRadians(this.angle);
		dx = Math.cos(rads) * this.speed;
		dy = Math.sin(rads) * this.speed;
		
    	if (this.isForward)
    	{
    		this.x += dx;
    		System.out.println("X: " + this.x);
    		this.y -= dy;
    		System.out.println("Y: " + this.y);
    	}
    	
    	else if (this.isBackwards)
    	{
    		this.x -= dx;
    		System.out.println("X: " + this.x);
    		this.y += dy;
    		System.out.println("Y: " + this.y);
    	}
    	
    	if (this.isLeft)
    	{
    		this.setAngle(this.angle + .25);
    		System.out.println("Angle: " + this.angle);
    	}
    	
    	else if (this.isRight)
    	{
    		this.setAngle(this.angle - .25);
    		System.out.println("Angle: " + this.angle);
    	}
    	
    	if (this.isReset)
    		resetPositions();
    }
    
    @Override
    public void repaint(Graphics graphics) {
		checkPosition();
        graphics.drawImage(sprite.getImage((int) angle/6), (int) this.x, (int) this.y, null);
    }
}
