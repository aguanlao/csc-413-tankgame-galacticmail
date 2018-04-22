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
    
    public void setX(int x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public double getDir() {
        return direction;
    }
    
    public void setDir(double d) {
        this.direction = d;
    }
    
    public void setAngle(double a) {
    	if(a < 0) {
    		a += 360;
    	}
    	this.angle = a % 360;
    }
    
    public double getAngle() {
    	return angle;
    }
    
    public void setSpeed(int s) {
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
