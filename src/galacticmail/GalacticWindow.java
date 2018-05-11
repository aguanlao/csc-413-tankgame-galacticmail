package galacticmail;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class GalacticWindow extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    private static final int REFRESH_DELAY = 75;
    
    private final GalacticWorld world;
    public GalacticWindow(GalacticWorld world) {
        this.world = world;
        //initializeFrame();
    }
    
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
