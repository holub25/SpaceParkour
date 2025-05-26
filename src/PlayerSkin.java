import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerSkin extends ComponentSkin{
    private ImageIcon mainSkin;
    private ArrayList<ImageIcon> moveRightSkins;
    private ArrayList<ImageIcon> moveLeftSkins;
    private int frameDelay;
    private int frameCounter;
    private int frameNow;
    private Shop shop;
    private Player player;

    public PlayerSkin(String name,String way, int price, Type type,Shop shop) {
        super(price, type);
        frameNow = 0;
        frameCounter = 0;
        frameDelay = 10;
        this.shop = shop;
        this.mainSkin = new ImageIcon(way);
        moveLeftSkins = new ArrayList<>();
        moveRightSkins = new ArrayList<>();
        addSkinsLeft(name);
        addSkinsRight(name);
    }
    public void setButtonActionPlay(SkinPanel panel,Player player,Game game){
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
                    getNewSkin(player);
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
    public void getNewSkin(Player player){
        player.setPlayerSkin(this);
    }

    public void addSkinsLeft(String name){
        for(int i = 0;i<3;i++){
            moveLeftSkins.add(new ImageIcon("skins\\player\\"+name+"\\"+name+"Left"+(i+1)+".png"));
        }
    }
    public void addSkinsRight(String name){
        for(int i = 0;i<3;i++){
            moveRightSkins.add(new ImageIcon("skins\\player\\"+name+"\\"+name+"Right"+(i+1)+".png"));
        }
    }
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

    public void setMainSkin(ImageIcon mainSkin) {
        this.mainSkin = mainSkin;
    }

    public void setMoveRightSkins(ArrayList<ImageIcon> moveRightSkins) {
        this.moveRightSkins = moveRightSkins;
    }

    public void setMoveLeftSkins(ArrayList<ImageIcon> moveLeftSkins) {
        this.moveLeftSkins = moveLeftSkins;
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
