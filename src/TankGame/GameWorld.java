package TankGame;

import java.util.*; 

public class GameWorld implements Observer{
    
    private ArrayList<CollidableObject> players;
    private ArrayList<CollidableObject> shots;
    private ArrayList<CollidableObject> walls;
    private ArrayList<NonCollidableObject> explosions;
    

    
    public GameWorld() {

    }
    
    @Override
    public void update(Observable observed, Object arg) {

    } 
}
