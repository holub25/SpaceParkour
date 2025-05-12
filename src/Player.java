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
    private boolean isJumping = false;
    private boolean staying;
    private int jumpPower;
    private int jumpSpeed;
    private double gravity;


    public Player(int x,int y,int width, int height,int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.jumpPower = -25;
        this.jumpSpeed = 0;
        this.gravity = 1;
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.setFocusable(true);
    }

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
        if (!isJumping && staying) {
            jumpSpeed =  jumpPower;
            isJumping = true;
            staying = false;
        }
    }
    public void gameJump(ArrayList<Platform> platforms){
        jumpSpeed += (int) gravity;
        int move = jumpSpeed;
        boolean collision = false;
        for(int i = 0;i<platforms.size();i++){
            Rectangle newPosition = new Rectangle(platforms.get(i).getX(),platforms.get(i).getY()-move,platforms.get(i).getWidth(),platforms.get(i).getHeight());
            if(this.getBounds().intersects(newPosition)){
                collision = true;
                jumpSpeed = 0;
                isJumping = false;
                staying = true;
            }
        }
        if(!collision){
            for(int i = 0;i<platforms.size();i++){
                platforms.get(i).setLocation(platforms.get(i).getX(),platforms.get(i).getY()-move);
            }
            staying = false;
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
    public boolean isJumping() {
        return isJumping;
    }

    public boolean isStaying() {
        return staying;
    }

    public void setStaying(boolean staying) {
        this.staying = staying;
    }
}
