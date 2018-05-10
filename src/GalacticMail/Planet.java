package galacticmail;
import java.io.File;

import TankGame.CollidableObject;

public class Planet extends CollidableObject {
	private static final String Planet_Image = "resources" + File.separator + "Bases_strip8.png";
	
	public Planet(int x, int y, String image) {
		super(x, y, image);
	}

}
