package TankGame;

import java.util.*;

public class GameWorld implements Observer{
    private ArrayList<CollidableObject> players;
    private ArrayList<CollidableObject> shots;
    private ArrayList<CollidableObject> walls;
    private ArrayList<NonCollidableObject> explosions;
    
    
    public void update() {
        
    }


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
    
}
