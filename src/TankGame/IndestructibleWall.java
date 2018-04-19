package TankGame;

public class IndestructibleWall extends CollidableObject{
    public IndestructibleWall(int x, int y) {
        this.x = x;
        this.y = y;
        isLive = true;
    }
}
