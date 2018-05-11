package galacticmail;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GalacticListener implements KeyListener {

    private Ship playerShip;

    public void setShip(Ship ship) {
        playerShip = ship;
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Ship Controls
            // W activates forward thrusters
            // A/D to turn left/right
            case KeyEvent.VK_W:
                playerShip.setForward(true);
                break;

            case KeyEvent.VK_A:
                playerShip.setLeft(true);
                break;

            case KeyEvent.VK_D:
                playerShip.setRight(true);
                break;

            default:
                break;
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
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

            default:
                break;
        }
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
    }
}
