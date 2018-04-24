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
        final TankListener listener = new TankListener();
        //TODO: Create sound manager
        //window.addKeyListener(listener);
        clock.addObserver(world);
        clock.addObserver(window);
        while(!isGameOver) {
            clock.tickClock();
        }
        
    }
    
}
