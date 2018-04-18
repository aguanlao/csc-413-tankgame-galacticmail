package TankGame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.BufferedImage;
import javax.imageio.ImageIO;
import javax.io.File;

public class Sprite {

	public int columns = 60; // sprite sheet has 60 frames
	// store each sprite frame here
	BufferedImage[] spritesArray;
	// read in the sheet
	BufferedImage spritesSheet = ImageIO.read(new File("Tank_blue_basic_strip60.png"));
	
	spritesArray = new BufferedImage[columns];
	
	public Sprite() {
		for (int i = 0; i < columns; i++)
			spritesArray[i] = spritesSheet.getSubimage(2,2,2,2);
	}
	
	public void draw(Graphics g) {
		g.drawImage(spritesArray[], 10, 10, 0);
	}
}
