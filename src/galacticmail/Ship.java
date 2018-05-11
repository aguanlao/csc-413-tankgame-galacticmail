package galacticmail;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

import common.CollidableObject;
import common.GameObject;

public class Ship extends CollidableObject {

    private static final String SHIP_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Flying_strip72.png";

    private double startX, startY, speed, turnSpeed;
    private int startDirection, direction;
    private boolean isForward, isLeft, isRight;

    public Ship(int x, int y) {

        super(x, y, SHIP_IMAGE);
    }

    public Ship(int x, int y, int direction) {
        super(x, y, SHIP_IMAGE);

        this.startX = x;
        this.startY = y;

        this.direction = direction % 360;
        startDirection = this.direction;

        speed = 1;
        turnSpeed = 1;
        isLive = true;

    }

    public void addAngle(double angle) {
        // prevents out of bounds, allow complete 360 turns
        if (angle < 0) {
            angle += 360;
        }
        this.direction = (this.direction + (int) angle) % 360;
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
        return this.direction;
    }

    public Point getHitboxCenter() {
        return hitbox.getCenter();
    }

    public void setLanding(GameObject obj) {
        // set player's x, y, speed, and direction the same as the base obj

    }

    public void checkPosition() {
        double oldX, oldY;
        double rads, dx, dy;
        rads = Math.toRadians(this.direction);
        dx = Math.cos(rads) * this.speed;
        dy = Math.sin(rads) * this.speed;
        oldX = this.x;
        oldY = this.y;

        if (this.isForward) {
            this.x += 1.2 * dx;
            this.y -= 1.2 * dy;
            hitbox.translate(((int) this.x - (int) oldX), ((int) this.y - (int) oldY));
        }

        if (this.isLeft && !this.isRight) {
            this.addAngle(this.turnSpeed);
            hitbox.rotate(this.direction);
        } else if (this.isRight && !this.isLeft) {
            this.addAngle(-this.turnSpeed);
            hitbox.rotate(this.direction);
        }
    }

    @Override
    public void repaint(Graphics graphics) {
        checkPosition();
        graphics.drawImage(sprite.getImage((int) direction / 6), (int) this.x, (int) this.y, null);
    }
}
