package Panels.Shop;

import Skins1.ComponentSkin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A panel that displays a list of skins for selection within the shop.
 */
public class SkinPanel extends JPanel{
    private ArrayList<ComponentSkin> skins;
    public SkinPanel() {
        panelSettings();
        this.skins = new ArrayList<>();
    }

    /**
     * Sets the panel's appearance and behavior.
     */
    public void panelSettings(){
        this.setVisible(true);
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.repaint();
        this.revalidate();
    }

    public ArrayList<ComponentSkin> getSkins() {
        return skins;
    }

}
