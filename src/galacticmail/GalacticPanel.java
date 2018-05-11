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
    
    private List<GameObject> worldObjects;
    
    public GalacticPanel() {
        worldObjects = new ArrayList<>();
    }
    
    private void createBackground() {
    }
    
    public void redrawWorldImage() {
    
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        
    }    
}
