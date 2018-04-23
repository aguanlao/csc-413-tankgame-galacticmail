package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class TankListener extends GamePanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private static Set<KeyEvent> doList;
	
	public TankListener() {
		initialize();
	}
	
	public void initialize() {
		doList = new HashSet<KeyEvent>();
	}
	
	public void doActions(KeyEvent e) {
		double rads, dx, dy;
		double rads2, dx2, dy2;
		
		switch ( e.getKeyCode() )
		{
			// Tank One Controls
			// W moves the tank towards the direction it is facing
			// S backwards
			// A/D to steer left/right
			case KeyEvent.VK_W:
				rads = Math.toRadians(GamePanel.tankOne.angle);
				dx = Math.cos(rads) * 1;
				dy = Math.sin(rads) * 1;
				
				GamePanel.tankOne.x += dx;
				GamePanel.tankOne.y -= dy;
				System.out.println("W");
				break;
				
			case KeyEvent.VK_A:
				GamePanel.tankOne.setAngle(GamePanel.tankOne.angle + 3);
				System.out.println("A");
				break;
			
			case KeyEvent.VK_S:
				rads = Math.toRadians(GamePanel.tankOne.angle);
				dx = Math.cos(rads) * 1;
				dy = Math.sin(rads) * 1;
				
				GamePanel.tankOne.x -= dx;
				GamePanel.tankOne.y += dy;
				System.out.println("S");
				break;
			
			case KeyEvent.VK_D:
				GamePanel.tankOne.setAngle(GamePanel.tankOne.angle - 3);
				System.out.println("D");
				break;
				
			// Tank Two Controls
			// I moves the tank towards the direction it is facing
			// K backwards
			// J/L to steer left/right
			case KeyEvent.VK_I:
				rads2 = Math.toRadians(GamePanel.tankTwo.angle);
				dx2 = Math.cos(rads2) * 4;
				dy2 = Math.sin(rads2) * 4;
				
				GamePanel.tankTwo.x += dx2;
				GamePanel.tankTwo.y -= dy2;		
				break;
				
			case KeyEvent.VK_J:
				GamePanel.tankTwo.setAngle(GamePanel.tankTwo.angle + 3);
				break;
				
			case KeyEvent.VK_K:
				rads2 = Math.toRadians(GamePanel.tankTwo.angle);
				dx2 = Math.cos(rads2) * 4;
				dy2 = Math.sin(rads2) * 4;
				
				GamePanel.tankTwo.x -= dx2;
				GamePanel.tankTwo.y += dy2;		
				break;
				
			case KeyEvent.VK_L:
				GamePanel.tankTwo.setAngle(GamePanel.tankTwo.angle - 3);
				break;
				
			default:
				break;
		} // end switch
	}
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		doList.add(e);
		for (KeyEvent k : doList)
		{
			if (e.getKeyChar() == k.getKeyChar())
			{
				doList.remove(e);
			}
			
			doActions(e);
		}
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		System.out.println("released:" + e.getKeyChar());
		doList.remove(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
