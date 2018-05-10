package common;

public abstract class NonCollidableObject extends GameObject{
    public NonCollidableObject(int x, int y, String image) {
        super(x, y, image);
    }
    
    public NonCollidableObject(int x, int y, String image, int frameCount) {
        super(x, y, image, frameCount);
    }   
    
}
