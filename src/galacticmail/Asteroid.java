package galacticmail;

import common.CollidableObject;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

public class Asteroid extends CollidableObject {
    private static final String ASTEROID_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Asteroid_strip180.png";
    
    private static final int ASTEROID_FRAMES = 180;
    private final double velocity, vector, rotationSpeed;
    private double angle;
    
    //TODO: Debug constructor
    public Asteroid(int startX, int startY) {
        this(startX, startY, 270, 0.5, .1);
    }

    public Asteroid(int startX, int startY, int direction, double speed, double rotateSpeed) {
        super(startX, startY, ASTEROID_IMAGE, ASTEROID_FRAMES);
        velocity = speed;
        vector = Math.toRadians(direction);
        angle = Math.random() * 360;
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
        hitbox.rotate(-angle);
    }

    @Override
    public void repaint(Graphics graphics) {
        move();
        graphics.drawImage(sprite.getImage((int) angle / 2), (int) x, (int) y, null);
//        drawHitbox(graphics);
    }
}
