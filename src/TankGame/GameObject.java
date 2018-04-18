package TankGame;

import java.util.Observable;

public class GameObject extends Observable {
    private int x, y;
    
    public GameObject() {
    
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
}
