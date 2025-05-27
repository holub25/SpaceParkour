package Components.Coins;

import Components.TextLabel;

import java.awt.*;

public class CoinCounter {
    private int coinsCount;
    private TextLabel coinsLabel;

    public CoinCounter(int coinsCount,int x,int y,int width, int height,int textSize) {
        this.coinsCount = coinsCount;
        this.coinsLabel = new TextLabel("coinsCount","COINS: "+coinsCount,x,y,width,height,textSize, Color.WHITE);
    }
    public void getOneCoin(){
        coinsCount++;
    }
    public void updateText(){
        coinsLabel.setText("COINS: "+coinsCount);
    }

    public int getCoinsCount() {
        return coinsCount;
    }

    public void setCoinsCount(int coinsCount) {
        this.coinsCount = coinsCount;
        System.out.println(coinsCount);
    }

    public TextLabel getCoinsLabel() {
        return coinsLabel;
    }
}
