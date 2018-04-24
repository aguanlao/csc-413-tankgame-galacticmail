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
