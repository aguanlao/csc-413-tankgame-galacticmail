package TankGame;

import java.io.File;

public class IndestructibleWall extends CollidableObject{
    private static final String INDESTRUCT_WALL_IMAGE = "resources" + File.separator + "wall_indestructible.png";
    public IndestructibleWall(int x, int y) {
        super(x, y, INDESTRUCT_WALL_IMAGE, 1);
        isLive = true;
    }
}
