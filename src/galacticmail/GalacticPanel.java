package galacticmail;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GalacticPanel extends JPanel{
    private static final int VIEW_WIDTH = 1000;
    private static final int VIEW_HEIGHT = 600;
    private static final String BACKGROUND_IMAGE = "galacticmail" + File.separator 
            + "resources" + File.separator + "Background.png";
    
    private final GalacticWorld world;
    private BufferedImage background;
    private JLabel header, score;
    
    public GalacticPanel(GalacticWorld world) {        
        this.world = world;
        try {
            background = ImageIO.read(new File(BACKGROUND_IMAGE));
        } catch (IOException exception) {
            System.err.println("Error reading background file.");
            exception.printStackTrace();
        }
        createEndScreen();
    }
    
    public void displayTitleScreen() {
    }
    
    private void createEndScreen() {
        header = new JLabel("Game Over! ");
        score = new JLabel("");
        Font headerFont = new Font("SansSerif", Font.PLAIN, 36);
        
        
        header.setVerticalAlignment(JLabel.TOP);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setForeground(Color.WHITE);
        header.setFont(headerFont);
        header.setOpaque(false);
        header.setSize(GalacticWindow.WINDOW_WIDTH, GalacticWindow.WINDOW_HEIGHT);
        score.setLocation(GalacticWindow.WINDOW_WIDTH/2, GalacticWindow.WINDOW_HEIGHT/2);
        score.setForeground(Color.WHITE);
        score.setFont(new Font("SansSerif", Font.PLAIN, 24));
        score.setOpaque(false);
        
        header.setVisible(false);
        score.setVisible(false);
        this.add(header);
        this.add(score);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2D = (Graphics2D)graphics.create();
        
        graphics.drawImage(background, 0, 0, VIEW_WIDTH, VIEW_HEIGHT, null);
        for(int i = 0; i < world.getAsteroids().size(); i++) {
            world.getAsteroids().get(i).repaint(g2D);
        }
        for(int i = 0; i < world.getObjects().size(); i++) {
            world.getObjects().get(i).repaint(g2D);
        }
        
        if(world.getShip().isLiveNow()) {
            world.getShip().repaint(g2D);
        }
        
        if(world.getEndScreenState()) {
            String finalScore = "Final score: " + world.getScore(); 
            score.setText(finalScore);
            header.setVisible(true);
            score.setVisible(true);
        }
        
        g2D.dispose();        
    }    
}
