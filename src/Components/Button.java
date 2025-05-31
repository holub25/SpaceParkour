package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Custom implementation of a button.
 * Supports setting the button's appearance using skins and attaching an action.
 */
public class Button extends JButton {
    private String size;

    public Button(String text,int x, int y, int width, int height,int textSize,String size) {
        this.size = size;
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setText(text);
        this.setFont(new Font("Space Bd BT",Font.BOLD,textSize));
        this.setForeground(Color.BLACK);
        UIManager.put("Button.disabledText", Color.BLACK);
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setBackground(Color.CYAN);
        this.repaint();
        this.revalidate();
    }

    /**
     * Sets the button's appearance based on the selected size (skin).
     * Uses specific images for normal, pressed, and disabled states.
     */

    public void setButtonsSkin(){
        String way = "/skins/button/"+size+"/"+size+"Button";
        ImageIcon enebaleIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(way + "Ene.png")));
        ImageIcon pressIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(way + "EnePress.png")));
        ImageIcon disableIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(way + "Dis.png")));
        this.setIcon(enebaleIcon);
        this.setPressedIcon(pressIcon);
        this.setDisabledIcon(disableIcon);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
    }

    /**
     * Adds an action listener that is triggered when the button is clicked.
     *
     * @param actionListener the event listener.
     */
    public void setActionList(ActionListener actionListener){
        this.addActionListener(actionListener);
    }
}
