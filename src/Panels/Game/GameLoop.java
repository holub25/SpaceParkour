package Panels.Game;

/**
 * Main game loop running in a separate thread.
 */
public class GameLoop extends Thread{

    private boolean running;
    private GameLogic gameLogic;

    public GameLoop(GameLogic gameLogic) {

        this.running = false;
        this.gameLogic = gameLogic;
    }

    /**
     * The run method starts the loop that calls updateLogic.
     */
    @Override
    public void run() {

        running = true;

        int fps = 60;
        long frameTime = 1000 /fps;

        while (running){
            long start = System.currentTimeMillis();
            gameLogic.updateLogic();
            long after = System.currentTimeMillis() - start;
            long sleep = frameTime - after;

            if(sleep>0){
                try{
                    Thread.sleep(sleep);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Stops the game loop.
     */
    public void stopRun(){
        running = false;
    }
}
