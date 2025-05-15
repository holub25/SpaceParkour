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
        labelBestS = new TextLabel("Best score: "+bestScore,40,40,130,80,20, Color.WHITE);
        labelNowS = new TextLabel("Score: "+playerScore,40,40,130,80,18,Color.WHITE);
    }
    public void updateScore(){
        labelNowS.setText("Score: " + playerScore);
    }
    public void setBestScore(){

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

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
