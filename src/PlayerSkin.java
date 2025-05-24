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
    /*//private JPanel shopIcon;
    //private Button buyButton;
    private int price;
    private Type type;
    private ImageIcon icon;
    private JLabel background;*/

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
    /*public void setBackGround(){
        this.background = new JLabel();
        this.background.setVisible(true);
        this.background.setBounds(0,0,180,180);
        this.background.setIcon(icon);
        this.background.setLayout(null);
    }*/

    /*public JPanel addShopIcon(int x,int y,int width, int height,String image){
        shopIcon.setBounds(x,y,width,height);
        shopIcon.setVisible(true);
        shopIcon.setBackground(Color.GREEN);
        this.icon = new ImageIcon(image);
        setBackGround();
        shopIcon.setLayout(null);
        //buyButton = new Button("Buy",10,height-30,50,30,10);
        //shopIcon.add(buyButton);
        shopIcon.add(background);
        return shopIcon;
    }*/
    public void addSkinsLeft(String name){
        for(int i = 0;i<3;i++){
            moveLeftSkins.add(new ImageIcon("skins//"+name+"Left"+(i+1)+".png"));
        }
    }
    public void addSkinsRight(String name){
        for(int i = 0;i<3;i++){
            moveRightSkins.add(new ImageIcon("skins//"+name+"Right"+(i+1)+".png"));
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
