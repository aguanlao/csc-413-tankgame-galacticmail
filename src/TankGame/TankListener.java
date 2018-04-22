package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class TankListener extends GamePanel implements KeyListener {
	
	//private Set<Character> keysBeingPressed = new HashSet<Character>();
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		double rads;
		double dx;
		double dy;
		
		switch ( e.getKeyCode() )
		{
		// Tank One Controls
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
		case KeyEvent.VK_I:
			GamePanel.tankTwo.y -= GamePanel.tankTwo.speed;
			GamePanel.tankTwo.direction = (GamePanel.tankTwo.direction + 1) % 60;
			System.out.println("I");
			break;
		case KeyEvent.VK_J:
			GamePanel.tankTwo.x -= GamePanel.tankTwo.speed;
			GamePanel.tankTwo.direction = (GamePanel.tankTwo.direction + 1) % 60;
			System.out.println("J");
			break;
		case KeyEvent.VK_K:
			GamePanel.tankTwo.y += GamePanel.tankTwo.speed;
			GamePanel.tankTwo.direction = (GamePanel.tankTwo.direction + 1) % 60;
			System.out.println("K");
			break;
		case KeyEvent.VK_L:
			GamePanel.tankTwo.x += GamePanel.tankTwo.speed;
			GamePanel.tankTwo.direction = (GamePanel.tankTwo.direction + 1) % 60;
			System.out.println("L");
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
