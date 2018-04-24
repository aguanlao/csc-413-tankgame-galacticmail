package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankListener extends GamePanel implements KeyListener {

    private static final long serialVersionUID = 1L;

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Tank One Controls
            // W moves the tank towards the direction it is facing
            // S backwards
            // A/D to steer left/right
            case KeyEvent.VK_W:
                GamePanel.tankOne.isForward = true;
                break;

            case KeyEvent.VK_A:
                GamePanel.tankOne.isLeft = true;
                break;

            case KeyEvent.VK_S:
                GamePanel.tankOne.isBackwards = true;
                break;

            case KeyEvent.VK_D:
                GamePanel.tankOne.isRight = true;
                break;

            // Tank Two Controls
            // I moves the tank towards the direction it is facing
            // K backwards
            // J/L to steer left/right
            case KeyEvent.VK_I:
                GamePanel.tankTwo.isForward = true;
                break;

            case KeyEvent.VK_J:
                GamePanel.tankTwo.isLeft = true;
                break;

            case KeyEvent.VK_K:
                GamePanel.tankTwo.isBackwards = true;
                break;

            case KeyEvent.VK_L:
                GamePanel.tankTwo.isRight = true;
                break;

            case KeyEvent.VK_SPACE:
                GamePanel.tankOne.isReset = true;
                GamePanel.tankTwo.isReset = true;
            default:
                break;
        } // end switch
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                GamePanel.tankOne.isForward = false;
                break;

            case KeyEvent.VK_A:
                GamePanel.tankOne.isLeft = false;
                break;

            case KeyEvent.VK_S:
                GamePanel.tankOne.isBackwards = false;
                break;

            case KeyEvent.VK_D:
                GamePanel.tankOne.isRight = false;
                break;

            case KeyEvent.VK_I:
                GamePanel.tankTwo.isForward = false;
                break;

            case KeyEvent.VK_J:
                GamePanel.tankTwo.isLeft = false;
                break;

            case KeyEvent.VK_K:
                GamePanel.tankTwo.isBackwards = false;
                break;

            case KeyEvent.VK_L:
                GamePanel.tankTwo.isRight = false;
                break;

            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
