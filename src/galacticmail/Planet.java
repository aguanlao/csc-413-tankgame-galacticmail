package galacticmail;

import java.io.File;
import common.CollidableObject;

public class Planet extends CollidableObject {
	private static final String PLANET_IMAGE = "galacticmail" + File.separator + "resources" + File.separator + "Bases_strip8.png";
	
	public Planet(int x, int y, String image) {
		super(x, y, image);
	}

}
