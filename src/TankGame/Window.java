package TankGame;

import static TankGame.GameWorld.isGameOver;
import java.util.*;
import javax.swing.*;

public class Window extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    private static final int REFRESH_DELAY = 15;
    
    private final GameWorld world;
    public GamePanel panel;
    public Window(GameWorld world) {
        this.world = world;
        initializeFrame();
    }
    
    private void initializeFrame() {
        
        setTitle("Tanks");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);       
        panel = new GamePanel();
        panel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        add(panel);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
    }
    
    @Override
    public void update(Observable observed, Object arg) {
        //On clock tick, update list of objects, redraw screen
        panel.updateObjects((ArrayList)world.getObjects(), (ArrayList)world.getShots(), (ArrayList)world.getTanks());
        
        if(((GameClock)observed).getFrame() % REFRESH_DELAY == 0) {
            panel.redrawWorldImage();
            panel.repaint();
        }
        
        if(isGameOver) {
            panel.displayWinnerScreen();
        }
    }
}