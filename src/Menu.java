import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu extends JPanel {
    private Frame frame;
    private HashMap<String,Button> buttons;
    private ArrayList<TextLabel> labels;
    private Score score;
    private Background background;


    public Menu(Frame frame) {
        buttons = new HashMap<>();
        labels = new ArrayList<>();
        this.frame = frame;
        this.score = new Score();
        this.add(score.getLabelBestS());
        addTextsList();
        putButtons();
        setButtons();
        panelSettings();
    }
    public void panelSettings(){
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setLayout(null);
        this.repaint();
        this.revalidate();
    }
    public void addTextsList(){
        labels.add(new TextLabel("title","SPACE PARKOUR",25,30,600,100,40,Color.WHITE));
        addTexts();
    }
    public void addTexts(){
        for(TextLabel label : labels){
            this.add(label);
        }
    }
    public void putButtons(){
        buttons.put("Play",new Button("Play",250,200,150,100,30,"big"));
        buttons.put("Shop",new Button("Shop",250,400,150,100,30,"big"));
        addButtons();
    }
    public void addButtons(){
        for(Button button : buttons.values()){
            button.setButtonsSkin();
            this.add(button);
        }
    }
    public void setButtons(){
        
        for(String name : buttons.keySet()){
            switch (name){
                case "Play":
                    buttons.get(name).setActionList(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(Component panel : frame.getMainPanel().getComponents()){
                                if(panel instanceof Game game){
                                    frame.getCardLayout().show(frame.getMainPanel(),"game");
                                    frame.getShop().equipSkins(game);
                                    frame.getShop().equipPlatformSkins(game);
                                    frame.getShop().equipBackground(frame);
                                    game.requestFocusInWindow();
                                    game.startGame();
                                }
                            }
                        }
                    });
                break;
                case "Shop":
                    buttons.get(name).setActionList(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(Component panel : frame.getMainPanel().getComponents()){
                                if(panel instanceof Shop shop){
                                    frame.getCardLayout().show(frame.getMainPanel(),"shop");
                                    shop.updateButtons(frame.getGame().getPlayer());
                                    shop.requestFocusInWindow();
                                }
                            }
                        }
                    });
                break;

            }
        }
    }


    public Score getScore() {
        return score;
    }
}
