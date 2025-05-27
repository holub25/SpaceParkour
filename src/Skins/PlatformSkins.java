package Skins;

import Components.Platform;
import Components.Player.Player;
import Panels.Game.Game;
import Panels.Shop.Shop;
import Panels.Shop.SkinPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlatformSkins extends ComponentSkin {
    private ArrayList<String> platformSkins;
    private int lastNum;
    private Shop shop;
    public PlatformSkins(String name, int price, Type type, int lastNum, Shop shop) {
        super(name,price, type);
        this.platformSkins = new ArrayList<>();
        this.lastNum = lastNum;
        this.shop = shop;
        addPlatformSkin();
    }
    public void setButtonActionPlat(SkinPanel panel, Player player){
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
                }else if(getType() == Type.BUY){
                    player.getCoinCounter().setCoinsCount(player.getCoinCounter().getCoinsCount()-getPrice());
                    shop.updateCoinText(player.getCoinCounter().getCoinsCount());
                    setType(Type.OWN);

                }
                shop.updateButtons(player);
                panel.requestFocusInWindow();
                panel.repaint();
                panel.revalidate();
            }
        });
    }
    public void addPlatformSkin(){
        for(int i = 1;i<lastNum;i++){
            platformSkins.add("skins\\platforms\\"+getName()+"\\"+getName()+"-"+i+".png");
        }
    }

    public ArrayList<String> getPlatformSkins() {
        return platformSkins;
    }
}
