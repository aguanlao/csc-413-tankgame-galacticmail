package galacticmail;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GalacticListener implements KeyListener {
	private Ship playerShip;
	
	public void addShip(Ship ship) {
		playerShip = ship;
	}
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        // Tank One Controls
        // W moves the tank towards the direction it is facing
        // S backwards
        // A/D to steer left/right
        case KeyEvent.VK_W:
            playerShip.setForward(true);
            break;

        case KeyEvent.VK_A:
        	playerShip.setLeft(true);
            break;

        case KeyEvent.VK_D:
        	playerShip.setRight(true);
            break;

        case KeyEvent.VK_SPACE:
        	playerShip.setShooting(true);
        	break;
        	
    	default:
    		break;
        }
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
        	playerShip.setForward(false);
            break;

        case KeyEvent.VK_A:
        	playerShip.setLeft(false);
            break;

        case KeyEvent.VK_D:
        	playerShip.setRight(false);
            break;

        case KeyEvent.VK_SPACE:
        	playerShip.setShooting(false);
        	break;
        	
        default:
        	break;
        }
	}

	@Override
	public synchronized void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
