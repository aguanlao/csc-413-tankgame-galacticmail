package TankGame;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Window extends JFrame implements Observer{
        private static final int WINDOW_WIDTH = 1200;
        private static final int WINDOW_HEIGHT = 800;
        private final JFrame mainWindow;        
	
	public Window() {
            mainWindow = new JFrame("Tanks");
            initializeFrame();
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
            System.out.println("Window saw clock tick.");
        }
	

}
