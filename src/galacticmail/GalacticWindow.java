package galacticmail;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

import common.GameClock;

public class GalacticWindow extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;
    private static final int REFRESH_DELAY = 150;

    private final GalacticWorld world;
    private GalacticPanel panel;

    public GalacticWindow(GalacticWorld world) {
        this.world = world;
        initializeFrame();
    }
    
    private void initializeFrame() {
        setTitle("Galactic Mail");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);       
        panel = new GalacticPanel(world);
        panel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        add(panel);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void update(Observable observed, Object arg) {
        if(((GameClock)observed).getFrame() % REFRESH_DELAY == 0) {
            panel.repaint();
            setTitle("Galactic Mail | Score: " + world.getScore());
        }
    }

}
