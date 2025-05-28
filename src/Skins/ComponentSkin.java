package Skins;

import Components.Button;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class ComponentSkin represents the basic skin component,
 * which includes visual representation (icon), a buy button,
 * name, price, and skin type.
 */
public abstract class ComponentSkin {
    private JPanel shopIcon;
    private Components.Button buyButton;
    private String name;
    private int price;
    private Type type;
    private ImageIcon icon;
    private JLabel background;

    public ComponentSkin(String name, int price, Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.shopIcon = new JPanel();

    }

    /**
     * Creates a skin panel for the shop, sets position, size, and background.
     * Adds buy button and background icon.
     *
     * @param x panel X position
     * @param y panel Y position
     * @param width panel width
     * @param height panel height
     * @param image path to icon image
     * @return prepared skin panel for display
     */
    public JPanel addShopIcon(int x,int y,int width, int height,String image){
        shopIcon.setBounds(x,y,width,height);
        shopIcon.setVisible(true);
        shopIcon.setBackground(Color.GREEN);
        this.icon = new ImageIcon(image);
        setBackGround();
        shopIcon.setLayout(null);
        buyButton = new Components.Button("Buy",(width/2)-50,height-40,100,30,13,"small");
        buyButton.setButtonsSkin();
        shopIcon.add(buyButton);
        shopIcon.add(background);
        return shopIcon;
    }

    /**
     * Sets buyButton state based on skin type and player's coin count.
     * Updates button text and enabled state accordingly.
     *
     * @param coins current player's coin count
     */
    public void typeSet(int coins){
        if(type == Type.EQUIP || type == Type.OWN){
            System.out.println("U4");
            if(type == Type.EQUIP){
                System.out.println("U5");
                buyButton.setEnabled(false);
                buyButton.setText("EQUIPED");
            }else if(type == Type.OWN){
                System.out.println("U6");
                buyButton.setEnabled(true);
                buyButton.setText("EQUIP");
            }

        }else {
            System.out.println("U1");
            if(price>coins){
                System.out.println("U2");
                type = Type.EXPENSIVE;
                buyButton.setEnabled(false);
                buyButton.setText("BUY "+price);
            }else {
                System.out.println("U3");
                type = Type.BUY;
                buyButton.setEnabled(true);
                buyButton.setText("BUY "+price);
            }
        }
        shopIcon.repaint();
        shopIcon.revalidate();
    }

    /**
     * Sets background of the skin panel as a JLabel with the icon.
     */
    public void setBackGround(){
        this.background = new JLabel();
        this.background.setVisible(true);
        this.background.setBounds(0,0,180,180);
        this.background.setIcon(icon);
        this.background.setLayout(null);
    }

    public String getName() {
        return name;
    }

    public Button getBuyButton() {
        return buyButton;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }
}
