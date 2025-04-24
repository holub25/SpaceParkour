import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private int width = 650;
    private int height = 800;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Frame(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(40,40,width,height);
        this.setVisible(true);
        this.setResizable(false);
        this.add(new Menu(this));
    }
    public void addPanels(){
        
    }

    public JLabel backgr(String image){
        ImageIcon background = new ImageIcon(image);
        JLabel label = new JLabel(background);
        label.setBounds(0,0,width,height);
        label.setVisible(true);
        return label;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
