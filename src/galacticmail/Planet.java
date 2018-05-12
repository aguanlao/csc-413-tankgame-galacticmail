package galacticmail;

import common.CollidableObject;

import java.awt.Graphics;
import java.io.File;

public class Planet extends CollidableObject {

    private static final String BASES_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Bases_strip8.png";
    private static final int BASES_COUNT = 8;

    private final int imageIndex;

    public Planet(int x, int y) {
        super(x, y, BASES_IMAGE, BASES_COUNT);
        imageIndex = (int) (Math.random() * BASES_COUNT);
        isLive = true;
    }

    public void markDelivered() {
        //Sets collidableObject.isLive to false, to be ignored in rendering later
        //Possibly code for fade out effect
        isLive = false;
    }

    @Override
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage(imageIndex), (int) x, (int) y, null);
    }
}
