package Components.Coins;

import javax.swing.*;
        import java.awt.*;

public class Coin extends JLabel {
    private int x;
    private int y;
    private int height;
    private int width;
    public Coin(int x,int y,int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        labelSettings();
    }
    public void moveRight(int speed){
        this.setLocation(getX()-speed,getY());
    }
    public void moveLeft(int speed){
        this.setLocation(getX()+speed,getY());
    }
    public void labelSettings(){
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setBackground(Color.CYAN);
        this.setOpaque(true);
    }
}
