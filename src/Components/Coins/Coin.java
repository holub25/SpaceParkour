package Components.Coins;

import javax.swing.*;
        import java.awt.*;
import java.util.Objects;

/**
 * Represents a coin that the player can collect. Extends from JLabel for GUI rendering.
 */
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

    /**
     * Moves the coin to the right on the screen
     * @param speed Movement speed
     */
    public void moveRight(int speed){
        this.setLocation(getX()-speed,getY());
    }

    /**
     * Moves the coin to the left on the screen
     * @param speed Movement speed
     */
    public void moveLeft(int speed){
        this.setLocation(getX()+speed,getY());
    }

    /**
     * Initializes label properties (position, size, transparency, texture).
     * Called during coin instantiation.
     */
    public void labelSettings(){
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setOpaque(false);
        setCoinTexture();
    }

    /**
     * Sets the coin's image from a file
     */
    public void setCoinTexture(){
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/skins/coin/coin.png")));
        this.setIcon(icon);
    }
}
