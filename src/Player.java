import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends JLabel  {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Timer jumpTimer;
    private boolean isJumping = false;
    private boolean staying;
    private int jumpPower;


    public Player(int x,int y,int width, int height,int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.setFocusable(true);

    }

    /*public void playerGravity(ArrayList<Platform> platforms){
        boolean inf = false;
        while (!inf){
            for(int i = 0;i<platforms.size();i++){
                platforms.get(i).setLocation(platforms.get(i).getX(),platforms.get(i).getY()+1);
            }
        }
    }*/
    public void moveLeft(ArrayList<Platform> platforms){
        boolean colision = false;
        for(int i = 0;i<platforms.size();i++){
            Rectangle newPosition = new Rectangle((platforms.get(i).getX()+speed),platforms.get(i).getY(),platforms.get(i).getWidth(),platforms.get(i).getHeight());
            if(this.getBounds().intersects(newPosition)){
                colision = true;
                break;
            }
        }
        if(!colision){
            for (int i = 0;i<platforms.size();i++){
                platforms.get(i).setLocation((platforms.get(i).getX()+speed),platforms.get(i).getY());
            }
        }
    }
    public void moveRight(ArrayList<Platform> platforms){
        boolean colision = false;
        for(int i = 0;i<platforms.size();i++){
            Rectangle newPosition = new Rectangle((platforms.get(i).getX()-speed),platforms.get(i).getY(),platforms.get(i).getWidth(),platforms.get(i).getHeight());
            if(this.getBounds().intersects(newPosition)){
                colision = true;
                break;
            }

        }
        if(!colision){
            for(int i = 0;i<platforms.size();i++){
                platforms.get(i).setLocation((platforms.get(i).getX()-speed),platforms.get(i).getY());
            }
        }


    }
    public void moveUp(){
        if(staying == true && isJumping == false){
            isJumping = true;
            jumpTimer = new Timer(1000,null);
            jumpTimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isJumping = false;
                    jumpTimer.stop();
                }
            });
            jumpTimer.setRepeats(false);
            jumpTimer.start();
        }
    }
    public void gameJump(ArrayList<Platform> platforms){
        for(int i = 0;i<platforms.size();i++){
            platforms.get(i).setLocation(platforms.get(i).getX(),platforms.get(i).getY()+5);
        }
    }
    public void moveDown(Platform platform){

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }



    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isStaying() {
        return staying;
    }

    public void setStaying(boolean staying) {
        this.staying = staying;
    }
}
