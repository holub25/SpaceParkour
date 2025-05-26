import javax.swing.*;
import java.awt.*;


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

    public Frame(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        this.background = new Background(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(40,40,width,height);
        this.setFocusable(true);
        this.setTitle("Space Parkour");
        this.add(mainPanel);
        musicSettings();
        addPanels();
        cardLayout.show(mainPanel,"menu");
        mainPanel.setFocusable(true);
        this.setVisible(true);
        this.setResizable(false);
        this.revalidate();
        this.repaint();
    }

    public void musicSettings(){
        this.mainMusic = new Audio("Sounds//music.wav");
        this.mainMusic.setVolume(-15);
        this.mainMusic.loop();
    }
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
        setBackground(this.getGameBackground().getBackgroundSkin().getWay());
    }
    public void setBackground(String way){
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
    public void repainRevalidate(JPanel panel){
        panel.repaint();
        panel.revalidate();
    }

    private void removeOldBackground(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel label && label.getIcon() != null) {
                panel.remove(label);
                break;
            }
        }
    }
    public JLabel backgr(String image){
        ImageIcon background = new ImageIcon(image);
        JLabel label = new JLabel(background);
        label.setBounds(0,0,width,height);
        label.setVisible(true);
        return label;
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
