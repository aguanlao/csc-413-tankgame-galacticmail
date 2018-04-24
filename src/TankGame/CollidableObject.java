package TankGame;

import java.io.IOException;
import java.awt.*;

public abstract class CollidableObject extends GameObject{
    
    public CollidableObject(String image) {
        super(image);
    }
    
    protected boolean isLive;
        //TODO: Replace with Polygon?
    protected Rectangle hitbox;
    
    public boolean collides (CollidableObject object) {
        return hitbox.intersects(object.hitbox);
    }
    
    public boolean isLiveNow() {
        return isLive;
    }
}