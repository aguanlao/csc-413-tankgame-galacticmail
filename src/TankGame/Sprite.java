package TankGame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Sprite {

	public int columns = 60; // sprite sheet has 60 frames
	private BufferedImage spritesFile;
	
	// store each sprite frame here
	//private BufferedImage[] spritesArray;
	
	// read in the sheet
	//public BufferedImage spritesSheet = ImageIO.read( new File ("Tank_blue_basic_strip60.png") );
	
	//spritesArray = new BufferedImage[columns];
	
	public Sprite(BufferedImage imageFile) {
            spritesFile = imageFile;
	//	for (int i = 0; i < columns; i++)
	//		spritesArray[i] = spritesSheet.getSubimage(2,2,2,2);
	}
	
	public void draw(Graphics g, int i) {
		//g.drawImage(spritesArray[i], 10, 10, 0);
	}
}
