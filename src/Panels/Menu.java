package Panels;

import Panels.Game.Game;
import Panels.Shop.Shop;
import Components.Score;
import Components.Background;
import Components.TextLabel;
import Components.Button;
import Frame.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The main menu panel of the game.
 * Displays the title, best score, and navigation buttons
 */
public class Menu extends JPanel {
    private Frame frame;
    private HashMap<String, Button> buttons;
    private ArrayList<TextLabel> labels;
    private Score score;
    private Background background;


    public Menu(Frame frame) {
        buttons = new HashMap<>();
        labels = new ArrayList<>();
        this.frame = frame;
        this.score = new Score();
        this.add(score.getLabelBestS());
        addTextsList();
        putButtons();
        setButtons();
        panelSettings();
    }

    /**
     * Configures basic panel settings like size, layout, and visibility.
     */
    public void panelSettings(){
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setLayout(null);
        this.repaint();
        this.revalidate();
    }

    /**
     * Adds the game title label to the menu.
     */
    public void addTextsList(){
        labels.add(new TextLabel("title","SPACE PARKOUR",25,30,600,100,40,Color.WHITE));
        addTexts();
    }

    /**
     * Adds all text labels to the panel.
     */
    public void addTexts(){
        for(TextLabel label : labels){
            this.add(label);
        }
    }

    /**
     * Creates and stores the menu buttons
     */
    public void putButtons(){
        buttons.put("Play",new Button("Play",250,200,150,100,30,"big"));
        buttons.put("Shop",new Button("Shop",250,400,150,100,30,"big"));
        addButtons();
    }

    /**
     * Adds all buttons to the panel and applies skin to each.
     */
    public void addButtons(){
        for(Button button : buttons.values()){
            button.setButtonsSkin();
            this.add(button);
        }
    }

    /**
     * Sets up action listeners for Play and Shop buttons.
     * Play - Starts the game.
     * Shop - Opens the shop panel.
     */
    public void setButtons(){

        for(String name : buttons.keySet()){
            switch (name){
                case "Play":
                    buttons.get(name).setActionList(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(Component panel : frame.getMainPanel().getComponents()){
                                if(panel instanceof Game game){
                                    frame.getCardLayout().show(frame.getMainPanel(),"game");
                                    frame.getShop().equipSkins(game);
                                    frame.getShop().equipPlatformSkins(game);
                                    frame.getShop().equipBackground(frame);
                                    game.requestFocusInWindow();
                                    game.startGame();
                                }
                            }
                        }
                    });
                break;
                case "Shop":
                    buttons.get(name).setActionList(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(Component panel : frame.getMainPanel().getComponents()){
                                if(panel instanceof Shop shop){
                                    frame.getCardLayout().show(frame.getMainPanel(),"shop");
                                    shop.updateButtons(frame.getGame().getPlayer());
                                    shop.requestFocusInWindow();
                                }
                            }
                        }
                    });
                break;

            }
        }
    }


    public Score getScore() {
        return score;
    }
}
