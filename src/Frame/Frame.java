package Frame;

import Panels.Game.Game;
import Panels.Shop.Shop;
import Panels.Restart;
import Panels.Menu;
import Audio.Audio;
import Components.Background;
import Saves.Loader;
import Saves.Saver;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

/**
 * Main application window for the Space Parkour game.
 * Manages panels, background music, game saving/loading, and background display.
 */
public class Frame extends JFrame {
    private int width = 650;
    private int height = 800;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Audio mainMusic;
    private Game game;
    private Shop shop;
    private Menu menu;
    private Restart restart;
    private Background background;
    private Saver saver;
    private Loader loader;

    public Frame(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        this.background = new Background(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(40,40,width,height);
        this.setFocusable(true);
        this.setTitle("Space Parkour");
        iconSet("/skins/player/skin2/skin2.png");
        this.add(mainPanel);
        musicSettings();
        addPanels();
        cardLayout.show(mainPanel,"menu");
        mainPanel.setFocusable(true);
        loadGame();
        this.setVisible(true);
        this.setResizable(false);
        this.revalidate();
        this.repaint();
        endFrame();
    }

    /**
     * Set icon of the frame
     *
     * @param image path of image
     */
    public void iconSet(String image){
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource(image));
        this.setIconImage(icon);
    }


    /**
     * Saves the current game state.
     */
    public void saveGame(){
        this.saver = new Saver(menu,game.getPlayer(),shop);
    }

    /**
     * Loads the saved game state.
     */
    public void loadGame(){
        this.loader = new Loader(menu,game.getPlayer(),shop,this);
    }

    /**
     * Sets up and starts the main game music in a loop.
     */
    public void musicSettings(){
        this.mainMusic = new Audio("/Sounds/music.wav");
        this.mainMusic.setVolume(-15);
        this.mainMusic.loop();
    }

    /**
     * Initializes and adds main panels to the CardLayout.
     */
    public void addPanels(){
        menu = new Menu(this);
        mainPanel.add("menu",menu);
        game = new Game(this);
        mainPanel.add("game", game);
        shop = new Shop(this);
        mainPanel.add("shop",shop);
        shop.equipBackground(this);
        restart = new Restart(this);
        mainPanel.add("restart", restart);
        setBackgrounds(this.getGameBackground().getBackgroundSkin().getWay());
    }

    /**
     * Sets background images for specified panels.
     *
     * @param way path to the background image
     */
    public void setBackgrounds(String way){
        removeOldBackground(menu);
        removeOldBackground(shop);
        removeOldBackground(restart);
        menu.add(backgr(way));
        shop.add(backgr(way));
        restart.add(backgr(way));
        repainRevalidate(menu);
        repainRevalidate(shop);
        repainRevalidate(restart);
    }
    /**
     * Refreshes the panel by calling repaint and revalidate.
     *
     * @param panel JPanel to refresh
     */
    public void repainRevalidate(JPanel panel){
        panel.repaint();
        panel.revalidate();
    }

    /**
     * Removes old background from the panel.
     *
     * @param panel to remove background from
     */
    private void removeOldBackground(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel label && label.getIcon() != null) {
                panel.remove(label);
                break;
            }
        }
    }

    /**
     * Creates a JLabel with background set from given image path.
     * I implemented this to handle data saving when running the application as a JAR file and was
     * This uses getClass() and related methods to properly load data from within the JAR file when the application is packaged.
     * Inspired by Chat GPT
     *
     * @param image path to the image
     * @return JLabel with set background icon
     */
    public JLabel backgr(String image){
        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource(image)));
        JLabel label = new JLabel(background);
        label.setBounds(0,0,width,height);
        label.setVisible(true);
        return label;
    }

    /**
     * The game is saved when the window is closed
     */
    public void endFrame(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("END");
                saveGame();
                super.windowClosing(e);
            }
        });
    }

    public Game getGame() {
        return game;
    }

    public Shop getShop() {
        return shop;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }
    public CardLayout getCardLayout() {
        return cardLayout;
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    public Background getGameBackground() {
        return background;
    }
}
