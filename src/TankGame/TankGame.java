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
        final TankListener keysOne = new TankListener();
        final TankListener keysTwo = new TankListener();
        /*Create sound manager*/
        /*Create actual game window for rendering*/
        window.addKeyListener(keysOne);
        window.addKeyListener(keysTwo);
        clock.addObserver(world);
        clock.addObserver(window);
        while(!isGameOver) {
            clock.tickClock();
        }
        
    }
    
}
