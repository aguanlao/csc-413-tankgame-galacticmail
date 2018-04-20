package TankGame;

import java.io.IOException;
import java.awt.Rectangle;

public abstract class CollidableObject extends GameObject{
    
    public CollidableObject(String image) throws IOException {
        super(image);
    }
    
    protected boolean isLive;
    protected Rectangle hitbox;
    
    public boolean collides (CollidableObject object) {
        return hitbox.intersects(object.hitbox);
    }
    
    public boolean isLiveNow() {
        return isLive;
    }
}