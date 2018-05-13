package galacticmail;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

import common.CollidableObject;
import common.Sprite;

public class Ship extends CollidableObject {

    private static final String FLYING_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Flying_strip72.png";
    private static final String LANDED_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Landed_strip72.png";
    private static final int SHIP_FRAMES = 72;
    private static final int HITBOX_TRIM = 5;
    private static final double BASE_SPEED = 1;
    private static final double TURN_SPEED = 0.3;
    
    private double speed, turnSpeed;
    private double direction;
    private boolean isLeft, isRight, isLanded;
    private Sprite otherSprite;

    public Ship(int x, int y) {
        this(x, y, 0);
    }

    public Ship(int x, int y, int direction) {
        super(x, y, LANDED_IMAGE, SHIP_FRAMES);

        this.direction = direction % 360;
        otherSprite = new Sprite(FLYING_IMAGE, SHIP_FRAMES);

        speed = BASE_SPEED;
        turnSpeed = TURN_SPEED;
        isLive = true;
        isLanded = true;

        trimHitbox(HITBOX_TRIM, -HITBOX_TRIM, HITBOX_TRIM, -HITBOX_TRIM);
    }

    public void swapSprites() {
        Sprite tempSprite = sprite;
        sprite = otherSprite;
        otherSprite = tempSprite;
    }

    public void addAngle(double angle) {
        direction += angle;
        if (direction < 0) {
            direction += 360;
        }
        direction = direction % 360;
    }

    public void setLeft(boolean flag) {
        isLeft = flag;
    }

    public void setRight(boolean flag) {
        isRight = flag;
    }

    public double getDirection() {
        return direction;
    }

    public Point getHitboxCenter() {
        return hitbox.getCenter();
    }

    public void landOn(CollidableObject obj) {
        Point oldCenter = this.getCenter();
        Point newCenter = obj.getCenter();

        shiftBy(newCenter.x - oldCenter.x, newCenter.y - oldCenter.y);
        swapSprites();
        isLanded = true;
    }

    public void takeOff() {
        swapSprites();
        isLanded = false;
    }

    public boolean getLandedState() {
        return isLanded;
    }

    public void setLanded(boolean flag) {
        isLanded = flag;
    }

    public void move() {
        double oldX, oldY;
        double rads, dx, dy;
        rads = Math.toRadians(direction);
        dx = Math.cos(rads) * speed;
        dy = Math.sin(rads) * speed;
        oldX = x;
        oldY = y;

        if (!isLanded) {
            x += dx;
            y -= dy;
            hitbox.translate(((int) x - (int) oldX), ((int) y - (int) oldY));
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
        graphics.drawImage(sprite.getImage((int) direction / 5), (int) x, (int) y, null);
       
//        drawHitbox(graphics);
    }
}
