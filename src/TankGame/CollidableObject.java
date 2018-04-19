package TankGame;

import java.awt.Rectangle;

public abstract class CollidableObject extends GameObject{
    
    protected boolean isLive;
    protected Rectangle hitbox;
    
    public boolean collides (CollidableObject object) {
        return hitbox.intersects(object.hitbox);
    }
    
    public boolean isLiveNow() {
        return isLive;
    }
}
