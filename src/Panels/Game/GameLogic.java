package Panels.Game;

import Panels.Game.Game;

/**
 * Manages the game logic - calls methods to update the game state.
 */
public class GameLogic {
    private Game game;

    public GameLogic(Game game) {
        this.game = game;
    }

    /**
     * Updates game logic by calling appropriate methods in Game.
     */
    public void updateLogic(){
        game.startJump();
        game.verticalMove();
        game.sprint();
        game.horizonatlMove();
        game.palyerSettings();
        game.deletePlatform();
        game.platfomrGenerating();
        game.deadRestart();
        game.repaint();
    }
}
