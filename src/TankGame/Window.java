package TankGame;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Window extends JFrame implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 1200;
	private static final int WINDOW_HEIGHT = 800;
	
	private final JFrame mainWindow;        
	private BufferedImage background;
	    
	public Window() {
        mainWindow = new JFrame("Tanks");
        initializeFrame();
        
        File test = new File("resources/background_tile.png");
        
        System.out.println("CWD: " + System.getProperty("user.dir"));
        
        try {
            background = ImageIO.read(test);
        }   
        
        catch (IOException e) 
        {
            System.out.println("Error reading background file.");
        }
       
        JLabel label = new JLabel(new ImageIcon(background));
        label.setPreferredSize(new Dimension(100,100));
        mainWindow.getContentPane().add(label);
        mainWindow.repaint();
        
    }
    
    private void initializeFrame() {
    	mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainWindow.setLocationRelativeTo(null);

        JPanel pane = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D make_bg = (Graphics2D) g.create();
                int w = background.getWidth();
                int h = background.getHeight();
                for (int y = 0; y < getHeight(); y += h)
                	for (int x = 0; x < getWidth(); x += w)
                		make_bg.drawImage(background, x, y, this);
            }
        };
        mainWindow.add(pane);
        mainWindow.setVisible(true);
    }
    
    @Override
    public void update(Observable observed, Object arg) {
        //On clock tick, redraw screen
        //System.out.println("Window saw clock tick.");
    }


}