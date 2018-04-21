package TankGame;

public class TankGame {
    private static boolean isWon, isGameOver, isFinished;
    
    public TankGame() {
        isWon = false;
        isGameOver = false;
        isFinished = false;
    }

    public static void main(String[] args) {
        final GameWorld world = new GameWorld();
        final GameClock clock = new GameClock();
        final Window window = new Window(world);
        /*Create sound manager*/
        /*Create actual game window for rendering*/
        
        clock.addObserver(world);
        clock.addObserver(window);
        
        while(!isGameOver) {
            clock.tickClock();
        }
        
    }
    
}
