package TankGame;

import java.io.File;
import java.io.IOException;
import java.awt.Point;

public class Shot extends CollidableObject{
    private static final String SHOT_IMAGE = "resources" + File.separator + "Shell_basic_strip60.png";
    private static final int BASE_SPEED = 5;
    private static final int BASE_DAMAGE = 10;
    private int vector;
    private float velocity;
    private int damage;
    
    public Shot(Point position, int vector) throws IOException {
        this(position, vector, BASE_SPEED);
    }
    
    public Shot(Point position, int vector, float velocity) throws IOException{
        this(position, vector, velocity, BASE_DAMAGE);
    }
    
    public Shot(Point position, int vector, float velocity, int damage) throws IOException{
        super(position.x, position.y, SHOT_IMAGE);
        this.vector = vector;
        this.velocity = velocity;
        this.damage = damage;
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
    
    private int getDamage() {
        return damage;
    }
    
    private void setDamage(int newDamage) {
        damage = newDamage;
    }
    
}
