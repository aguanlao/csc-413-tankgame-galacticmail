package galacticmail;

import common.CollidableObject;

import java.awt.Graphics;
import java.io.File;

public class Planet extends CollidableObject {
	private static final String BASES_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Bases_strip8.png";
	private static final int BASES_COUNT = 8;
        
        private final int imageIndex;
        private boolean isDelivered;
        
	public Planet(int x, int y) {
		super(x, y, BASES_IMAGE);                
                imageIndex = (int)(Math.random() * (BASES_COUNT +1));
	}
        
        @Override
        public void repaint(Graphics graphics) {
            graphics.drawImage(sprite.getImage(imageIndex), (int)x, (int)y, null);
        }
}
