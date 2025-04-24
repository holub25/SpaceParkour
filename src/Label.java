import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {

    public Label(String text,int x, int y,int width,int height,int textSize,Color color) {
        this.setBounds(x,y,width,height);

        this.setVisible(true);
        this.setText(text);
        this.setFont(new Font("Space Bd BT",Font.BOLD,60));
        this.setForeground(color);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }
}
