package Panels.Shop;

import Panels.Game.Game;
import Components.TextLabel;
import Components.Player.Player;
import Components.Button;
import Frame.Frame;
import Skins1.ComponentSkin;
import Skins1.PlatformSkins;
import Skins1.PlayerSkin;
import Skins1.BackgroundSkin;
import Skins1.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Shop panel in the game.
 * Allows the player to select, buy, and equip skins for the player, platforms, and background.
 */
public class Shop extends JPanel implements KeyListener {
    private Frame frame;
    private HashMap<String, Button> buttons;
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
        updatePlayerReferences(frame.getGame().getPlayer());
        panelSettings();
    }

    /**
     * Initializes separate panels for player, platform, and background skins and adds them to the shop.
     */
    public void addSkinsPanel(){
        playerSkinsPan = new SkinPanel();
        playerSkinsPan.setLayout(null);
        playerSkinsPan.setName("playerSkins");
        shopPanel.add("playerSkins", playerSkinsPan);

        platformSkinsPan = new SkinPanel();
        platformSkinsPan.setLayout(null);
        platformSkinsPan.setName("platformSkins");
        shopPanel.add("platformSkins", platformSkinsPan);

        backgroundSkinsPan = new SkinPanel();
        backgroundSkinsPan.setLayout(null);
        backgroundSkinsPan.setName("backgroundSkins");
        shopPanel.add("backgroundSkins", backgroundSkinsPan);
    }

    /**
     * Adds skin icons to the corresponding skin panels
     */
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

    /**
     * Adds individual skins to a given panel based on type and image paths.
     *
     * @param x starting X position
     * @param y starting Y position
     * @param width width of the skin icon
     * @param height height of the skin icon
     * @param round skin index
     * @param panel panel to which skins are added
     * @param name name prefix of the skin
     * @param whichSkin skin folder
     */
    public void addSkinsOnPanel(int x, int y, int width, int height, int round, SkinPanel panel, String name, String whichSkin){
        for(ComponentSkin playerSkin : panel.getSkins()){
            panel.add(playerSkin.addShopIcon(x,y,width,height,"/skins/"+whichSkin+"/"+name+round+"/"+name+round+"Shop.png"));
            round++;
            if(round==4){
                y = 200;
                x = 10;
            }
            x = x+190;
        }
    }

    /**
     * Adds new skins to the shop
     */
    public void addSkins(){
        playerSkinsPan.getSkins().add(new PlayerSkin("skin1","/skins/player/skin1/skin1.png",0, Type.EQUIP,this));
        playerSkinsPan.getSkins().add(new PlayerSkin("skin2","/skins/player/skin2/skin2.png",5, Type.EXPENSIVE,this));
        playerSkinsPan.getSkins().add(new PlayerSkin("skin3","/skins/player/skin3/skin3.png",10,Type.EXPENSIVE,this));
        platformSkinsPan.getSkins().add(new PlatformSkins("platformSkin1",0, Type.EQUIP,4,this));
        platformSkinsPan.getSkins().add(new PlatformSkins("platformSkin2",10, Type.EXPENSIVE,4,this));
        platformSkinsPan.getSkins().add(new PlatformSkins("platformSkin3",15,Type.EXPENSIVE,4,this));
        backgroundSkinsPan.getSkins().add(new BackgroundSkin("backgroundSkin1",0, Type.EQUIP,this,frame));
        backgroundSkinsPan.getSkins().add(new BackgroundSkin("backgroundSkin2",5, Type.EXPENSIVE,this,frame));
        backgroundSkinsPan.getSkins().add(new BackgroundSkin("backgroundSkin3",15,Type.EXPENSIVE,this,frame));
    }

    /**
     * Updates the button states based on the player's coin count
     *
     * @param player player whose coin count is used for checking availability
     */
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

    /**
     * Updates the player reference in all skin instances (player, platform, background).
     * Also sets the corresponding button actions for each skin type.
     *
     * @param newPlayer new player instance to be assigned to skin components
     */
    public void updatePlayerReferences(Player newPlayer) {
        for (ComponentSkin skin : playerSkinsPan.getSkins()) {
            if (skin instanceof PlayerSkin ps) {
                ps.setPlayer(newPlayer);
                ps.setButtonActionPlay(playerSkinsPan, newPlayer);
            }
        }
        for(ComponentSkin skin : platformSkinsPan.getSkins()){
            if(skin instanceof PlatformSkins platformSkins){
                platformSkins.setButtonActionPlat(platformSkinsPan,frame.getGame().getPlayer());
            }
        }
        for(ComponentSkin skin : backgroundSkinsPan.getSkins()){
            if(skin instanceof BackgroundSkin backgroundSkin){
                backgroundSkin.setButtonActionBack(backgroundSkinsPan,frame.getGame().getPlayer());
            }
        }
    }

    /**
     * Updates the coin label with the new coin amount.
     *
     * @param updateCoins current coin count
     */
    public void updateCoinText(int updateCoins){
        coins.setText("Coins: "+updateCoins);
    }

    /**
     * Adds the coin label showing current coin count to the panel.
     */
    public void addCoinLabel(){
        this.coins = new TextLabel("coins","Coins: " + frame.getGame().getPlayer().getCoinCounter().getCoinsCount(),10,10,200,50,20,Color.WHITE);
        this.add(coins);
    }

    /**
     * Adds skin category buttons to the panel.
     */
    public void addButtons(){
        for(Button button : buttons.values()){
            button.setButtonsSkin();
            this.add(button);
        }
    }

    /**
     * Initializes and adds all skin category buttons to the panel.
     */
    public void putButons(){
        buttons.put("playerSkins",new Button("Player",10,50,200,50,20,"medium"));
        buttons.put("platformSkins",new Button("Platforms",220,50,200,50,20,"medium"));
        buttons.put("backgroundSkins",new Button("Background",430,50,200,50,20,"medium"));
        addButtons();
    }

    /**
     * Sets basic panel properties such as size, layout, background color.
     */
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

    /**
     * Applies the equipped background skin to the game background.
     *
     * @param frame the main game frame
     */
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

    /**
     * Applies the equipped player skin to the current game.
     *
     * @param game the current game instance
     */
    public void equipSkins(Game game){
        for(ComponentSkin componentSkin : playerSkinsPan.getSkins()){
            if(componentSkin instanceof PlayerSkin ps){
                if(ps.getType()== Type.EQUIP){
                    game.getPlayer().setPlayerSkin(ps);
                }
            }
        }
    }

    /**
     * Applies the equipped platform skin to all active platforms in the game.
     *
     * @param game the current game instance
     */
    public void equipPlatformSkins(Game game){
        for(ComponentSkin componentSkin : platformSkinsPan.getSkins()){
            if(componentSkin instanceof PlatformSkins platformSkins){
                if(platformSkins.getType()== Type.EQUIP){
                    for(int i = 0;i<game.getPlatforms().size();i++){
                        //System.out.println("jaj "+game.getPlatforms().size());
                        game.getPlatforms().get(i).setPlatformSkins(platformSkins);
                        game.getPlatforms().get(i).addTexture();
                    }
                }
            }
        }
    }

    /**
     * Sets up actions for each skin category button to switch visible panels.
     */
    public void setButtons(){
        for(String name : buttons.keySet()){
            for(Component panel : shopPanel.getComponents()){
                if(name.equalsIgnoreCase(panel.getName())){
                    buttons.get(name).setActionList(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(shopPanel,name);
                            updateButtons(getFrame().getGame().getPlayer());
                            shopRequestFocus();
                        }
                    });
                }
            }
        }
    }

    public Frame getFrame() {
        return frame;
    }

    public void shopRequestFocus(){
        this.requestFocusInWindow();
    }

    public SkinPanel getPlatformSkinsPan() {
        return platformSkinsPan;
    }

    public SkinPanel getPlayerSkinsPan() {
        return playerSkinsPan;
    }

    public SkinPanel getBackgroundSkinsPan() {
        return backgroundSkinsPan;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles key press events, particularly ESC to return to the menu.
     *
     * @param e the event to be processed
     */
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
