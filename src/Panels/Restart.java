package Panels;

import Panels.Game.Game;
import Panels.Shop.Shop;
import Frame.Frame;
import Components.TextLabel;
import Components.Background;
import Components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Restart panel shown after the player fails.
 * Provides options to restart the game or return to the main menu.
 */
public class Restart extends JPanel {
    private Frame frame;
    private HashMap<String, Button> buttons;
    private ArrayList<TextLabel> texts;
    private int bestScore;
    private int lastMoney;
    private Background background;
    public Restart(Frame frame) {
        this.frame = frame;
        this.buttons = new HashMap<>();
        this.texts = new ArrayList<>();
        this.lastMoney = 0;
        addTextsList();
        putButtons();
        setButtons();
        panelSettings();
    }

    /**
     * Configures panel size, layout and background.
     */
    public void panelSettings() {
        this.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        this.add(frame.backgr(frame.getGameBackground().getBackgroundSkin().getWay()));
        this.setVisible(true);
        this.setLayout(null);
        this.repaint();
        this.revalidate();
    }

    /**
     * Updates the record label to show "NEW RECORD" if the best score is achieved.
     */
    public void record() {
        bestScore = 0;
        for (Component panel : frame.getMainPanel().getComponents()) {
            if (panel instanceof Panels.Menu menu) {
                for (int i = 0; i < texts.size(); i++) {
                    if (texts.get(i).getName().equals("record")) {
                        if (menu.getScore().setBestScoreOnMenu()) {
                            texts.get(i).setText("NEW RECORD: "+menu.getScore().getBestScore());
                            texts.get(i).setVisible(true);
                        }else {
                            texts.get(i).setVisible(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Saves the number of coins collected before failure.
     *
     * @param coins number of coins
     */
    public void saveCoins(int coins){
        this.lastMoney = coins;
    }

    /**
     * Fully restarts the game: removes old game, creates a new one, reequips skins.
     */
    public void bigRestart() {
        for (Component panel : frame.getMainPanel().getComponents()) {
            if (panel instanceof Game) {
                frame.getMainPanel().remove(panel);
            } else if (panel instanceof Menu menu) {
                menu.getScore().setPlayerScore(0);
            }else if(panel instanceof Shop shop){
                shop.updateCoinText(lastMoney);
            }
        }
        Game newGame = new Game(frame);
        newGame.getPlayer().getCoinCounter().setCoinsCount(lastMoney);
        frame.setGame(newGame);
        frame.getShop().updatePlayerReferences(newGame.getPlayer());
        //System.out.println(newGame.getPlayer().getCoinCounter().getCoinsCount()+"PPP");
        frame.getMainPanel().add(newGame, "game");
        frame.getShop().equipSkins(newGame);
        frame.getShop().equipPlatformSkins(newGame);
        frame.getShop().equipBackground(frame);
    }

    /**
     * Initializes restart and menu buttons and adds them to the panel.
     */
    public void putButtons() {
        buttons.put("restart", new Button("Restart", 250, 200, 150, 100,30,"big"));
        buttons.put("menu", new Button("Menu", 250, 400, 150, 100,30,"big"));
        addButtons();
    }

    /**
     * Adds all buttons to the panel and applies skins.
     */
    public void addButtons() {
        for (Button button : buttons.values()) {
            button.setButtonsSkin();
            this.add(button);
        }
    }

    /**
     * Adds text labels to the panel.
     */
    public void addTextsList() {
        texts.add(new TextLabel("fail", "FAIL", 25, 30, 600, 100, 40, Color.WHITE));
        texts.add(new TextLabel("record", "NEW RECORD: " + bestScore, 250, 100, 150, 80, 15, Color.YELLOW));
        addTexts();
    }

    /**
     * Adds all text labels to the panel.
     */
    public void addTexts() {
        for (TextLabel textLabel : texts) {
            this.add(textLabel);
        }
    }

    /**
     * Assigns actions to restart and menu buttons.
     * Restart: fully restart the game
     * Menu: return to main menu
     */
    public void setButtons() {
        for (String name : buttons.keySet()) {
            switch (name) {
                case "restart":
                    buttons.get(name).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            bigRestart();
                            frame.getCardLayout().show(frame.getMainPanel(), "game");
                            for (Component panel : frame.getMainPanel().getComponents()) {
                                if (panel instanceof Game game) {
                                    game.requestFocusInWindow();
                                    game.startGame();
                                }
                            }
                        }
                    });
                    break;
                case "menu":
                    buttons.get(name).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            bigRestart();
                            frame.getCardLayout().show(frame.getMainPanel(), "menu");
                        }
                    });
                    break;
            }
        }
    }
}
