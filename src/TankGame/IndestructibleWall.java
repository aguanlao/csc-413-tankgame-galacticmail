package tankgame;

import java.io.File;

import common.CollidableObject;

public class IndestructibleWall extends CollidableObject{
    private static final String INDESTRUCT_WALL_IMAGE = "resources" + File.separator + "wall_indestructible.png";
    public static final int IMAGE_LENGTH = 32;
    
    public IndestructibleWall(int x, int y) {
        super(x, y, INDESTRUCT_WALL_IMAGE, 1);
        isLive = true;
    }
}
