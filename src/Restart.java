import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Restart extends JPanel {
    private Frame frame;
    private HashMap<String,Button> buttons;

    public Restart(Frame frame) {
        this.frame = frame;
        this.buttons = new HashMap<>();
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setLayout(null);
        putButtons();
        setButtons();
        this.add(frame.backgr("images\\space.png"));
        this.repaint();
        this.revalidate();
    }
    public void bigRestart(){
        for(Component panel : frame.getMainPanel().getComponents()){
            if(panel instanceof Game){
                frame.getMainPanel().remove(panel);
            }
        }
        Game newGame = new Game(frame);
        frame.getMainPanel().add(newGame,"game");
    }
    public void putButtons(){
        buttons.put("restart",new Button("Restart",250,200,150,100));
        buttons.put("menu",new Button("Menu",250,400,150,100));
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
                case "restart":
                    buttons.get(name).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            bigRestart();
                            frame.getCardLayout().show(frame.getMainPanel(),"game");
                            for(Component panel:frame.getMainPanel().getComponents()){
                                if(panel instanceof Game game){
                                    game.requestFocusInWindow();
                                    game.getTimer().start();
                                }
                            }
                        }
                    });
                    break;
                case "menu":
                    buttons.get(name).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            bigRestart();
                            frame.getCardLayout().show(frame.getMainPanel(),"menu");
                        }
                    });
                    break;
            }
        }
    }
}
