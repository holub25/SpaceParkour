import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class PlatformSkins extends ComponentSkin{
    private ArrayList<String> platformSkins;
    private String name;
    private int lastNum;
    private Shop shop;
    public PlatformSkins(int price, Type type,String name,int lastNum,Shop shop) {
        super(price, type);
        this.platformSkins = new ArrayList<>();
        this.name = name;
        this.lastNum = lastNum;
        this.shop = shop;
        addPlatformSkin();
    }
    public void setButtonActionPlat(SkinPanel panel,ArrayList<Platform> platforms,Player player,Game game){
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
                    /*for (Platform p : platforms){
                        p.setPlatformSkins(PlatformSkins.this);
                        p.addTexture();
                    }*/
                }else if(getType() == Type.BUY){
                    player.getCoinCounter().setCoinsCount(player.getCoinCounter().getCoinsCount()-getPrice());
                    shop.updateCoinText(player.getCoinCounter().getCoinsCount());
                    //game.getScore().getLabelNowS().setText("Score: "+player.getCoinCounter().getCoinsCount());
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
            platformSkins.add("skins\\platforms\\"+name+"\\"+name+"-"+i+".png");
        }
    }

    public ArrayList<String> getPlatformSkins() {
        return platformSkins;
    }
}
