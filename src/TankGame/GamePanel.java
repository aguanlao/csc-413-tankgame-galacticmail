package TankGame;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements Runnable {
    private static final String BACKGROUND_IMAGE = "resources" + File.separator + "background_tile.png";
    private BufferedImage background;
    private BufferedImage minimap = background;	
    private ArrayList<GameObject> worldObjects;

    public GamePanel() {
        File imageFile = new File(BACKGROUND_IMAGE);
        System.out.println("CWD: " + System.getProperty("user.dir"));
        try {
            background = ImageIO.read(imageFile);
        }
        catch (IOException exception) {
            System.err.println("Error reading background file.");
        }
        
        setFocusable(true);
    }
    
    public void updateObjects(ArrayList<GameObject> objects) {
        worldObjects = objects;
    }
 
    @Override
    public void run() {
    	
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D makeBackground = (Graphics2D) graphics.create();
        int w = background.getWidth();
        int h = background.getHeight();
        for (int y = 0; y < getHeight(); y += h) {
            for (int x = 0; x < getWidth(); x += w) {
                makeBackground.drawImage(background, x, y, this);
            }
        }
      
        for (int i = 0; i < worldObjects.size(); i++) {
            worldObjects.get(i).repaint(graphics);
        }

    }

}
