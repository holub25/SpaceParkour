package Panels.Game;

import Panels.Game.Game;

public class GameLogic {
    private Game game;

    public GameLogic(Game game) {
        this.game = game;
    }
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
