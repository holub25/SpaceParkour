package Components;

import javax.swing.*;
import java.awt.*;

/**
 * Custome Label that displays text at a given position with a specific style and color.
 */
public class TextLabel extends JLabel {
    private String name;
    public TextLabel(String name,String text, int x, int y, int width, int height, int textSize, Color color) {
        this.name = name;
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setText(text);
        this.setFont(new Font("Space Bd BT",Font.BOLD,textSize));
        this.setForeground(color);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.repaint();
        this.revalidate();
    }
    @Override
    public String getName() {
        return name;
    }
}
