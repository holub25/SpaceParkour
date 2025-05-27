package Components;

import Skins.PlatformSkins;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Platform extends JLabel implements Comparable<Platform> {
    private int x;
    private int y;
    private int width;
    private int height;
    private PlatformSkins platformSkins;
    private ImageIcon icon;
    private Random rd;

    public Platform(int x, int y, int width, int height) {
        rd = new Random();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setVisible(true);
        this.setBounds(x,y,width,height);
        this.setBackground(Color.GREEN);
        //this.platformSkins = new Skins.PlatformSkins(0,Skins.Type.EQUIP,"platformSkin1",4);
        //addBasicTexture();
        //addTexture();
        this.setOpaque(true);
    }
    public void addTexture(){
        int randomNum = rd.nextInt(platformSkins.getPlatformSkins().size());
        icon = new ImageIcon(platformSkins.getPlatformSkins().get(randomNum));
        this.setIcon(icon);
    }

    public void setPlatformSkins(PlatformSkins platformSkins) {
        this.platformSkins = platformSkins;
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
