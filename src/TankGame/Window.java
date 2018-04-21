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

//    private static final long serialVersionUID = 1L;
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 800;


    public Window() {
        initializeFrame();
    }
    
    private void initializeFrame() {
        GamePanel panel;
        setTitle("Tanks");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);       
        panel = new GamePanel();
        add(panel);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
    }
    
    @Override
    public void update(Observable observed, Object arg) {
        //On clock tick, redraw screen
        //System.out.println("Window saw clock tick.");
        repaint();
    }


}