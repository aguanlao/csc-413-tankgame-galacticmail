package galacticmail;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class GalacticWindow extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	
	private static final String BACKGROUND_IMAGE = "galacticgame" + File.separator + "resources" + File.separator + "Background.png";
	private BufferedImage background, worldView;
	
	public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;
    private static final int REFRESH_DELAY = 75;
    
    private final GalacticWorld world;
    public GalacticWindow(GalacticWorld world) {
        this.world = world;
        //initializeFrame();
    }
    
    public void paintComponent(Graphics graphics) {
    	
    }
    
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
