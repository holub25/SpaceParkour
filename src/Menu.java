import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu extends JPanel {
    private Frame frame;
    private HashMap<String,Button> buttons;
    private ArrayList<Label> labels;

    public Menu(Frame frame) {
        buttons = new HashMap<>();
        labels = new ArrayList<>();
        this.frame = frame;
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setLayout(null);
        addLabelsList();
        addButtonsList();
        setButtons();
        this.add(frame.backgr("images\\space.png"));
        this.repaint();
        this.revalidate();
    }
    public void addLabelsList(){
        labels.add(new Label("SPACE PARKOUR",25,30,600,100,40,Color.WHITE));
        addLabels();
    }
    public void addLabels(){
        for(Label label : labels){
            this.add(label);
        }
    }
    public void addButtonsList(){
        buttons.put("Play",new Button("Play",250,200,150,100));
        buttons.put("Skins",new Button("Skins",250,400,150,100));
        addButtons();
    }
    public void addButtons(){
        for(Button button : buttons.values()){
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
                            frame.getCardLayout().show(frame.getMainPanel(),"game");
                            frame.getMainPanel().getComponent(1).requestFocusInWindow();
                        }
                    });
                break;
            }
        }
    }

}
