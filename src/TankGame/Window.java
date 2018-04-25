package TankGame;

import java.util.*;
import javax.swing.*;

public class Window extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 800;
    
    private final GameWorld world;
    public GamePanel panel;
    public Window(GameWorld world) {
        initializeFrame();
        this.world = world;
    }
    
    private void initializeFrame() {
        
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
        //On clock tick, update list of objects, redraw screen
        panel.updateObjects(world.getObjects());
        repaint();
    }
}