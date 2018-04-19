package TankGame;

public class Shot extends CollidableObject{
    private static final int BASE_SPEED = 5;
    private static final int BASE_DAMAGE = 10;
    private float vector;
    private float velocity;
    private int damage;
    private Tank source;
    
    public Shot(Tank source, float vector) {
        this(source, vector, BASE_SPEED);
    }
    
    public Shot(Tank source, float vector, float velocity) {
        this(source, vector, velocity, BASE_DAMAGE);
    }
    
    public Shot(Tank source, float vector, float velocity, int damage) {
        this.source = source;
        this.vector = vector;
        this.velocity = velocity;
        this.damage = damage;
    }
    
    public Tank getSource() {
        return source;
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
