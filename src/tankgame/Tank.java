package tankgame;

import common.CollidableObject;
import java.awt.Graphics;
import java.awt.Point;

public class Tank extends CollidableObject {
    private static final int HITBOX_TRIM = 9;
    private static final int TANK_HEALTH = 100;
    private static final int TANK_LIVES = 3;
    private static final double BASE_SPEED = 1;
    private static final double TURN_SPEED = 1;

    private double startX, startY, speed, lastX, lastY, turnSpeed;
    private int startDirection, direction, lastDirection, livesLeft, health;
    private boolean isForward, isBackwards, isLeft, isRight, isColliding, isShooting;

    public Tank(int x, int y, String image) {
        this(x, y, 0, image);
    }

    public Tank(int x, int y, int direction, String image) {
        super(x, y, image);

        this.startX = x;
        this.startY = y;

        this.direction = direction % 360;
        startDirection = this.direction;
        health = TANK_HEALTH;
        speed = BASE_SPEED;
        turnSpeed = TURN_SPEED;
        isLive = true;
        livesLeft = TANK_LIVES;

        trimHitbox(HITBOX_TRIM, -HITBOX_TRIM + 2, HITBOX_TRIM, -HITBOX_TRIM - 1);
    }

    public void addAngle(double angle) {
        // prevents out of bounds, allow complete 360 turns
        if (angle < 0) {
            angle += 360;
        }
        this.direction = (this.direction + (int) angle) % 360;
    }

    public void tookDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            livesLeft--;
            isLive = false;
        }
    }

    public int getHealth() {
        if (isLive) {
            return health;
        }
        return 0;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void resetPosition() {
        this.x = this.startX;
        this.y = this.startY;
        this.direction = 0;
    }

    public void checkPosition() {
        double oldX, oldY;
        double rads, dx, dy;
        rads = Math.toRadians(this.direction);
        dx = Math.cos(rads) * this.speed;
        dy = Math.sin(rads) * this.speed;
        oldX = this.x;
        oldY = this.y;

        if (!this.isColliding) {
            lastX = this.x;
            lastY = this.y;
            lastDirection = this.direction;

            if (this.isForward && !this.isBackwards) {
                this.x += 1.2 * dx;
                this.y -= 1.2 * dy;
                hitbox.translate(((int) this.x - (int) oldX), ((int) this.y - (int) oldY));
            } else if (this.isBackwards && !this.isForward) {
                this.x -= 1.2 * dx;
                this.y += 1.2 * dy;
                hitbox.translate(((int) this.x - (int) oldX), ((int) this.y - (int) oldY));
            }

            if (this.isLeft && !this.isRight) {
                this.addAngle(this.turnSpeed);
                hitbox.rotate(this.direction);
            } else if (this.isRight && !this.isLeft) {
                this.addAngle(-this.turnSpeed);
                hitbox.rotate(this.direction);
            }
        } else {
            this.x = lastX;
            this.y = lastY;
            this.direction = lastDirection;
            hitbox.translate(((int) this.x - (int) oldX), ((int) this.y - (int) oldY));
            hitbox.rotate(this.direction);
            setColliding(false);
        }
    }

    public void setForward(boolean flag) {
        isForward = flag;
    }

    public void setBackwards(boolean flag) {
        isBackwards = flag;
    }

    public void setLeft(boolean flag) {
        isLeft = flag;
    }

    public void setRight(boolean flag) {
        isRight = flag;
    }

    public void setColliding(boolean flag) {
        isColliding = flag;
    }

    public void setShooting(boolean flag) {
        isShooting = flag;
    }

    public boolean getShootState() {
        return isShooting;
    }

    public int getDirection() {
        return this.direction;
    }

    public Point getHitboxCenter() {
        return hitbox.getCenter();
    }

    public void respawn() {
        if (livesLeft > 0) {
            hitbox.translate(((int) this.startX - (int) this.x), ((int) this.startY - (int) this.y));
            this.x = this.startX;
            this.y = this.startY;
            this.direction = this.startDirection;

            health = TANK_HEALTH;
            isLive = true;
        }
    }

    @Override
    public void repaint(Graphics graphics) {
        checkPosition();
        graphics.drawImage(sprite.getImage((int) direction / 6), (int) this.x, (int) this.y, null);
    }
}
