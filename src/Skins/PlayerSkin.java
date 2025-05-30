package Skins;

import Components.Player.Player;
import Panels.Game.Game;
import Panels.Shop.Shop;
import Panels.Shop.SkinPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * PlayerSkin class represents a player skin, including animations for moving left and right.
 * It allows setting button actions for buying and equipping the skin.
 */
public class PlayerSkin extends ComponentSkin {
    private ImageIcon mainSkin;
    private ArrayList<ImageIcon> moveRightSkins;
    private ArrayList<ImageIcon> moveLeftSkins;
    private int frameDelay;
    private int frameCounter;
    private int frameNow;
    private Shop shop;
    private Player player;

    public PlayerSkin(String name, String way, int price, Type type, Shop shop) {
        super(name,price, type);
        frameNow = 0;
        frameCounter = 0;
        frameDelay = 10;
        this.shop = shop;
        this.mainSkin = new ImageIcon(getClass().getResource(way));
        moveLeftSkins = new ArrayList<>();
        moveRightSkins = new ArrayList<>();
        addSkinsLeft(name);
        addSkinsRight(name);
    }

    /**
     * Sets action for the skin's button in the given SkinPanel.
     * When clicked, the skin is either equipped (if owned) or bought (if for sale).
     *
     * @param panel  skin panel
     * @param player player whose coins are modified
     */
    public void setButtonActionPlay(SkinPanel panel, Player player){
        getBuyButton().setActionList(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getType() == Type.OWN){
                    for(int i = 0;i<panel.getSkins().size();i++){
                        if(panel.getSkins().get(i).getType()== Type.EQUIP){
                            panel.getSkins().get(i).setType(Type.OWN);
                        }
                    }
                    setType(Type.EQUIP);
                    getNewSkin(player);
                }else if(getType() == Type.BUY){
                    player.getCoinCounter().setCoinsCount(player.getCoinCounter().getCoinsCount()-getPrice());
                    shop.updateCoinText(player.getCoinCounter().getCoinsCount());
                    setType(Type.OWN);
                }
                shop.updateButtons(player);
                shop.requestFocusInWindow();
                panel.repaint();
                panel.revalidate();
            }
        });
    }

    /**
     * Sets this skin as the current skin for the player.
     *
     * @param player player to set the skin for
     */
    public void getNewSkin(Player player){
        player.setPlayerSkin(this);
    }

    /**
     * Adds animation frames for moving left.
     *
     * @param name skin name
     */
    public void addSkinsLeft(String name){
        for(int i = 0;i<3;i++){
            moveLeftSkins.add(new ImageIcon(getClass().getResource("/skins/player/"+name+"/"+name+"Left"+(i+1)+".png")));
        }
    }

    /**
     * Adds animation frames for moving right.
     *
     * @param name skin name
     */
    public void addSkinsRight(String name){
        for(int i = 0;i<3;i++){
            moveRightSkins.add(new ImageIcon(getClass().getResource("/skins/player/"+name+"/"+name+"Right"+(i+1)+".png")));
        }
    }

    /**
     * Method to change animation frame depending on whether the player is moving.
     * If moving, animation advances according to frameDelay.
     * If not moving, stays on first frame.
     *
     * @param moving whether player is moving
     * @param skins animation frames list
     * @return current animation frame
     */
    private ImageIcon changeFrame(boolean moving, ArrayList<ImageIcon> skins) {
        if (moving) {
            frameCounter++;
            if (frameCounter >= frameDelay) {
                frameNow = (frameNow + 1) % skins.size();
                frameCounter = 0;
            }
        } else {
            frameNow = 0;
        }
        return skins.get(frameNow);
    }

    public ImageIcon getRightSkin(boolean moveRight){
        return changeFrame(moveRight,moveRightSkins);
    }
    public ImageIcon getLeftSkin(boolean moveLeft){
        return changeFrame(moveLeft,moveLeftSkins);
    }
    public ImageIcon getMainSkin(){
        return mainSkin;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
