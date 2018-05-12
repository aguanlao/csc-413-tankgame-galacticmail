package galacticmail;

import common.CollidableObject;

import java.awt.Graphics;
import java.io.File;

public class Planet extends CollidableObject {

    private static final String BASES_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Bases_strip8.png";
    private static final int BASES_COUNT = 8;
    private static int HITBOX_TRIM = 28;

    private final int imageIndex;

    private boolean isLandedOn;
    
    public Planet(int x, int y) {
        super(x, y, BASES_IMAGE, BASES_COUNT);
        imageIndex = (int) (Math.random() * BASES_COUNT);
        isLive = true;
        
        trimHitbox(HITBOX_TRIM, -HITBOX_TRIM, HITBOX_TRIM, -HITBOX_TRIM);
    }

    public void markDelivered() {
        //Sets collidableObject.isLive to false, to be ignored in rendering later
        //Possibly code for fade out effect
        isLive = false;
        isLandedOn = true;
    }
    
    public boolean isLandedOn() {
        return isLandedOn;
    }
    
    public void setLandedOn(boolean flag) {
        isLandedOn = flag;
    }
    
    @Override
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage(imageIndex), (int) x, (int) y, null);
        
//        drawHitbox(graphics);
    }
}
