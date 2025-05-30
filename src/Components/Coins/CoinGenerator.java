package Components.Coins;

import Components.Platform;
import Panels.Game.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class is responsible for generating coins above the highest platform in the game.
 * It generates coins randomly based on a given chance and adds them to a coin list.
 */
public class CoinGenerator {
    private ArrayList<Coin> coins;
    private Game game;
    private Random rd;
    private int chance;

    public CoinGenerator(Game game,int chance) {
        this.chance = chance;
        this.rd = new Random();
        this.coins = new ArrayList<>();
        this.game = game;
    }

    /**
     * Attempts to generate a coin above the highest platform in the game if a randomly generated number
     * is less than the defined chance. The coin is then added to the coin list.
     *
     * @param height coin height
     * @param width coin width
     */
    public void generator(int height,int width){
        int guess = rd.nextInt(100);
        ArrayList<Platform> platforms = game.getPlatforms();
        Platform highestPlatform = Collections.min(platforms);
        if(guess<chance){
            int y = highestPlatform.getY()-height;
            int x = highestPlatform.getX() + rd.nextInt(highestPlatform.getWidth() - width);
            Coin coin = new Coin(x,y,width,height);
            coins.add(coin);
        }
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
}
