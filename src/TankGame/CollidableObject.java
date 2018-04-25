package TankGame;

import java.awt.*;
import java.awt.geom.Area;

public abstract class CollidableObject extends GameObject{
        
    protected boolean isLive;
    protected Polygon hitbox;
    protected int xPoints[], yPoints[];
    
    public CollidableObject(int x, int y, String image, int frameCount) {
        super(x, y, image, frameCount);
        buildHitbox();
//        hitbox = new Rectangle((int)this.x, (int)this.y, this.sprite.getWidth(), this.sprite.getHeight());
    }
    
    public CollidableObject(int x, int y, String image) {
        super(x, y, image);
        buildHitbox();
//        hitbox = new Rectangle((int)this.x, (int)this.y, this.sprite.getWidth(), this.sprite.getHeight());
    }
    
    private void buildHitbox() {
        int x2, y2;
        x2 = (int)this.x + sprite.getWidth();
        y2 = (int)this.y + sprite.getHeight();
        xPoints = new int[]{(int)this.x, x2, x2, (int)this.x};
        yPoints = new int[]{(int)this.y, (int)this.y, y2, y2};
        hitbox = new Polygon(xPoints, yPoints, 4);
    }

    public boolean collides (CollidableObject object) {
        Area myArea, otherArea;
        myArea = new Area(hitbox);
        otherArea = new Area(object.hitbox);
        
        myArea.intersect(otherArea);
        return !myArea.isEmpty();
    }
    
    public boolean isLiveNow() {
        return isLive;
    }
}