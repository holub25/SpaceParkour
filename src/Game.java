import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {
    private Player player;
    private Frame frame;
    private ArrayList<Platform> platforms;
    private Timer timer;
    private Generator generator;
    private boolean isJumpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isSpeedPressed;
    private Score score;

    public Game(Frame frame) {
        this.timer = new Timer(1,this);
        this.frame = frame;
        this.platforms = new ArrayList<>();
        this.setScore();
        this.player = new Player(300,400,29,45,5,10,-25);
        this.generator = new Generator(score);
        Platform firstPlatform = new Platform(10,800,500,20);
        this.add(score.getLabelNowS());
        platforms.add(firstPlatform);
        this.add(firstPlatform);
        panelSettings();
    }
    public void panelSettings(){
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setBackground(Color.BLUE);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.add(player);
        this.add(frame.backgr("images\\space.png"));
        this.repaint();
        this.revalidate();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                isLeftPressed = true;
                break;
            case KeyEvent.VK_D:
                isRightPressed = true;
                break;
            case KeyEvent.VK_W:
                isJumpPressed = true;
                break;
            case KeyEvent.VK_SHIFT:
                isSpeedPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                isJumpPressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                frame.getCardLayout().show(frame.getMainPanel(),"menu");

        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                isLeftPressed = false;
                break;
            case KeyEvent.VK_D:
                isRightPressed = false;
                break;
            case KeyEvent.VK_W:
                isJumpPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                isJumpPressed = false;
                break;
            case KeyEvent.VK_SHIFT:
                isSpeedPressed = false;
                break;
        }
        repaint();
    }

    public void deletePlatform(){
        for(int i = 0;i<platforms.size();i++){
            if(platforms.get(i).getY()>1000){
                this.remove(platforms.get(i));
                platforms.remove(platforms.get(i));
                score.scoreCounter();
            }
        }
    }

    public void gameGravity(){
        boolean colision = false;
        for(int i = 0;i<platforms.size();i++){
            Rectangle newPosition = new Rectangle(platforms.get(i).getX(),platforms.get(i).getY()-5,platforms.get(i).getWidth(),platforms.get(i).getHeight());
            if(player.getBounds().intersects(newPosition)){
                colision = true;
                player.setStaying(true);
                break;
            }
        }
        if(!colision){
            for(int i = 0;i<platforms.size();i++){
                platforms.get(i).setLocation(platforms.get(i).getX(),platforms.get(i).getY()-5);
                player.setStaying(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startJump();
        verticalMove();
        sprint();
        horizonatlMove();
        player.changeIcon(isLeftPressed,isRightPressed);
        deletePlatform();
        platfomrGenerating();
        deadRestart();
        repaint();
    }
    public void deadRestart(){
        if(player.died(platforms)){
            for(Component panel : frame.getMainPanel().getComponents()){
                if(panel instanceof Restart restart){
                    restart.record();
                }
            }
            frame.getCardLayout().show(frame.getMainPanel(),"restart");
            timer.stop();

        }
    }
    public void startJump(){
        if (isJumpPressed && !player.isJumping() && player.isStaying()) {
            player.moveUp();
        }
    }
    public void platfomrGenerating(){
        if(player.isStaying()){
            generator.platformGenerator(this,platforms);
        }
    }
    public void horizonatlMove(){
        if (isLeftPressed) {
            player.moveLeft(platforms,isLeftPressed);
        } else if (isRightPressed) {
            player.moveRight(platforms,isRightPressed);
        }
    }
    public void sprint(){
        if (isSpeedPressed) {
            player.controlSpeed(true);
        } else {
            player.controlSpeed(false);
        }
    }
    public void verticalMove(){
        if(player.isJumping() || !player.isStaying()){
            player.gameJump(platforms);
        }else {
            gameGravity();
        }
    }

    public void setScore() {
        for(Component panel : frame.getMainPanel().getComponents()){
            if(panel instanceof Menu menu){
                this.score = menu.getScore();
            }
        }
    }

    public Timer getTimer() {
        return timer;
    }

}
