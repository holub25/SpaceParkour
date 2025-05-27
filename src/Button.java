import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

    public void setButtonsSkin(){
        String way = "skins\\button\\"+size+"\\"+size+"Button";
        ImageIcon enebaleIcon = new ImageIcon(way+"Ene.png");
        ImageIcon pressIcon = new ImageIcon(way+"EnePress.png");
        ImageIcon disableIcon = new ImageIcon(way+"Dis.png");
        this.setIcon(enebaleIcon);
        this.setPressedIcon(pressIcon);
        this.setDisabledIcon(disableIcon);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
    }
    public void setActionList(ActionListener actionListener){
        this.addActionListener(actionListener);
    }
}
