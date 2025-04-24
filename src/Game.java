import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    public Game(Frame frame) {
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setBackground(Color.BLUE);
    }
}
