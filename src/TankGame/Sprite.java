package TankGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {

    private static final int FRAMES = 60;
    protected int totalFrames;
    private BufferedImage[] images;
    
    public Sprite(String imageFile) {
        this(imageFile, FRAMES);
    }

    public Sprite(String imageFile, int frames) {
        try {
            loadImages(imageFile, frames);
            totalFrames = frames;
        }
        catch (IOException exception) {
            System.err.println("Failed to load sprite " + imageFile);
        }
    }
    
    private void loadImages(String file, int framesCount) throws IOException{
        int frameWidth, frameHeight;
        BufferedImage spriteFile = ImageIO.read(new File(file));
        images = new BufferedImage[framesCount];
        
        frameWidth = spriteFile.getWidth() / framesCount;
        frameHeight = spriteFile.getHeight();
        for(int i = 0; i < images.length; i++) {
            images[i] = spriteFile.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
    }

    public void draw(Graphics g, int i) {
        //g.drawImage(spritesArray[i], 10, 10, 0);
    }
    
    public BufferedImage getImage(int index) {
        return images[index];
    }
}
