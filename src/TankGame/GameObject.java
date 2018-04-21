package TankGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public class GameObject extends Observable {

    protected int x, y;
    protected Sprite sprite;
    private BufferedImage image;
    
    public GameObject(String input) throws IOException {
    	image = ImageIO.read(new File (input) );
        sprite = new Sprite(image);
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
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
        graphics.drawImage(sprite.getImage(), x, y, null);
    }
}
