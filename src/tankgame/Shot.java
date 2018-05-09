package tankgame;

import java.io.File;
import java.awt.Graphics;
import java.awt.Point;

public class Shot extends CollidableObject{
    private static final String SHOT_IMAGE = "resources" + File.separator + "Shell_basic_strip60.png";
    private static final int BASE_SPEED = 3;
    private static final int BASE_DAMAGE = 10;
    private static final int TRIM_X = 3;
    private static final int TRIM_Y = 7;
    
    private Tank source;
    private int vector;
    private float velocity;
    private int damage;
    
    public Shot(Tank source) {
        this(source, BASE_SPEED);
    }
    
    public Shot(Tank source, float velocity) {
        this(source, velocity, BASE_DAMAGE);
    }
    
    public Shot(Tank source, float velocity, int damage) {
        super((int)source.getX(), (int)source.getY() , SHOT_IMAGE);
        this.source = source;
        this.vector = source.getDirection();
        this.velocity = velocity;
        this.damage = damage;
        adjustShot();
        trimHitbox(TRIM_X, -TRIM_X, TRIM_Y, -TRIM_Y);
    }
    
    private void adjustShot() {
        double shiftX, shiftY;
        Point sourceCenter = source.getHitboxCenter();
        Point myCenter = this.hitbox.getCenter();
        
        shiftX = sourceCenter.x - myCenter.x;
        shiftY = sourceCenter.y - myCenter.y;
        
        this.x += shiftX;
        this.y += shiftY;
        this.hitbox.translate((int)shiftX, (int)shiftY);        
    }

    public float getVector() {
        return vector;
    }
    
    public void setVector(int newVector) {
        vector = newVector;
    }
    
    public float getVelocity() {
        return velocity;
    }
    
    public void setVelocity(float newVelocity) {
        velocity = newVelocity;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setDamage(int newDamage) {
        damage = newDamage;
    }
    
    public Tank getSource() {
        return source;
    }
    
    private void makeMove() {
        double oldX, oldY;
        double rads, dx, dy;
        rads = Math.toRadians(this.vector);
        oldX = this.x;
        oldY = this.y;
        dx = Math.cos(rads) * this.velocity;
        dy = Math.sin(rads) * this.velocity;
        this.x += dx;
        this.y -= dy;
        hitbox.translate(((int)this.x - (int)oldX), ((int)this.y - (int)oldY));
        hitbox.rotate(vector);
    }
    
    @Override
    public void repaint(Graphics graphics) {
    	makeMove();
        graphics.drawImage(sprite.getImage((int)vector/6), (int) this.x, (int) this.y, null);
    }
}
