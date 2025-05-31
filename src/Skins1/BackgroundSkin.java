package Skins1;

import Components.Player.Player;
import Frame.Frame;
import Panels.Shop.Shop;
import Panels.Shop.SkinPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * BackgroundSkin class represents a background skin in the game.
 * It holds information about the name, price, type, background image, and its path.
 */
public class BackgroundSkin extends ComponentSkin {
    private ImageIcon icon;
    private String way;
    private Shop shop;
    private Frame frame;
    public BackgroundSkin(String name, int price, Type type, Shop shop, Frame frame) {
        super(name,price, type);
        this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/skins/background/" + name + "/" + name + ".png")));
        this.way = "/skins/background/"+name+"/"+name+".png";
        this.shop = shop;
        this.frame = frame;
    }

    /**
     * Sets action for the skin's button in the given SkinPanel.
     * When clicked, the skin is either equipped (if owned) or bought (if for sale).
     *
     * @param panel panel containing the skins where the button is located
     * @param player player whose coins are updated when buying the skin
     */
    public void setButtonActionBack(SkinPanel panel, Player player){
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
                    frame.setBackgrounds(way);
                    //System.out.println(way);
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
    public String getWay() {
        return way;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
