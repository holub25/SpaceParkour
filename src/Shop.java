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
    private SkinPanel playerSkinsPan;
    private SkinPanel platformSkinsPan;
    private SkinPanel backgroundSkinsPan;

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
        addSkisPlayerOnPanel();
        setButtons();
        updateButtons(frame.getGame().getPlayer());
        //buutonSet();
        updatePlayerReferences(frame.getGame().getPlayer(),frame.getGame());
        panelSettings();
    }
    public void addSkinsPanel(){
        playerSkinsPan = new SkinPanel(Color.RED,frame);
        playerSkinsPan.setLayout(null);
        playerSkinsPan.setName("playerSkins");
        shopPanel.add("playerSkins", playerSkinsPan);

        platformSkinsPan = new SkinPanel(Color.GRAY,frame);
        platformSkinsPan.setLayout(null);
        platformSkinsPan.setName("platformSkins");
        shopPanel.add("platformSkins", platformSkinsPan);

        backgroundSkinsPan = new SkinPanel(Color.YELLOW,frame);
        backgroundSkinsPan.setLayout(null);
        backgroundSkinsPan.setName("backgroundSkins");
        shopPanel.add("backgroundSkins", backgroundSkinsPan);
    }
    public void addSkisPlayerOnPanel(){
        int x = 10;
        int y = 10;
        int width = 180;
        int height = 180;
        int round = 1;
        addSkinsOnPanel(x,y,width,height,round,playerSkinsPan,"skin","player");
        addSkinsOnPanel(x,y,width,height,round,platformSkinsPan,"platformSkin","platforms");
        addSkinsOnPanel(x,y,width,height,round,backgroundSkinsPan,"backgroundSkin","background");
    }
    public void addSkinsOnPanel(int x, int y, int width,int height, int round,SkinPanel panel,String name,String whichSkin){
        for(ComponentSkin playerSkin : panel.getSkins()){
            panel.add(playerSkin.addShopIcon(x,y,width,height,"skins\\"+whichSkin+"\\"+name+round+"\\"+name+round+"Shop.png"));
            //buttonSettings(playerSkin);
            round++;
            if(round==3){
                y = 200;
            }
            x = x+190;
        }
    }

    public void addSkins(){
        playerSkinsPan.getSkins().add(new PlayerSkin("skin1","skins\\player\\skin1\\skin1.png",0,Type.EQUIP,this));
        playerSkinsPan.getSkins().add(new PlayerSkin("skin2","skins\\player\\skin2\\skin2.png",5,Type.EXPENSIVE,this));
        platformSkinsPan.getSkins().add(new PlatformSkins(0,Type.EQUIP,"platformSkin1",4,this));
        platformSkinsPan.getSkins().add(new PlatformSkins(10,Type.EXPENSIVE,"platformSkin2",4,this));
        backgroundSkinsPan.getSkins().add(new BackgroundSkin(0,Type.EQUIP,"backgroundSkin1",this,frame));
        backgroundSkinsPan.getSkins().add(new BackgroundSkin(5,Type.EXPENSIVE,"backgroundSkin2",this,frame));
    }
    public void updateButtons(Player player){
        for(int i = 0;i<playerSkinsPan.getSkins().size();i++){
            playerSkinsPan.getSkins().get(i).typeSet(player.getCoinCounter().getCoinsCount());
        }
        for (int i = 0;i<platformSkinsPan.getSkins().size();i++){
            platformSkinsPan.getSkins().get(i).typeSet(player.getCoinCounter().getCoinsCount());
        }
        for(int i = 0;i<backgroundSkinsPan.getSkins().size();i++){
            backgroundSkinsPan.getSkins().get(i).typeSet(player.getCoinCounter().getCoinsCount());
        }
    }
    public void updatePlayerReferences(Player newPlayer,Game newGame) {
        for (ComponentSkin skin : playerSkinsPan.getSkins()) {
            if (skin instanceof PlayerSkin ps) {
                ps.setPlayer(newPlayer);
                ps.setButtonActionPlay(playerSkinsPan, newPlayer,newGame);
            }
        }
        for(ComponentSkin skin : platformSkinsPan.getSkins()){
            if(skin instanceof PlatformSkins platformSkins){
                platformSkins.setButtonActionPlat(platformSkinsPan,frame.getGame().getPlatforms(),frame.getGame().getPlayer(),frame.getGame());
            }
        }
        for(ComponentSkin skin : backgroundSkinsPan.getSkins()){
            if(skin instanceof BackgroundSkin backgroundSkin){
                backgroundSkin.setButtonActionBack(backgroundSkinsPan,frame.getGame().getPlayer());
            }
        }
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
            button.setButtonsSkin();
            this.add(button);
        }
    }
    public void putButons(){
        buttons.put("playerSkins",new Button("Player",10,50,200,50,20,"medium"));
        buttons.put("platformSkins",new Button("Platforms",220,50,200,50,20,"medium"));
        buttons.put("backgroundSkins",new Button("Background",430,50,200,50,20,"medium"));
        addButtons();
    }

    public void panelSettings(){
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        //this.add(frame.getGameBackground());
        this.repaint();
        this.revalidate();
    }
    public void equipBackground(Frame frame){
        for(ComponentSkin componentSkin : backgroundSkinsPan.getSkins()){
            if(componentSkin instanceof BackgroundSkin bs){
                if(bs.getType() == Type.EQUIP){
                    frame.getGameBackground().setBackgroundSkin(bs);
                    frame.getGameBackground().addTexture();
                }
            }
        }
    }

    public void equipSkins(Game game){
        for(ComponentSkin componentSkin : playerSkinsPan.getSkins()){
            if(componentSkin instanceof PlayerSkin ps){
                if(ps.getType()==Type.EQUIP){
                    game.getPlayer().setPlayerSkin(ps);
                }
            }
        }

    }
    public void equipPlatformSkins(Game game){
        for(ComponentSkin componentSkin : platformSkinsPan.getSkins()){
            if(componentSkin instanceof PlatformSkins platformSkins){
                if(platformSkins.getType()==Type.EQUIP){
                    for(int i = 0;i<game.getPlatforms().size();i++){
                        System.out.println("jaj "+game.getPlatforms().size());
                        game.getPlatforms().get(i).setPlatformSkins(platformSkins);
                        game.getPlatforms().get(i).addTexture();
                    }
                }
            }
        }
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

    public SkinPanel getPlatformSkinsPan() {
        return platformSkinsPan;
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
