import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop extends JPanel implements KeyListener {
    private Frame frame;
    private HashMap<String,Button> buttons;
    private JPanel shopPanel;
    private CardLayout cardLayout;

    public Shop(Frame frame) {
        cardLayout = new CardLayout();
        shopPanel = new JPanel(cardLayout);
        this.frame = frame;
        this.buttons = new HashMap<>();
        this.add(shopPanel);
        shopPanel.setBounds(10,100,550,600);
        shopPanel.setFocusable(true);
        addSkinsPanel();
        //cardLayout.show(shopPanel,"playerSkins");
        putButons();
        setButtons();
        panelSettings();
    }
    public void addSkinsPanel(){
        SkinPanel playerSkins = new SkinPanel(Color.RED);
        playerSkins.setName("playerSkins");
        shopPanel.add("playerSkins", playerSkins);

        SkinPanel platformSkins = new SkinPanel(Color.GRAY);
        platformSkins.setName("platformSkins");
        shopPanel.add("platformSkins", platformSkins);

        SkinPanel backgroundSkins = new SkinPanel(Color.YELLOW);
        backgroundSkins.setName("backgroundSkins");
        shopPanel.add("backgroundSkins", backgroundSkins);
    }
    public void addCoinLabel(){
        this.add(frame.getGame().getPlayer().getCoinCounter().getCoinsLabel());
    }
    public void addButtons(){
        for(Button button : buttons.values()){
            this.add(button);
        }
    }
    public void putButons(){
        buttons.put("playerSkins",new Button("Player",10,50,200,50,20));
        buttons.put("platformSkins",new Button("Platforms",220,50,200,50,20));
        buttons.put("backgroundSkins",new Button("Background",430,50,200,50,20));
        addButtons();
    }

    public void panelSettings(){
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.repaint();
        this.revalidate();
    }
    public void setButtons(){
        for(String name : buttons.keySet()){
            for(Component panel : shopPanel.getComponents()){
                if(name.equalsIgnoreCase(panel.getName())){
                    buttons.get(name).setActionList(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(shopPanel,name);
                        }
                    });
                }
            }
        }
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
