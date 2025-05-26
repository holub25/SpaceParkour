import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgroundSkin extends ComponentSkin{
    private ImageIcon icon;
    private String way;
    private Shop shop;
    private Frame frame;
    public BackgroundSkin(String name,int price, Type type,Shop shop,Frame frame) {
        super(name,price, type);
        this.icon = new ImageIcon("skins\\background\\"+name+"\\"+name+".png");
        this.way = "skins\\background\\"+name+"\\"+name+".png";
        this.shop = shop;
        this.frame = frame;
    }

    public void setButtonActionBack(SkinPanel panel,Player player){
        getBuyButton().setActionList(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getType() == Type.OWN){
                    for(int i = 0;i<panel.getSkins().size();i++){
                        if(panel.getSkins().get(i).getType()==Type.EQUIP){
                            panel.getSkins().get(i).setType(Type.OWN);
                        }
                    }
                    setType(Type.EQUIP);
                    frame.setBackgrounds(way);
                    System.out.println(way);
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
    public String getWay() {
        return way;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
