import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private int width = 650;
    private int height = 800;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Audio mainMusic;


    public Frame(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
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
        this.mainMusic.getClip().loop(Clip.LOOP_CONTINUOUSLY);
        this.mainMusic.setVolume(-35);
    }
    public void addPanels(){
        mainPanel.add("menu",new Menu(this));
        mainPanel.add("game",new Game(this));
        mainPanel.add("restart",new Restart(this));
    }
    public JLabel backgr(String image){
        ImageIcon background = new ImageIcon(image);
        JLabel label = new JLabel(background);
        label.setBounds(0,0,width,height);
        label.setVisible(true);
        return label;
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
}
