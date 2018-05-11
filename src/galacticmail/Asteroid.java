package galacticmail;

import common.CollidableObject;

import java.awt.Graphics;
import java.io.File;

public class Asteroid extends CollidableObject{
	private static final String ASTEROID_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Asteroid_strip180.png";
	private float velocity;
	private int vector;
	
	public Asteroid(int x, int y) {
		super(x, y, ASTEROID_IMAGE, 1);
	}
	
    private void makeMove() {
        double oldX, oldY;
        double rads, dx, dy;
        rads = Math.toRadians(this.vector);
        oldX = this.x;
        oldY = this.y;
        dx = Math.cos(rads) * this.velocity;
        dy = Math.sin(rads) * this.velocity;
        this.x += dx;
        this.y -= dy;
        hitbox.translate(((int)this.x - (int)oldX), ((int)this.y - (int)oldY));
        hitbox.rotate(vector);
    }
    
    @Override
    public void repaint(Graphics graphics) {
    	makeMove();
        graphics.drawImage(sprite.getImage((int)vector/6), (int) this.x, (int) this.y, null);
    }
}
