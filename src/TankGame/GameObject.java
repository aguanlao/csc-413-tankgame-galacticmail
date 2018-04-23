package TankGame;

import java.io.IOException;
import java.util.Observable;
import java.awt.Graphics;

public class GameObject extends Observable {

    protected double x, y, speed, angle;
    protected double direction;
    protected Sprite sprite;
    
    public GameObject(String fileName) throws IOException {

        sprite = new Sprite(fileName);
    }
    
    public double getX() {
        return x;
    }
    
    public void addX(double x) {
        this.x = x;
        if (this.x > 800)
        	this.x = 800;
    }
    
    public double getY() {
        return y;
    }
    
    public void addY(double y) {
        this.y = y;
        if (this.y > 1200)
        	this.y = 1200;
    }
    
    public double getDir() {
        return direction;
    }
    
    public void setDir(double d) {
        this.direction = d;
    }
    
    public void setAngle(double a) {
    // prevents out of bounds, allow complete 360 turns
    	if (a < 0) {
    		a += 360;
    	}
    	this.angle = a % 360;
    }
    
    public double getAngle() {
    	return angle;
    }
    
    public void setSpeed(double s) {
    	this.speed = s;
    }
    
    public double getSpeed() {
    	return speed;
    }
    
    public int getHeight() {
    	return 0;
    }
    
    public int getWidth() {
    	return 0;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage(0), (int) x, (int) y, null);
    }
}
