package Components;

import Skins1.BackgroundSkin;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the background of the game scene.
 */
public class Background extends JLabel {
    private BackgroundSkin backgroundSkin;
    private Frame frame;


    public Background(Frame frame) {
        this.frame = frame;
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setOpaque(true);
        this.setBackground(Color.RED);
    }

    public BackgroundSkin getBackgroundSkin() {
        return backgroundSkin;
    }

    public void setBackgroundSkin(BackgroundSkin backgroundSkin){
        this.backgroundSkin = backgroundSkin;
    }

    /**
     * Sets the graphical texture of the background using the selected skin.
     */
    public void addTexture(){
        this.setIcon(backgroundSkin.getIcon());
    }
}
