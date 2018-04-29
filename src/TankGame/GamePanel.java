package TankGame;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String BACKGROUND_IMAGE = "resources" + File.separator + "background_tile.png";
    private static final int MINIMAP_WIDTH = 200;
    private static final int MINIMAP_HEIGHT = 200;
    private static final int VIEW_WIDTH = 600;
    private static final int VIEW_HEIGHT = 600;
    
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
        }
        
        Graphics2D graphics = background.createGraphics();
        int w = backgroundTile.getWidth();
        int h = backgroundTile.getHeight();
        for (int y = 0; y < background.getHeight(); y += h) {
            for (int x = 0; x < background.getWidth(); x += w) {
                graphics.drawImage(backgroundTile, x, y, this);
            }
        }
        graphics.dispose();
    }
    
    public void updateObjects(ArrayList<GameObject> objects, ArrayList<Shot> shotsFired) {
        worldObjects = objects;
        allShots = shotsFired;
    }

    public void redrawWorldImage() {
        Graphics2D graphics = worldImage.createGraphics();
        graphics.drawImage(background, 0, 0, null);
        for (int i = 0; i < worldObjects.size(); i++) {
            worldObjects.get(i).repaint(graphics);
        }
                
        for (int i = 0; i < allShots.size(); i++) {
        	allShots.get(i).repaint(graphics);
        }
        graphics.dispose();
      
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2D = (Graphics2D) graphics.create();
        
        playerOneView = worldImage.getSubimage( (int) (GameWorld.playerOne.getX()/2.5), (int) (GameWorld.playerOne.getY()/2.5), VIEW_WIDTH, VIEW_HEIGHT);
        
        playerTwoView = worldImage.getSubimage( (int) (GameWorld.playerTwo.getX()/2.5), (int) (GameWorld.playerTwo.getY()/2.5), VIEW_WIDTH, VIEW_HEIGHT);
        
        g2D.drawImage(playerOneView, 0, 0, this.getWidth()/2, this.getHeight(), null);
        g2D.drawImage(playerTwoView, this.getWidth()/2, 0, this.getWidth()/2, this.getHeight(), null);
        
        g2D.setColor(Color.BLACK);
        
        g2D.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
        
        g2D.drawImage(worldImage, this.getWidth()/2 - MINIMAP_WIDTH/2, this.getHeight() * 1/2, 
                MINIMAP_WIDTH, MINIMAP_HEIGHT, null);
        
        g2D.drawRect(this.getWidth()/2 - MINIMAP_WIDTH/2, this.getHeight() * 1/2, 
                MINIMAP_WIDTH, MINIMAP_HEIGHT);

        g2D.dispose();
    }

}
