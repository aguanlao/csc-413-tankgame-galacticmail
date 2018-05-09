package tankgame;

import java.awt.Graphics;

public class GameObject {

    protected double x, y;
    protected Sprite sprite;
    
    public GameObject(int x, int y, String fileName) {
        this(x, y, fileName, Sprite.FRAMES);
    }
    
    public GameObject(int x, int y, String fileName, int frameCount) {
        this.x = x;
        this.y = y;
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
    
    public double calculateDistance(GameObject other) {
        return Math.hypot(this.x - other.x, this.y - other.y);
    }
    
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage(0), (int) x, (int) y, null);
    }
}
