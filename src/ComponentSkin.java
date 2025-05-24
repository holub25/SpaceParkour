import javax.swing.*;
import java.awt.*;

public abstract class ComponentSkin {
    private JPanel shopIcon;
    private Button buyButton;
    private int price;
    private Type type;
    private ImageIcon icon;
    private JLabel background;

    public ComponentSkin(int price,Type type) {
        this.price = price;
        this.type = type;
        this.shopIcon = new JPanel();

    }
    public JPanel addShopIcon(int x,int y,int width, int height,String image){
        shopIcon.setBounds(x,y,width,height);
        shopIcon.setVisible(true);
        shopIcon.setBackground(Color.GREEN);
        this.icon = new ImageIcon(image);
        setBackGround();
        shopIcon.setLayout(null);
        buyButton = new Button("Buy",10,height-30,50,30,10);
        shopIcon.add(buyButton);
        shopIcon.add(background);
        return shopIcon;
    }
    public void typeSet(int coins){
        if(type == Type.EQUIP || type == Type.OWN){
            System.out.println("4");
            if(type == Type.EQUIP){
                System.out.println("5");
                buyButton.setEnabled(false);
                buyButton.setText("EQUIPED");
            }else if(type == Type.OWN){
                System.out.println("6");
                buyButton.setEnabled(true);
                buyButton.setText("EQUIP");
            }

        }else {
            System.out.println("1");
            if(price>coins){
                System.out.println("2");
                type = Type.EXPENSIVE;
                buyButton.setEnabled(false);
                buyButton.setText("BUY");
            }else if(price<=coins){
                System.out.println("3");
                type = Type.BUY;
                buyButton.setEnabled(true);
                buyButton.setText("BUY");
            }
        }
    }
    public void setBackGround(){
        this.background = new JLabel();
        this.background.setVisible(true);
        this.background.setBounds(0,0,180,180);
        this.background.setIcon(icon);
        this.background.setLayout(null);
    }

    public Button getBuyButton() {
        return buyButton;
    }


    public Type getType() {
        return type;
    }
    public void setShopIcon(JPanel shopIcon) {
        this.shopIcon = shopIcon;
    }
}
