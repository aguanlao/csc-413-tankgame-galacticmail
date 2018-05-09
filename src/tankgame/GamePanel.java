package tankgame;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
    private static final String BACKGROUND_IMAGE = "resources" + File.separator + "background_tile.png";
    private static final int MINIMAP_WIDTH = 200;
    private static final int MINIMAP_HEIGHT = 200;
    private static final int VIEW_WIDTH = 800;
    private static final int VIEW_HEIGHT = 800;
    private static final int BAR_MARGIN = 50;
    private static final int BAR_LENGTH = 200;
    private static final int BAR_HEIGHT = 25;
    
    private BufferedImage backgroundTile, worldImage, background, playerOneView, playerTwoView;
    private ArrayList<GameObject> worldObjects;
    private ArrayList<Shot> allShots;
    private ArrayList<Tank> players;
    
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
    
    public void updateObjects(ArrayList<GameObject> objects, ArrayList<Shot> shotsFired, ArrayList<Tank> tanks) {
        worldObjects = objects;
        allShots = shotsFired;
        players = tanks;
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
    
    void drawPlayerViews() {
        int viewOneX, viewOneY, viewTwoX, viewTwoY;
        Tank playerOne = players.get(0);
        Tank playerTwo = players.get(1);
        
        if(playerOne.getX() <= VIEW_WIDTH/2) {
            viewOneX = 0;
        }
        else if(playerOne.getX() >= GameWorld.WORLD_WIDTH - VIEW_WIDTH/2) {
            viewOneX = GameWorld.WORLD_WIDTH - VIEW_WIDTH;
        }
        else {
            viewOneX = (int)playerOne.getX() - VIEW_WIDTH/2;
        }
        
        if(playerOne.getY() <= VIEW_HEIGHT/2) {
            viewOneY = 0;
        }
        else if(playerOne.getY() >= GameWorld.WORLD_HEIGHT - VIEW_HEIGHT/2) {
            viewOneY = GameWorld.WORLD_HEIGHT - VIEW_HEIGHT;
        }
        else {
            viewOneY = (int)playerOne.getY() - VIEW_HEIGHT/2;
        }
        
        if(playerTwo.getX() <= VIEW_WIDTH/2) {
            viewTwoX = 0;
        }
        else if(playerTwo.getX() >= GameWorld.WORLD_WIDTH - VIEW_WIDTH/2) {
            viewTwoX = GameWorld.WORLD_WIDTH - VIEW_WIDTH;
        }
        else {
            viewTwoX = (int)playerTwo.getX() - VIEW_WIDTH/2;
        }
        
        if(playerTwo.getY() <= VIEW_HEIGHT/2) {
            viewTwoY = 0;
        }
        else if(playerTwo.getY() >= GameWorld.WORLD_HEIGHT - VIEW_HEIGHT/2) {
            viewTwoY = GameWorld.WORLD_HEIGHT - VIEW_HEIGHT;
        }
        else {
            viewTwoY = (int)playerTwo.getY() - VIEW_HEIGHT/2;
        }               

        playerOneView = worldImage.getSubimage(viewOneX, viewOneY, VIEW_WIDTH, VIEW_HEIGHT);
        playerTwoView = worldImage.getSubimage(viewTwoX, viewTwoY, VIEW_WIDTH, VIEW_HEIGHT);
    }
    
    private void drawHealthbars(Graphics2D graphics) {
        Rectangle healthBarOne, healthBarTwo;
        healthBarOne = new Rectangle(BAR_MARGIN, this.getHeight() - BAR_MARGIN, 
                players.get(0).getHealth() * 2, BAR_HEIGHT);
        healthBarTwo = new Rectangle(this.getWidth() - BAR_MARGIN - BAR_LENGTH, 
                this.getHeight() - BAR_MARGIN, players.get(1).getHealth() * 2, BAR_HEIGHT);
        
        graphics.setColor(Color.GREEN);
        graphics.fill(healthBarOne);
        graphics.fill(healthBarTwo);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(BAR_MARGIN, this.getHeight() - BAR_MARGIN,
                BAR_LENGTH, BAR_HEIGHT);        
        graphics.drawRect(this.getWidth() - BAR_MARGIN - BAR_LENGTH,
                this.getHeight() - BAR_MARGIN, BAR_LENGTH, BAR_HEIGHT);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2D = (Graphics2D) graphics.create();
        
        drawPlayerViews();
        g2D.drawImage(playerOneView, 0, 0, this.getWidth()/2, this.getHeight(), null);
        g2D.drawImage(playerTwoView, this.getWidth()/2, 0, this.getWidth()/2, this.getHeight(), null);
        g2D.setColor(Color.BLACK);
        g2D.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
        g2D.drawImage(worldImage, this.getWidth()/2 - MINIMAP_WIDTH/2, this.getHeight() * 1/2, 
                MINIMAP_WIDTH, MINIMAP_HEIGHT, null);
        g2D.drawRect(this.getWidth()/2 - MINIMAP_WIDTH/2, this.getHeight() * 1/2, 
                MINIMAP_WIDTH, MINIMAP_HEIGHT);
        
        drawHealthbars(g2D);

        g2D.dispose();
    }

    
    public void displayWinnerScreen () {
    	
        String winner = "";
        if (GameWorld.player1Won) {
            winner = "Player 1";
        } else if (GameWorld.player2Won) {
            winner = "Player 2";
        }

    	JLabel winScreen = new JLabel(winner + " wins!");
    	winScreen.setVerticalTextPosition(JLabel.CENTER);
    	winScreen.setHorizontalTextPosition(JLabel.CENTER);
    	winScreen.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        winScreen.setBackground(Color.BLACK);
        winScreen.setForeground(Color.WHITE);
        winScreen.setFont(new Font("SansSerif", Font.PLAIN, 48));
        winScreen.setOpaque(true);
        winScreen.setHorizontalAlignment(JLabel.CENTER);
    	winScreen.setVisible(true);
    	this.add(winScreen);
    	
    }
}
