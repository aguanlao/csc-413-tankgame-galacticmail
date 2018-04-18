package TankGame;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
//import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Window extends JFrame implements Observer{
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
            }   catch   (IOException e) {
                System.out.println("Error reading background file.");
//            }
//            JLabel label = new JLabel(new ImageIcon(background));
//            mainWindow.add(label);
//            mainWindow.repaint();
//            
    
            }
            JLabel label = new JLabel(new ImageIcon(background));
            label.setPreferredSize(new Dimension(100,100));
            mainWindow.getContentPane().add(label);
            mainWindow.repaint();
            
        }
        
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            System.out.println("Drawing image.");
            
            g2.drawImage(background, 300, 300, null);
            g2.drawString("Testing drawing", 100, 50);
        }
        
        private void initializeFrame() {
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            mainWindow.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setOpaque(true);
            panel.setBackground(new Color(255,255,50));
            panel.setPreferredSize(new Dimension(100,200));
            mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
            
            mainWindow.setVisible(true);
        }
        
        @Override
        public void update(Observable observed, Object arg) {
            //On clock tick, redraw screen
            //System.out.println("Window saw clock tick.");
        }
	

}
