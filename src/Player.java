import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Player extends JLabel  {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private int sprint;
    private boolean isJumping = false;
    private boolean staying;
    private int jumpPower;
    private int jumpSpeed;
    private double gravity;
    private int originalSpeed;
    //private boolean death;


    public Player(int x,int y,int width, int height,int speed,int sprint,int jumpPower) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.originalSpeed = speed;
        this.sprint = sprint;
        this.jumpPower = jumpPower;
        this.jumpSpeed = 0;
        this.gravity = 1;
        //this.death = false;
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.setFocusable(true);
    }
    public boolean diead(ArrayList<Platform> platforms){
        Platform lowestPlatfomr = Collections.max(platforms);
        if((y-1000)>lowestPlatfomr.getY()){
            return true;
        }
        return false;
    }
    public void controlSpeed(boolean pressedShift){
        if(pressedShift){
            speed = sprint;
        }else {
            speed = originalSpeed;
        }

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
        if(headCollision(platforms)){
            isJumping = false;
            staying = false;
            return;
        }
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
    public boolean headCollision(ArrayList<Platform> platforms){
        Rectangle newPosition = new Rectangle(x,y + jumpSpeed,width,height);

        for(int i = 0;i<platforms.size();i++){
            if(newPosition.intersects(platforms.get(i).getBounds())){
                if(y>platforms.get(i).getY()){
                    jumpSpeed = 0;
                    return true;
                }
            }
        }
        return false;

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
