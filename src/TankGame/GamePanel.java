package TankGame;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements Runnable {
    private static final String BACKGROUND_IMAGE = "resources" + File.separator + "background_tile.png";
    private static final String TANK_IMAGE = "resources" + File.separator + "Tank_grey_basic.png";
    private static final long serialVersionUID = 1L;

    private BufferedImage background;
    private Tank tank;
    private Shot shot;
    public GamePanel() {
        File imageFile = new File(BACKGROUND_IMAGE);
        System.out.println("CWD: " + System.getProperty("user.dir"));
        try {
            background = ImageIO.read(imageFile);
        }
        catch (IOException exception) {
            System.err.println("Error reading background file.");
        }        
        try {
            tank = new Tank(30, 30, TANK_IMAGE);
            shot = new Shot(new Point(50,50), 0);
        }
        catch (IOException exception) {
            System.err.println("Failed to create tank or shot.");
        }
    }

    @Override
    public void run() {
    }

    @Override
    public void paintComponent(Graphics graphics) {
        System.out.println("Painting GamePanel!");
        super.paintComponent(graphics);
        Graphics2D makeBackground = (Graphics2D) graphics.create();
        int w = background.getWidth();
        int h = background.getHeight();
        for (int y = 0; y < getHeight(); y += h) {
            for (int x = 0; x < getWidth(); x += w) {
                makeBackground.drawImage(background, x, y, this);
            }
        }
        
        tank.repaint(graphics);
        shot.repaint(graphics);
    }

}
