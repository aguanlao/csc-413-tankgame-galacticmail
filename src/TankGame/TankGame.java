package TankGame;

public class TankGame {
    private static boolean isWon, isGameOver, isFinished;
    
    public TankGame() {
        isWon = false;
        isGameOver = false;
        isFinished = false;
    }

    public static void main(String[] args) {
        final GameClock clock = new GameClock();
        final TankListener keys = new TankListener();
        final GameWorld world = new GameWorld(keys);
        final Window window = new Window(world);
        //TODO: Create sound manager
        window.addKeyListener(keys);
        clock.addObserver(world);
        clock.addObserver(window);
        
        Thread clockThread = new Thread(clock);
        clockThread.start();
        
    }
    
}
