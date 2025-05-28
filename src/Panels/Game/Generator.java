package Panels.Game;

import Components.Platform;
import Components.Score;
import Frame.Frame;
import Skins.ComponentSkin;
import Skins.PlatformSkins;
import Skins.Type;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Platform generator for the game. Creates new platforms based on the player's score and increases difficulty.
 */
public class Generator {

    private Random rd;
    private Score score;
    private Frame frame;
    private PlatformSkins platformSkins;

    public Generator(Score score,Frame frame) {
        this.score = score;
        this.frame = frame;
        this.rd = new Random();
    }

    /**
     * Generates a new platform if there are 5 or fewer platforms currently.
     * The new platform's position is based on the last platform and player's score (difficulty).
     *
     * @param game JPanel where new platforms are added.
     * @param platforms list of current platforms in the game.
     */
    public void platformGenerator(JPanel game,ArrayList<Platform> platforms){
        if(platforms.size()<=5){
            Platform lastPlatform = Collections.min(platforms);
            int newPositionY = difficultyY(lastPlatform.getY());
            int newPositionX = difficultyX(lastPlatform.getX());
            skin();
            Platform newPlatfrom = new Platform(newPositionX,newPositionY,180,20);
            newPlatfrom.setPlatformSkins(platformSkins);
            newPlatfrom.addTexture();
            platforms.add(newPlatfrom);
            game.add(newPlatfrom);
            game.setComponentZOrder(newPlatfrom,0);
        }
    }

    /**
     * Sets the platform skin to the currently equipped skin from the shop.
     */
    public void skin(){
        for(ComponentSkin skin : frame.getShop().getPlatformSkinsPan().getSkins()){
            if(skin instanceof PlatformSkins platformSkins){
                if(platformSkins.getType() == Type.EQUIP){
                    this.platformSkins = platformSkins;
                    System.out.println("DONE");
                }
            }
        }
    }

    /**
     * Calculates the new Y position of the platform based on the player's score for increasing difficulty.
     *
     * @param lastY position of the last platform
     * @return new Y position for the new platform
     */
    public int difficultyY(int lastY){
        if(score.getPlayerScore()<=20){
            return lastY-150;
        } else if (score.getPlayerScore()<=30) {
            return lastY-200;
        }else if (score.getPlayerScore()<=40) {
            return lastY-250;
        }else if (score.getPlayerScore()<=50) {
            return lastY-275;
        }else {
            return lastY-300;
        }
    }

    /**
     * Calculates the new X position of the platform based on the player's score for random difficulty distribution.
     *
     * @param lastX X position of the last platform
     * @return new X position for the new platform
     */
    public int difficultyX(int lastX){
        if(score.getPlayerScore()<=20){
            return lastX+rd.nextInt(800)-400;
        } else if (score.getPlayerScore()<=30) {
            return lastX+rd.nextInt(840)-420;
        }else if (score.getPlayerScore()<=40) {
            return lastX+rd.nextInt(880)-440;
        }else if (score.getPlayerScore()<=50) {
            return lastX+rd.nextInt(920)-460;
        }else {
            return lastX+rd.nextInt(960)-480;
        }
    }
}
