import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener {
    private Player player;

    public Game(Frame frame) {
        player = new Player(300,400,45,45,30);
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setBackground(Color.BLUE);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.add(player);
        this.repaint();
        this.revalidate();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                player.moveLeft();
                break;

            case KeyEvent.VK_D:
                player.moveRight();
                break;

            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_S:
                player.moveDown();
                break;
        }
        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                player.moveLeft();
                break;

            case KeyEvent.VK_D:
                player.moveRight();
                break;

            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_S:
                player.moveDown();
                break;
        }
        System.out.println("CHAR "+e.getKeyChar());
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("CHAR "+e.getKeyChar());

    }
}
