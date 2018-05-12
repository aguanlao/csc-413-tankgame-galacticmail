package common;

import java.awt.Graphics;

public class Explosion extends NonCollidableObject {
    private static final int FRAME_DELAY = 20;
    
    private boolean isFinished;
    private int currentFrame;
    private final int frameCount;
    
    public Explosion(int x, int y, String image, int count) {
        super(x, y, image, count);
        currentFrame = 0;
        frameCount = count;
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
        if(!isFinished) {
            graphics.drawImage(sprite.getImage(currentFrame/FRAME_DELAY), (int) this.x, (int) this.y, null);
            currentFrame++;
        }

        if(currentFrame == frameCount * FRAME_DELAY) {
            isFinished = true;
        }
    }
    
}
