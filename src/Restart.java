import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void panelSettings() {
        this.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        this.add(frame.backgr(frame.getGameBackground().getBackgroundSkin().getWay()));
        this.setVisible(true);
        this.setLayout(null);
        this.repaint();
        this.revalidate();
    }

    public void record() {
        bestScore = 0;
        for (Component panel : frame.getMainPanel().getComponents()) {
            if (panel instanceof Menu menu) {
                for (int i = 0; i < texts.size(); i++) {
                    if (texts.get(i).getName().equals("record")) {
                        if (menu.getScore().setBestScore()) {
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
    public void saveCoins(int coins){
        this.lastMoney = coins;
    }


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
        frame.getShop().updatePlayerReferences(newGame.getPlayer(),newGame);
        System.out.println(newGame.getPlayer().getCoinCounter().getCoinsCount()+"PPP");
        frame.getMainPanel().add(newGame, "game");
        frame.getShop().equipSkins(newGame);
        frame.getShop().equipPlatformSkins(newGame);
        frame.getShop().equipBackground(frame);
    }

    public void putButtons() {
        buttons.put("restart", new Button("Restart", 250, 200, 150, 100,30,"big"));
        buttons.put("menu", new Button("Menu", 250, 400, 150, 100,30,"big"));
        addButtons();
    }

    public void addButtons() {
        for (Button button : buttons.values()) {
            button.setButtonsSkin();
            this.add(button);
        }
    }

    public void addTextsList() {
        texts.add(new TextLabel("fail", "FAIL", 25, 30, 600, 100, 40, Color.WHITE));
        texts.add(new TextLabel("record", "NEW RECORD: " + bestScore, 250, 100, 150, 80, 18, Color.YELLOW));
        addTexts();
    }

    public void addTexts() {
        for (TextLabel textLabel : texts) {
            this.add(textLabel);
        }
    }

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
