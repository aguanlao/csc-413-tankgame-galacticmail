package galacticmail;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;

import common.GameObject;

public class GalacticPanel extends JPanel{
    private static final int VIEW_WIDTH = 800;
    private static final int VIEW_HEIGHT = 800;
    
    private final GalacticWorld world;
    
    Asteroid a;
    
    public GalacticPanel(GalacticWorld world) {
        this.world = world;
        a = new Asteroid(50, 50);
    }
    
    private void createBackground() {
    }
    
    public void redrawWorldImage() {
    
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2D = (Graphics2D)graphics.create();
        for(int i = 0; i < world.getAsteroids().size(); i++) {
            world.getAsteroids().get(i).repaint(g2D);
        }
        a.repaint(g2D);
        g2D.dispose();
        
    }    
}
