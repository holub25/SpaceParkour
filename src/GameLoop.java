<<<<<<< HEAD
public class GameLoop implements Runnable{
=======
public class GameLoop extends Thread{
>>>>>>> origin/master
    private boolean running;
    private GameLogic gameLogic;

    public GameLoop(GameLogic gameLogic) {
<<<<<<< HEAD
        this.running = false;
=======
        this.running = true;
>>>>>>> origin/master
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {
<<<<<<< HEAD
        running = true;
=======
>>>>>>> origin/master
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
    public void stopRun(){
        running = false;
    }
<<<<<<< HEAD

=======
>>>>>>> origin/master
}
