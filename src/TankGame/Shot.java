package TankGame;

import java.io.IOException;

public class Shot extends CollidableObject{
    private static final String SHOT_IMAGE = "";
    private static final int BASE_SPEED = 5;
    private static final int BASE_DAMAGE = 10;
    private float vector;
    private float velocity;
    private int damage;
    
    public Shot(float vector) throws IOException{
        this(vector, BASE_SPEED);
    }
    
    public Shot(float vector, float velocity) throws IOException{
        this(vector, velocity, BASE_DAMAGE);
    }
    
    public Shot(float vector, float velocity, int damage) throws IOException{
        super(SHOT_IMAGE);
        this.vector = vector;
        this.velocity = velocity;
        this.damage = damage;
    }

    public float getVector() {
        return vector;
    }
    
    public void setVector(float newVector) {
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
