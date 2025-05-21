import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class Button extends JButton {

    public Button(String text,int x, int y, int width, int height,int textSize) {
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setText(text);
        this.setFont(new Font("Space Bd BT",Font.BOLD,textSize));
        this.setBackground(Color.CYAN);
        this.repaint();
        this.revalidate();
    }
    public void setActionList(ActionListener actionListener){
        this.addActionListener(actionListener);
    }
}
