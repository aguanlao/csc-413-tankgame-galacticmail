package common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {

    protected static final int FRAMES = 60;
    protected int totalFrames, frameWidth, frameHeight;
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
            exception.printStackTrace();
        }
    }
    
    private void loadImages(String file, int framesCount) throws IOException{
        BufferedImage spriteFile = ImageIO.read(new File(file));
        images = new BufferedImage[framesCount];
        
        frameWidth = spriteFile.getWidth() / framesCount;
        frameHeight = spriteFile.getHeight();
        for(int i = 0; i < images.length; i++) {
            images[i] = spriteFile.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
    }
    
    public BufferedImage getImage(int index) {
        return images[index];
    }
    
    public int getWidth() {
        return frameWidth;
    }
    
    public int getHeight() {
        return frameHeight;
    }
}
