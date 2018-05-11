package galacticmail;

import common.CollidableObject;

import java.awt.Graphics;
import java.io.File;

public class Asteroid extends CollidableObject {
    private static final String ASTEROID_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Asteroid_strip180.png";
    
    private static final int ASTEROID_FRAMES = 180;
    private final float velocity;
    private final double vector;
    private final int rotationSpeed;
    private int angle;

    public Asteroid(int startX, int startY, int direction, int speed, int rotateSpeed) {
        super(startX, startY, ASTEROID_IMAGE, ASTEROID_FRAMES);
        velocity = speed;
        vector = Math.toRadians(direction);
        angle = (int)(Math.random() * 361);
        rotationSpeed = rotateSpeed;
    }

    private void move() {
        double oldX, oldY;
        double dx, dy;
        oldX = x;
        oldY = y;
        dx = Math.cos(vector) * velocity;
        dy = Math.sin(vector) * velocity;
        x += dx;
        y -= dy;
        angle = (angle + rotationSpeed) % 360;
        
        hitbox.translate(((int) x - (int) oldX), ((int) y - (int) oldY));
        hitbox.rotate(angle);
    }

    @Override
    public void repaint(Graphics graphics) {
        move();
        graphics.drawImage(sprite.getImage((int) angle / 2), (int) x, (int) y, null);
    }
}
