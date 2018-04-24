package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class TankListener implements KeyListener {
	
	private Set<Character> keysBeingPressed = new HashSet<Character>();
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		keysBeingPressed.add(e.getKeyChar());

		switch ( e.getKeyCode() )
		{
		case KeyEvent.VK_W:
			GamePanel.tankOne.y -= 20;
			System.out.println("W");
			break;
		case KeyEvent.VK_A:
			GamePanel.tankOne.x -= 20;
			System.out.println("A");
			break;
		case KeyEvent.VK_S:
			GamePanel.tankOne.y += 20;
			System.out.println("S");
			break;
		case KeyEvent.VK_D:
			GamePanel.tankOne.x += 20;
			System.out.println("D");
			break;

		case KeyEvent.VK_I:
			GamePanel.tankTwo.y -= 20;
			System.out.println("I");
			break;
		case KeyEvent.VK_J:
			GamePanel.tankTwo.x -= 20;
			System.out.println("J");
			break;
		case KeyEvent.VK_K:
			GamePanel.tankTwo.y += 20;
			System.out.println("K");
			break;
		case KeyEvent.VK_L:
			GamePanel.tankTwo.x += 20;
			System.out.println("L");
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysBeingPressed.remove(e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
