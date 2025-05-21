import javax.swing.*;
import java.awt.*;

public class SkinPanel extends JPanel {

    public SkinPanel(Color color) {
        panelSettings(color);
    }
    public void panelSettings(Color color){
        this.setVisible(true);
        this.setBackground(color);
    }
}
