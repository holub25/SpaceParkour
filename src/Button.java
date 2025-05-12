import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class Button extends JButton {
    private int x;
    private int y;
    private int width;
    private int height;
    //private ActionListener actionListener;
    public Button(String text,int x, int y, int width, int height) {
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setText(text);
        this.setFont(new Font("Space Bd BT",Font.BOLD,30));
        this.setBackground(Color.CYAN);
        this.repaint();
        this.revalidate();
    }
    public void setActionList(ActionListener actionListener){
        this.addActionListener(actionListener);
    }
}
