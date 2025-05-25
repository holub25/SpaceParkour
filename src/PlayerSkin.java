import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerSkin extends ComponentSkin{
    private ImageIcon mainSkin;
    private ArrayList<ImageIcon> moveRightSkins;
    private ArrayList<ImageIcon> moveLeftSkins;
    private int frameDelay;
    private int frameCounter;
    private int frameNow;

    public PlayerSkin(String name,String way, int price, Type type) {
        super(price, type);
        frameNow = 0;
        frameCounter = 0;
        frameDelay = 10;
        this.mainSkin = new ImageIcon(way);
        moveLeftSkins = new ArrayList<>();
        moveRightSkins = new ArrayList<>();
        addSkinsLeft(name);
        addSkinsRight(name);
    }
    public void addSkinsLeft(String name){
        for(int i = 0;i<3;i++){
            moveLeftSkins.add(new ImageIcon("skins//player//"+name+"//"+name+"Left"+(i+1)+".png"));
        }
    }
    public void addSkinsRight(String name){
        for(int i = 0;i<3;i++){
            moveRightSkins.add(new ImageIcon("skins//player//"+name+"//"+name+"Right"+(i+1)+".png"));
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
    public ImageIcon getRightSkin(boolean moveRight){
        return changeFrame(moveRight,moveRightSkins);
    }
    public ImageIcon getLeftSkin(boolean moveLeft){
        return changeFrame(moveLeft,moveLeftSkins);
    }
    public ImageIcon getMainSkin(){
        return mainSkin;
    }
}
