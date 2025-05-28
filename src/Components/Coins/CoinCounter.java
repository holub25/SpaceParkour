package Components.Coins;

import Components.TextLabel;

import java.awt.*;

/**
 * This class is used to store and display the number of coins collected by the player.
 * It contains a coin counter and a text label showing the current coin count in the game.
 */
public class CoinCounter {
    private int coinsCount;
    private TextLabel coinsLabel;

    public CoinCounter(int coinsCount,int x,int y,int width, int height,int textSize) {
        this.coinsCount = coinsCount;
        this.coinsLabel = new TextLabel("coinsCount","COINS: "+coinsCount,x,y,width,height,textSize, Color.WHITE);
    }
    /**
     * Increments the coin count by one when a coin is collected by the player.
     */
    public void getOneCoin(){
        coinsCount++;
    }

    /**
     * Updates the text label based on the current number of coins.
     */
    public void updateText(){
        coinsLabel.setText("COINS: "+coinsCount);
    }

    public int getCoinsCount() {
        return coinsCount;
    }

    public void setCoinsCount(int coinsCount) {
        this.coinsCount = coinsCount;
    }

    public TextLabel getCoinsLabel() {
        return coinsLabel;
    }
}
