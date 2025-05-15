import javax.swing.*;
import java.awt.*;

public class TextLabel extends JLabel {

    public TextLabel(String text, int x, int y, int width, int height, int textSize, Color color) {
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
}
