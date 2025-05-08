import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {
    private Player player;
    private Frame frame;
    private Platform platform;
    private ArrayList<Platform> platforms;
    private Timer timer;
    private Random rd;

    public Game(Frame frame) {
        rd = new Random();
        this.timer = new Timer(1,this);
        this.frame = frame;
        this.platforms = new ArrayList<>();
        player = new Player(300,400,45,45,5);
        platform = new Platform(40,40,200,80);
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setBackground(Color.BLUE);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.add(player);
        Platform firstPlatform = new Platform(10,800,500,20);
        platforms.add(firstPlatform);
        this.add(firstPlatform);
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
                player.moveLeft(platforms);
                break;
            case KeyEvent.VK_D:
                player.moveRight(platforms);
                break;
            case KeyEvent.VK_W:
                //player.moveUp();
                break;
            case KeyEvent.VK_S:
                player.moveDown(platform);
                break;
            case KeyEvent.VK_ESCAPE:
                frame.getCardLayout().show(frame.getMainPanel(),"menu");
                break;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                player.moveLeft(platforms);
                break;
            case KeyEvent.VK_D:
                player.moveRight(platforms);
                break;
            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_S:
                player.moveDown(platform);
                break;
        }
        repaint();
    }


    public void generatePlatform(){
        if(platforms.size()<=5){
            Platform lastPlatform = Collections.min(platforms);
            int newPositionX = lastPlatform.getX()+(rd.nextInt(800)-400);
            int newPositionY = lastPlatform.getY()-100;
            Platform newPlatfrom = new Platform(newPositionX,newPositionY,180,20);
            platforms.add(newPlatfrom);
            this.add(newPlatfrom);
            this.setComponentZOrder(newPlatfrom,0);
            System.out.println("2");
        }

    }
    public void addPlatforms(){
        generatePlatform();
        for(int i = 0;i<platforms.size();i++){
            this.add(platforms.get(i));
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
        if(player.isJumping()){
            player.gameJump(platforms);
        }else {
            gameGravity();
        }

        if(player.isStaying()){
            generatePlatform();
        }

        repaint();
    }

    public Timer getTimer() {
        return timer;
    }
}
