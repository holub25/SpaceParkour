import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Menu extends JPanel {
    private Frame frame;
    private HashMap<String,Button> buttons;

    public Menu(Frame frame) {
        buttons = new HashMap<>();
        this.frame = frame;
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setLayout(null);
        addButtonsList();
        addButtons();
        setButtons();
        this.repaint();
        this.revalidate();
    }
    public void addButtonsList(){
        buttons.put("Play",new Button("Play",100,100,200,100));
        buttons.put("Skins",new Button("Skins",300,300,200,100));
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
                        }
                    });
                break;
            }
        }
    }

}
