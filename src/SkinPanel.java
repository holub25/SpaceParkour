import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SkinPanel extends JPanel implements KeyListener {
    private Frame frame;
    private ArrayList<ComponentSkin> skins;
    public SkinPanel(Color color,Frame frame) {
        panelSettings(color);
        this.frame = frame;
        this.skins = new ArrayList<>();
    }
    public void panelSettings(Color color){
        this.setVisible(true);
        this.setBackground(color);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.repaint();
        this.revalidate();
    }

    public ArrayList<ComponentSkin> getSkins() {
        return skins;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                frame.getCardLayout().show(frame.getMainPanel(),"menu");
        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
