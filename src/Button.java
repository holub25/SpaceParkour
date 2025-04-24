import javax.swing.*;
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
    }

    public void setActionList(ActionListener actionListener){
        this.addActionListener(actionListener);
    }
}
