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
    
    public void addAngle(double a) {
    // prevents out of bounds, allow complete 360 turns
    	if (a < 0) {
    		a += 360;
    	}
    	
    	this.angle = (this.angle + a) % 360;
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
        graphics.drawImage(sprite.getImage((int) angle/6), (int) this.x, (int) this.y, null);
    }
}
