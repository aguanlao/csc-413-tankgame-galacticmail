package tankgame;

import common.NonCollidableObject;
import java.awt.Graphics;
import java.io.File;

public class Explosion extends NonCollidableObject {
    private static final String EXPLOSION_IMAGE = "tankgame" + File.separator + "resources" + File.separator + "Explosion_small_strip6.png";
    private static final int NUMBER_OF_FRAMES = 6;
    private static final int FRAME_DELAY = 20;
    
    private boolean isFinished;
    private int currentFrame;
    
    public Explosion(int x, int y) {
        super(x, y, EXPLOSION_IMAGE, NUMBER_OF_FRAMES);
        currentFrame = 0;
        isFinished = false;
    }
    
    public int getFrame() {
        return currentFrame;
    }
    
    public boolean isFinished() {
        return isFinished;
    }
    
    @Override
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage(currentFrame/FRAME_DELAY), (int) this.x, (int) this.y, null);
        currentFrame++;

        if(currentFrame == NUMBER_OF_FRAMES * FRAME_DELAY) {
            isFinished = true;
        }
    }
    
}
