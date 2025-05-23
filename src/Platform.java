import javax.swing.*;
import java.awt.*;

public class Platform extends JLabel implements Comparable<Platform> {
    private int x;
    private int y;
    private int width;
    private int height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setVisible(true);
        this.setBounds(x,y,width,height);
        this.setBackground(Color.GREEN);
        this.setOpaque(true);
    }
    public void moveRight(int speed){
        this.setLocation(getX()-speed,getY());
    }
    public void moveLeft(int speed){
        this.setLocation(getX()+speed,getY());
    }
    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public int compareTo(Platform p) {
        return Integer.compare(this.getY(),p.getY());
    }
}
