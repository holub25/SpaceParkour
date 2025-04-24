import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends JLabel {
    private int x;
    private int y;
    private int speed;


    public Player(int x,int y,int width, int height,int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.setFocusable(true);

    }
    public void moveLeft(){
        this.setLocation((x = x-speed),y);
        this.repaint();
    }
    public void moveRight(){
        this.setLocation((x = x+speed),y);
        this.repaint();
    }
    public void moveUp(){
        this.setLocation(x,(y = y-speed));
        this.repaint();
    }
    public void moveDown(){
        this.setLocation(x,(y = y+speed));
        this.repaint();
    }


}
