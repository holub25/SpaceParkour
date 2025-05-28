package Components;

import Components.TextLabel;

import java.awt.*;

/**
 * Manages the player's current and best score, and displays them using the TextLabel component.
 */
public class Score{
    private int playerScore;
    private int  bestScore;
    private TextLabel labelBestS;
    private TextLabel labelNowS;

    public Score() {
        this.playerScore = 0;
        this.bestScore = 0;
        labelBestS = new TextLabel("bestScore","Best score: "+bestScore,250,100,150,80,18, Color.WHITE);
        labelNowS = new TextLabel("scoreNow","SCORE: "+playerScore,5,40,100,40,20,Color.WHITE);
    }

    /**
     * Updates the label showing the player's current score.
     */
    public void updateScore(){
        labelNowS.setText("Score: " + playerScore);
    }

    /**
     * Updates the label showing the best score.
     */
    public void updateBestScore(){
        labelBestS.setText("Best score: "+bestScore);
    }

    /**
     * If the current score is higher than the best, updates the best score and label.
     *
     * @return true if the best score was updated, false otherwise
     */
    public boolean setBestScoreOnMenu(){
        if(playerScore>bestScore){
            bestScore = playerScore;
            updateBestScore();
            return true;
        }else {
            return false;
        }
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
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

    /**
     * Increases the current score by 1 and updates the score label.
     */
    public void scoreCounter(){
        playerScore++;
        updateScore();
    }

    /**
     * Sets a new player score and updates the label.
     * @param playerScore the new player score
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
        updateScore();
    }
    public int getBestScore() {
        return bestScore;
    }
}
