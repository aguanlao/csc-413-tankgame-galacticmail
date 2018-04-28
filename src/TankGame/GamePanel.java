package TankGame;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
    private static final String BACKGROUND_IMAGE = "resources" + File.separator + "background_tile.png";
    private static final int MINIMAP_WIDTH = 250;
    private static final int MINIMAP_HEIGHT = 250;
    private static final int VIEW_WIDTH = 400;
    private static final int VIEW_HEIGHT = 400;
    private static final int BAR_MARGIN = 50;
    private static final int BAR_LENGTH = 200;
    
    private BufferedImage backgroundTile, worldImage, background, playerOneView, playerTwoView;
    private ArrayList<GameObject> worldObjects;
    private ArrayList<Shot> allShots;
    
    public GamePanel() {
        int width = GameWorld.WORLD_WIDTH;
        int height = GameWorld.WORLD_HEIGHT;

        background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);        
        worldImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);        
        createBackground();
        setFocusable(true);
    }
    
    private void createBackground() {
        File imageFile = new File(BACKGROUND_IMAGE);
        System.out.println("CWD: " + System.getProperty("user.dir"));
        try {
            backgroundTile = ImageIO.read(imageFile);
        }
        catch (IOException exception) {
            System.err.println("Error reading background file.");
            exception.printStackTrace();
        }
        
        Graphics2D graphics = background.createGraphics();
        int w = backgroundTile.getWidth();
        int h = backgroundTile.getHeight();
        for (int y = 0; y < background.getWidth(); y += h) {
            for (int x = 0; x < background.getHeight(); x += w) {
                graphics.drawImage(backgroundTile, x, y, this);
            }
        }
        graphics.dispose();
    }
    
    //TODO: Change how objects transferred to panel
    public void updateObjects(ArrayList<GameObject> objects, ArrayList<Shot> shotsFired) {
        worldObjects = objects;
        allShots = shotsFired;
    }

    public void redrawWorldImage() {
        Graphics2D graphics = worldImage.createGraphics();
        graphics.drawImage(background, 0, 0, null);        
        
        
        for (int i = 0; i < allShots.size(); i++) {
        	allShots.get(i).repaint(graphics);
        }
        for (int i = 0; i < worldObjects.size(); i++) {
            worldObjects.get(i).repaint(graphics);
        }
                

        graphics.dispose();
      
    }
    
    private void drawHealthbars(Graphics2D graphics) {
        Rectangle healthBarOne, healthBarTwo, barOutlineOne, barOutlineTwo;
        healthBarOne = new Rectangle(BAR_MARGIN, this.getHeight() - BAR_MARGIN, 200, 25);
        healthBarTwo = new Rectangle(this.getWidth() - BAR_MARGIN - 200, this.getHeight() - BAR_MARGIN, 200, 25);
        barOutlineOne = healthBarOne;
        barOutlineTwo = healthBarTwo;
        
        graphics.setColor(Color.GREEN);
        graphics.fill(healthBarOne);
        graphics.fill(healthBarTwo);
        graphics.setColor(Color.BLACK);
        graphics.draw(barOutlineOne);
        graphics.draw(barOutlineTwo);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2D = (Graphics2D) graphics.create();

        playerOneView = worldImage.getSubimage(0, 0, VIEW_WIDTH, VIEW_HEIGHT);
        playerTwoView = worldImage.getSubimage(500, 500, VIEW_WIDTH, VIEW_HEIGHT);
        g2D.drawImage(playerOneView, 0, 0, this.getWidth()/2, this.getHeight(), null);
        g2D.drawImage(playerTwoView, this.getWidth()/2, 0, this.getWidth()/2, this.getHeight(), null);
        g2D.setColor(Color.BLACK);
        g2D.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
        g2D.drawImage(worldImage, this.getWidth()/2 - MINIMAP_WIDTH/2, this.getHeight() * 2/3, 
                MINIMAP_WIDTH, MINIMAP_HEIGHT, null);
        g2D.drawRect(this.getWidth()/2 - MINIMAP_WIDTH/2, this.getHeight() * 2/3, 
                MINIMAP_WIDTH, MINIMAP_HEIGHT);
        
        drawHealthbars(g2D);

        g2D.dispose();
    }

}
