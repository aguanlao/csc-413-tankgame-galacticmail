package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class TankListener extends GamePanel implements KeyListener {
	
	//private Set<Character> keysBeingPressed = new HashSet<Character>();
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
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
			dx = Math.cos(rads) * 4;
			dy = Math.sin(rads) * 4;
			
			GamePanel.tankOne.x += dx;
			GamePanel.tankOne.y -= dy;		
			break;
			
		case KeyEvent.VK_A:
			GamePanel.tankOne.setAngle(GamePanel.tankOne.angle + 3);
			break;
		
		case KeyEvent.VK_S:
			rads = Math.toRadians(GamePanel.tankOne.angle);
			dx = Math.cos(rads) * 4;
			dy = Math.sin(rads) * 4;
			
			GamePanel.tankOne.x -= dx;
			GamePanel.tankOne.y += dy;	
			break;
		
		case KeyEvent.VK_D:
			GamePanel.tankOne.setAngle(GamePanel.tankOne.angle - 3);
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
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//keysBeingPressed.remove(e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
