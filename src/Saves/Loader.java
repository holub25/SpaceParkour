package Saves;

import Components.Player.Player;
import Frame.Frame;
import Panels.Menu;
import Panels.Shop.Shop;
import Skins1.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Loader class is responsible for reading saved game data from a file
 * and initializing the game state based on that data.
 */
public class Loader {
    private FileReader fr;
    private BufferedReader br;

    public Loader(Menu menu, Player player, Shop shop, Frame frame) {
        try {
            String userHome = System.getProperty("user.home");
            File saveDir = new File(userHome+File.separator+"SpaceParkour"+File.separator+"Saves");
            if(!saveDir.exists()){
                saveDir.mkdirs();
            }
            File saveFile = new File(saveDir,"dataSave.txt");
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            fr = new FileReader(saveFile);
            br = new BufferedReader(fr);

            fileReading(menu,player,shop,frame);

            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the file line by line and calls the appropriate loading method for each line.
     *
     * @param menu the main menu panel
     * @param player the player object
     * @param shop the shop panel
     * @param frame the main game frame
     * @throws IOException if some error whil reading a file.
     */
    public void fileReading(Menu menu,Player player,Shop shop,Frame frame) throws IOException {
        String line;
        while ((line = br.readLine())!=null){
            loadBestScore(line,menu);
            loadCoins(line,player,shop);
            loadPlayerSkins(line,shop);
            loadPlatformSkins(line,shop);
            loadBackgroundSkins(line,shop);
            loadEveryBackground(line,frame);
        }
    }

    /**
     * Parses best score from save file and updates menu score label.
     *
     * @param line the current line in file
     * @param menu the main menu panel
     */
    public void loadBestScore(String line,Menu menu){
        if((line.split(";")[0]).equalsIgnoreCase("bestScore")){
            menu.getScore().setBestScore(Integer.parseInt(line.split(";")[1]));
            menu.getScore().updateBestScore();
        }
    }

    /**
     * Loads saved coin count, applies it to player and updates shop display.
     *
     * @param line the current line in file
     * @param player the player object
     * @param shop the shop panel
     */
    public void loadCoins(String line,Player player,Shop shop){
        if((line.split(";")[0]).equalsIgnoreCase("coins")){
            int coins = Integer.parseInt(line.split(";")[1]);
            player.getCoinCounter().setCoinsCount(coins);
            shop.updateCoinText(coins);
        }
    }

    /**
     * Helper method to convert string values to Type enum.
     *
     * @param value the string representing the skin type
     * @return the corresponding Type enum, or null if input is invalid
     */
    public Type typeSelect(String value){
        if(value == null){
            return null;
        }
        switch (value){
            case "EQUIP":
                return Type.EQUIP;
            case "OWN":
                return Type.OWN;
            case "BUY":
                return Type.BUY;
            case "EXPENSIVE":
                return Type.EXPENSIVE;
        }
        return null;
    }

    /**
     * Loads and sets skin state for player skins in the shop.
     *
     * @param line the current line in file
     * @param shop the shop containing the list of player skins
     */
    public void loadPlayerSkins(String line,Shop shop){
        for(int i = 0;i<shop.getPlayerSkinsPan().getSkins().size();i++){
            if(line.split(";")[0].equalsIgnoreCase(shop.getPlayerSkinsPan().getSkins().get(i).getName())){
                Type type = typeSelect(line.split(";")[1]);
                shop.getPlayerSkinsPan().getSkins().get(i).setType(type);
            }
        }
    }

    /**
     * Loads and sets skin state for platform skins in the shop.
     *
     * @param line the current line in file
     * @param shop the shop containing the list of platform skins
     */
    public void loadPlatformSkins(String line, Shop shop){
        for(int i = 0;i<shop.getPlatformSkinsPan().getSkins().size();i++){
            if(line.split(";")[0].equalsIgnoreCase(shop.getPlatformSkinsPan().getSkins().get(i).getName())){
                Type type = typeSelect(line.split(";")[1]);
                shop.getPlatformSkinsPan().getSkins().get(i).setType(type);
            }
        }
    }

    /**
     * Loads and sets skin state for background skins in the shop.
     *
     * @param line the current line in file
     * @param shop the shop containing the list of background skins
     */
    public void loadBackgroundSkins(String line,Shop shop){
        for(int i = 0;i<shop.getBackgroundSkinsPan().getSkins().size();i++){
            if(line.split(";")[0].equalsIgnoreCase(shop.getBackgroundSkinsPan().getSkins().get(i).getName())){
                Type type = typeSelect(line.split(";")[1]);
                shop.getBackgroundSkinsPan().getSkins().get(i).setType(type);
            }
        }
    }

    /**
     * Loads selected background path and applies it to the game frame.
     *
     * @param line the current line in file
     * @param frame the game frame where background will be set
     */
    public void loadEveryBackground(String line,Frame frame){
        if(line.split(";")[0].equalsIgnoreCase("selectWay")){
            frame.setBackgrounds(line.split(";")[1]);
        }
    }

}
