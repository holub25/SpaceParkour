package Components;

import Skins.PlatformSkins;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Represents a game platform that the player can stand on.
 * Implements Comparable to allow comparison by height.
 */
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
        //this.setBackground(Color.GREEN);
        this.setOpaque(false);
    }

    /**
     * Randomly selects and sets the platform's texture from the available skins.
     */
    public void addTexture(){
        int randomNum = rd.nextInt(platformSkins.getPlatformSkins().size());
        icon = new ImageIcon(platformSkins.getPlatformSkins().get(randomNum));
        this.setIcon(icon);
    }

    public void setPlatformSkins(PlatformSkins platformSkins) {
        this.platformSkins = platformSkins;
    }

    /**
     * Moves the platform to the right by the given speed
     *
     * @param speed of the movment
     */
    public void moveRight(int speed){
        this.setLocation(getX()-speed,getY());
    }

    /**
     * Moves the platform to the left by the given speed
     *
     * @param speed of the movment
     */
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

    /**
     * Compares two platforms based on their Y coordinate.
     * Useful for finding the highest or lowest platform.
     *
     * @param p the object to be compared.
     * @return -1 or 0 or 1
     */
    @Override
    public int compareTo(Platform p) {
        return Integer.compare(this.getY(),p.getY());
    }
}
