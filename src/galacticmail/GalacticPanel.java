package galacticmail;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import common.GameObject;

public class GalacticPanel extends JPanel{
    private static final int VIEW_WIDTH = 1200;
    private static final int VIEW_HEIGHT = 800;
    private static final String BACKGROUND_IMAGE = "galacticmail" + File.separator 
            + "resources" + File.separator + "Background.png";
    
    private final GalacticWorld world;
    private BufferedImage background;
    
    Asteroid a;
    
    public GalacticPanel(GalacticWorld world) {
        int width = GalacticWorld.WORLD_WIDTH;
        int height = GalacticWorld.WORLD_HEIGHT;
        
        this.world = world;
        try {
            background = ImageIO.read(new File(BACKGROUND_IMAGE));
        } catch (IOException exception) {
            System.err.println("Error reading background file.");
            exception.printStackTrace();
        }
        
        a = new Asteroid(50, 50);
    }
    
    public void redrawWorldImage() {
    
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2D = (Graphics2D)graphics.create();
        graphics.drawImage(background, 0, 0, VIEW_WIDTH, VIEW_HEIGHT, null);
        for(int i = 0; i < world.getAsteroids().size(); i++) {
            world.getAsteroids().get(i).repaint(g2D);
        }
        a.repaint(g2D);
        g2D.dispose();
        
    }    
}
