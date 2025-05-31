package Components.Player;

import Components.Coins.Coin;
import Components.Coins.CoinCounter;
import Components.Coins.CoinGenerator;
import Components.Platform;
import Panels.Game.Game;
import Skins1.PlayerSkin;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the player's character in the game.
 * Contains logic for movement, jumping, coin collection, and collision with platforms.
 */
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
    private PlayerSkin playerSkin;
    private CoinCounter coinCounter;
    private Frame frame;

    public Player(int x,int y,int width, int height,int speed,int sprint,int jumpPower) {
        this.frame = frame;
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
        this.coinCounter = new CoinCounter(0,10,10,100,30,20);
        labelSettings();
    }

    /**
     * Handles coin collection by the player. If the player intersects with a coin, it's removed and the counter increases.
     * @param game game instance used to access the coins.
     */
    public void playerGetCoin(Game game){
        ArrayList<Coin> coins = game.getCoinGenerator().getCoins();
        for(int i = 0;i<coins.size();i++){
            if(this.getBounds().intersects(coins.get(i).getBounds())){
                game.remove(coins.get(i));
                coins.remove(coins.get(i));
                coinCounter.getOneCoin();
            }
        }
    }

    /**
     * Changes the player's appearance based on movement direction.
     *
     * @param left if player is moving left
     * @param right if player is moving right
     */
    public void changeIcon(boolean left, boolean right){
        if(left){
            this.setIcon(playerSkin.getLeftSkin(left));
        } else if (right) {
            this.setIcon(playerSkin.getRightSkin(right));
        }else {
            this.setIcon(playerSkin.getMainSkin());
        }
    }

    /**
     * Sets initial properties of the JLabel component representing the player.
     */
    public void labelSettings(){
        this.setOpaque(false);
        this.setBounds(x,y,width,height);
        this.setVisible(true);
        this.setFocusable(true);
    }

    /**
     * Checks whether the player has fallen out of the playable area
     *
     * @param platforms list of platforms in the game
     * @return true if player has died, false otherwise
     */
    public boolean died(ArrayList<Platform> platforms){
        Platform lowestPlatfomr = Collections.max(platforms);
        if((y-1000)>lowestPlatfomr.getY()){
            return true;
        }
        return false;
    }

    /**
     * Adjusts the player's speed based on whether the sprint key is pressed.
     *
     * @param pressedShift true if sprint (Shift) is held, false otherwise.
     */
    public void controlSpeed(boolean pressedShift){
        if(pressedShift){
            speed = sprint;
        }else {
            speed = originalSpeed;
        }

    }

    /**
     * Moves all platforms and coins to the right (simulates player moving left)
     *
     * @param platforms list of platforms
     * @param coinGenerator coin generator
     */
    public void moveLeft(ArrayList<Platform> platforms, CoinGenerator coinGenerator){
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
                platforms.get(i).moveLeft(speed);
            }
            for(int i = 0;i<coinGenerator.getCoins().size();i++){
                Coin coin = coinGenerator.getCoins().get(i);
                coin.moveLeft(speed);
            }
        }
    }

    /**
     * Moves all platforms and coins to the left (simulates player moving right).
     *
     * @param platforms list of platforms
     * @param coinGenerator coin generator
     */
    public void moveRight(ArrayList<Platform> platforms, CoinGenerator coinGenerator){
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
                platforms.get(i).moveRight(speed);
            }
            for(int i = 0;i<coinGenerator.getCoins().size();i++){
                Coin coin = coinGenerator.getCoins().get(i);
                coin.moveRight(speed);
            }
        }
    }

    /**
     * Initiates a jump if the player is not already jumping and is standing.
     */
    public void moveUp(){
        if (!isJumping && staying) {
            jumpSpeed =  jumpPower;
            isJumping = true;
            staying = false;
        }
    }

    /**
     * Handles vertical movement of the player including collisions while jumping.
     *
     * @param platforms list of platforms
     * @param coinGenerator coin generator
     */
    public void gameJump(ArrayList<Platform> platforms, CoinGenerator coinGenerator){
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
            for(int i = 0;i<coinGenerator.getCoins().size();i++){
                Coin coin = coinGenerator.getCoins().get(i);
                coin.setLocation(coin.getX(),coin.getY()-move);
            }
            staying = false;
        }
    }

    /**
     * Checks whether the player has hit a platform above their head while jumping.
     *
     * @param platforms list of platforms
     * @return true if head collision occurred, false otherwise
     */
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

    public void setPlayerSkin(PlayerSkin playerSkin) {
        this.playerSkin = playerSkin;
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

    public CoinCounter getCoinCounter() {
        return coinCounter;
    }
}
