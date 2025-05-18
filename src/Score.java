import javax.swing.*;
import java.awt.*;

public class Score{
    private int playerScore;
    private int  bestScore;
    private TextLabel labelBestS;
    private TextLabel labelNowS;

    public Score() {
        this.playerScore = 0;
        this.bestScore = 0;
        labelBestS = new TextLabel("bestScore","Best score: "+bestScore,500,40,150,80,18, Color.WHITE);
        labelNowS = new TextLabel("scoreNow","Score: "+playerScore,40,40,150,80,18,Color.WHITE);
    }
    public void updateScore(){
        labelNowS.setText("Score: " + playerScore);
    }
    public void updateBestScore(){
        labelBestS.setText("Best score: "+bestScore);
    }
    public boolean setBestScore(){
        if(playerScore>bestScore){
            bestScore = playerScore;
            updateBestScore();
            return true;
        }else {
            return false;
        }
    }
    public int getPlayerScore() {
        return playerScore;
    }

    public TextLabel getLabelBestS() {
        return labelBestS;
    }

    public TextLabel getLabelNowS() {
        return labelNowS;
    }
    public void scoreCounter(){
        playerScore++;
        updateScore();
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
        updateScore();
    }
    public int getBestScore() {
        return bestScore;
    }
}
