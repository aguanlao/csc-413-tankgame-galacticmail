package TankGame;

public class Tank extends CollidableObject {
    private float direction;
    private int speed;
    
    public Tank() {
    
    }
    
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
