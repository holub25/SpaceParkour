import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop extends JPanel implements KeyListener {
    private Frame frame;
    private HashMap<String,Button> buttons;
    private JPanel shopPanel;
    private TextLabel coins;
    private CardLayout cardLayout;
    private SkinPanel playerSkins;
    private SkinPanel platformSkins;
    private SkinPanel backgroundSkins;

    public Shop(Frame frame) {
        cardLayout = new CardLayout();
        shopPanel = new JPanel(cardLayout);
        this.frame = frame;
        this.buttons = new HashMap<>();
        this.add(shopPanel);
        shopPanel.setBounds(35,110,580,600);
        shopPanel.setFocusable(true);
        addCoinLabel();
        addSkinsPanel();
        putButons();
        addSkins();
        addSkisOnPanel();
        setButtons();
        buutonSet();
        panelSettings();
    }
    public void addSkinsPanel(){
        playerSkins = new SkinPanel(Color.RED,frame);
        playerSkins.setLayout(null);
        playerSkins.setName("playerSkins");
        shopPanel.add("playerSkins", playerSkins);

        platformSkins = new SkinPanel(Color.GRAY,frame);
        playerSkins.setLayout(null);
        platformSkins.setName("platformSkins");
        shopPanel.add("platformSkins", platformSkins);

        backgroundSkins = new SkinPanel(Color.YELLOW,frame);
        backgroundSkins.setLayout(null);
        backgroundSkins.setName("backgroundSkins");
        shopPanel.add("backgroundSkins", backgroundSkins);
    }
    public void addSkisOnPanel(){
        for(ComponentSkin playerSkin : playerSkins.getSkins()){
            if(playerSkin instanceof PlayerSkin ps){
                playerSkins.add(ps.addShopIcon(10,10,180,180,"skins//skin1Shop.png"));
            }
        }
    }

    public void addSkins(){
        playerSkins.getSkins().add(new PlayerSkin("skin1","skins//skin1.png",0,Type.EQUIP));
        //playerSkins.getSkins().add(new PlayerSkin("skin"))
    }
    public void updateCoinText(int updateCoins){
        coins.setText("Coins: "+updateCoins);
    }
    public void addCoinLabel(){
        this.coins = new TextLabel("coins","Coins: " + frame.getGame().getPlayer().getCoinCounter().getCoinsCount(),10,10,200,50,20,Color.WHITE);
        this.add(coins);
    }
    public void addButtons(){
        for(Button button : buttons.values()){
            this.add(button);
        }
    }
    public void putButons(){
        buttons.put("playerSkins",new Button("Player",10,50,200,50,20));
        buttons.put("platformSkins",new Button("Platforms",220,50,200,50,20));
        buttons.put("backgroundSkins",new Button("Background",430,50,200,50,20));
        addButtons();
    }
    public void buutonSet(){
        for(int i = 0;i<playerSkins.getSkins().size();i++){
            playerSkins.getSkins().get(i).typeSet(frame.getGame().getPlayer().getCoinCounter().getCoinsCount());
        }
    }

    public void panelSettings(){
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.repaint();
        this.revalidate();
    }

    public PlayerSkin equipPlayerSkin(){
        for(ComponentSkin playerSkin : playerSkins.getSkins()){
            if(playerSkin.getType() == Type.EQUIP){
                return (PlayerSkin) playerSkin;
            }
        }
        return null;
    }

    public void setButtons(){
        for(String name : buttons.keySet()){
            for(Component panel : shopPanel.getComponents()){
                if(name.equalsIgnoreCase(panel.getName())){
                    buttons.get(name).setActionList(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(shopPanel,name);
                            panel.requestFocusInWindow();
                        }
                    });
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                frame.getCardLayout().show(frame.getMainPanel(),"menu");
        }
        repaint();

    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
