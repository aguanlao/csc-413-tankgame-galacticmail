package galacticmail;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

import common.CollidableObject;
import common.GameObject;

public class Ship extends CollidableObject {

    private static final String SHIP_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Flying_strip72.png";
    private static final int SHIP_FRAMES = 72;
    private static final int BASE_SPEED = 1;
    private static final int TURN_SPEED = 1;
    
    private double speed, turnSpeed;
    private int direction;
    private boolean isForward, isLeft, isRight, isLanded, leftThePlanet;

    public Ship(int x, int y) {
        this(x, y, 0);
    }

    public Ship(int x, int y, int direction) {
        super(x, y, SHIP_IMAGE, SHIP_FRAMES);

        this.direction = direction % 360;

        speed = BASE_SPEED;
        turnSpeed = TURN_SPEED;
        isLive = true;
        isLanded = false;
    }

    public void addAngle(double angle) {
        // prevents out of bounds, allow complete 360 turns
        if (angle < 0) {
            angle += 360;
        }
        direction = (direction + (int) angle) % 360;
    }

    public void setForward(boolean flag) {
        isForward = flag;
    }

    public void setLeft(boolean flag) {
        isLeft = flag;
    }

    public void setRight(boolean flag) {
        isRight = flag;
    }

    public int getDirection() {
        return direction;
    }

    public Point getHitboxCenter() {
        return hitbox.getCenter();
    }

    public void setLanding(GameObject obj) {
        // set player's x, y, speed, and direction the same as the base obj
        x = obj.getX();
        y = obj.getY();
        isLanded = true;
        isForward = false;
    }

    public boolean getIsLanded() {
        return isLanded;
    }
    
    public void setLeftThePlanet(boolean flag) {
        leftThePlanet = flag;
    }
    
    public boolean leftThePlanet() {
        return leftThePlanet;
    }
    
    public void move() {
        double oldX, oldY;
        double rads, dx, dy;
        rads = Math.toRadians(direction);
        dx = Math.cos(rads) * speed;
        dy = Math.sin(rads) * speed;
        oldX = x;
        oldY = y;

        if (isForward) {
            x += dx;
            y -= dy;
            hitbox.translate(((int)x - (int)oldX), ((int)y - (int)oldY));
            if (isLanded) {
                leftThePlanet = true;
                isLanded = false;
            } 
        }

        if (isLeft && !isRight) {
            addAngle(turnSpeed);
            hitbox.rotate(direction);
        } else if (isRight && !isLeft) {
            addAngle(-turnSpeed);
            hitbox.rotate(direction);
        }
    }

    @Override
    public void repaint(Graphics graphics) {
        move();
        graphics.drawImage(sprite.getImage((int) direction / 5), (int)x, (int)y, null);
    }
}