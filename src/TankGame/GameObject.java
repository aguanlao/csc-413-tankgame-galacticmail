package TankGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public class GameObject extends Observable {

    protected double x, y;
    protected Sprite sprite;
    
    public GameObject(String fileName) {
        sprite = new Sprite(fileName);
    }
    
    public GameObject(String fileName, int frameCount) {
        sprite = new Sprite(fileName, frameCount);
    }
    
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public int getHeight() {
    	return sprite.getHeight();
    }
    
    public int getWidth() {
    	return sprite.getWidth();
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage(0), (int)x, (int)y, null);
    }
}
