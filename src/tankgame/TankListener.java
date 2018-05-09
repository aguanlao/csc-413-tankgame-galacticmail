package tankgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankListener implements KeyListener {

    private static final long serialVersionUID = 1L;

    private Tank tankOne, tankTwo;
    
    public void addTanks(Tank tankOne, Tank tankTwo) {
        this.tankOne = tankOne;
        this.tankTwo = tankTwo;
    }
    
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Tank One Controls
            // W moves the tank towards the direction it is facing
            // S backwards
            // A/D to steer left/right
            case KeyEvent.VK_W:
                tankOne.setForward(true);
                break;

            case KeyEvent.VK_A:
                tankOne.setLeft(true);
                break;

            case KeyEvent.VK_S:
                tankOne.setBackwards(true);
                break;

            case KeyEvent.VK_D:
                tankOne.setRight(true);
                break;

            case KeyEvent.VK_SPACE:
            	tankOne.setShooting(true);
            	break;
            	
            // Tank Two Controls
            // I moves the tank towards the direction it is facing
            // K backwards
            // J/L to steer left/right
            case KeyEvent.VK_I:
                tankTwo.setForward(true);
                break;

            case KeyEvent.VK_J:
                tankTwo.setLeft(true);
                break;

            case KeyEvent.VK_K:
                tankTwo.setBackwards(true);
                break;

            case KeyEvent.VK_L:
                tankTwo.setRight(true);
                break;

            case KeyEvent.VK_ENTER:
            	tankTwo.setShooting(true);
                break;
                
            default:
                break;
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                tankOne.setForward(false);
                break;

            case KeyEvent.VK_A:
                tankOne.setLeft(false);
                break;

            case KeyEvent.VK_S:
                tankOne.setBackwards(false);
                break;

            case KeyEvent.VK_D:
                tankOne.setRight(false);
                break;

            case KeyEvent.VK_SPACE:
            	tankOne.setShooting(false);
            	break;
            	
            case KeyEvent.VK_I:
                tankTwo.setForward(false);
                break;

            case KeyEvent.VK_J:
                tankTwo.setLeft(false);
                break;

            case KeyEvent.VK_K:
                tankTwo.setBackwards(false);
                break;

            case KeyEvent.VK_L:
                tankTwo.setRight(false);
                break;

            case KeyEvent.VK_ENTER:
            	tankTwo.setShooting(false);
                break;
                
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
