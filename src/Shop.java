import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Shop extends JPanel implements KeyListener {
    private Frame frame;
    private ArrayList<Button> buttons;

    public Shop(Frame frame) {
        this.frame = frame;
        this.buttons = new ArrayList<>();
        setButons();
        panelSettings();
    }
    public void addCoinLabel(){
        this.add(frame.getGame().getPlayer().getCoinCounter().getCoinsLabel());
    }
    public void addButtons(){
        for(int i = 0;i<buttons.size();i++){
            this.add(buttons.get(i));
        }
    }
    public void setButons(){
        buttons.add(new Button("Player",10,50,10,50));
        buttons.add(new Button("Platforms",110,50,10,50));
        buttons.add(new Button("Background",220,50,10,50));
        addButtons();
    }
    public void panelSettings(){
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.repaint();
        this.revalidate();
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
