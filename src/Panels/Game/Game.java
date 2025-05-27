package Panels.Game;

import Panels.Restart;
import Panels.Menu;
import Components.Player.Player;
import Components.Platform;
import Components.Score;
import Components.Coins.CoinGenerator;
import Components.Coins.Coin;
import Components.Player.CollisionManager;
import Components.Background;
import Frame.Frame;


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
    private GameLoop gameLoop;
    private Thread thread;
    private GameLogic gameLogic;
    private Generator generator;
    private boolean isJumpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isSpeedPressed;
    private Score score;
    private CoinGenerator coinGenerator;
    private CollisionManager collisionManager;
    private Background background;

    public Game(Frame frame) {
        this.gameLogic = new GameLogic(this);
        this.frame = frame;
        this.platforms = new ArrayList<>();
        this.collisionManager = new CollisionManager();
        this.setScore();
        this.player = new Player(300,400,29,45,5,10,-25);
        this.generator = new Generator(score,frame);
        this.coinGenerator = new CoinGenerator(this,20);
        this.background = frame.getGameBackground();
        Platform firstPlatform = new Platform(225,800,180,20);
        this.add(score.getLabelNowS());
        this.add(player.getCoinCounter().getCoinsLabel());
        platforms.add(firstPlatform);
        this.add(firstPlatform);
        panelSettings();
    }
    public void startGame(){
        this.gameLoop = new GameLoop(gameLogic);
        this.thread = new Thread(gameLoop);
        thread.start();
    }
    public void panelSettings(){
        this.setBounds(0,0,frame.getWidth(),frame.getHeight());
        this.setVisible(true);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.add(player);
        this.add(frame.getGameBackground());
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
                gameLoop.stopRun();
                player.getCoinCounter().updateText();
                frame.getShop().updateCoinText(player.getCoinCounter().getCoinsCount());

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
                addCoins();
                this.remove(platforms.get(i));
                platforms.remove(platforms.get(i));
                score.scoreCounter();
            }
        }
    }

    public void gameGravity(){
        boolean colision = collisionManager.isOnPlatform(player,platforms);
        if(!colision){
            for(int i = 0;i<platforms.size();i++){
                platforms.get(i).setLocation(platforms.get(i).getX(),platforms.get(i).getY()-5);
                player.setStaying(false);
            }
            for(int i = 0;i<coinGenerator.getCoins().size();i++){
                Coin coin = coinGenerator.getCoins().get(i);
                coin.setLocation(coin.getX(),coin.getY()-5);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLogic.updateLogic();
    }
    public void palyerSettings(){
        player.changeIcon(isLeftPressed,isRightPressed);
        player.playerGetCoin(this);
        player.getCoinCounter().updateText();
    }

    public void deadRestart(){
        if(player.died(platforms)){
            for(Component panel : frame.getMainPanel().getComponents()){
                if(panel instanceof Restart restart){
                    restart.saveCoins(player.getCoinCounter().getCoinsCount());
                    restart.record();
                }
            }
            frame.getCardLayout().show(frame.getMainPanel(),"restart");
            gameLoop.stopRun();

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
            player.moveLeft(platforms,coinGenerator);
        } else if (isRightPressed) {
            player.moveRight(platforms,coinGenerator);
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
            player.gameJump(platforms,coinGenerator);
        }else {
            gameGravity();
        }
    }
    public void addCoins(){
        coinGenerator.generator(31,31);
        for(int i = 0;i<coinGenerator.getCoins().size();i++){
            Coin coin = coinGenerator.getCoins().get(i);
            this.add(coin);
            this.setComponentZOrder(coin,0);
        }
    }

    public void setScore() {
        for(Component panel : frame.getMainPanel().getComponents()){
            if(panel instanceof Menu menu){
                this.score = menu.getScore();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
    public CoinGenerator getCoinGenerator() {
        return coinGenerator;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

}
