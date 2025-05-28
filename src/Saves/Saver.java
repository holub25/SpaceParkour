package Saves;

import Components.Player.Player;
import Panels.Menu;
import Panels.Shop.Shop;
import Skins.BackgroundSkin;
import Skins.Type;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saver class is responsible for writing the current game state to a save file.
 * This includes best score, coins, owned/equipped skins, and selected background path.
 */
public class Saver {
    private FileWriter fw;
    private BufferedWriter bw;

    public Saver(Menu menu,Player player,Shop shop) {
        try {
            this.fw = new FileWriter("Saves\\dataSave");
            this.bw = new BufferedWriter(fw);

            saveScore(menu);
            saveCoins(player);
            savePlayerSkins(shop);
            savePlatfromsSkins(shop);
            saveBackgroundSkins(shop);
            saveSelectedBackWay(shop);

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the best score to the save file.
     *
     * @param menu the menu containing the best score
     * @throws IOException if writing fails
     */
    public void saveScore(Menu menu) throws IOException {
        bw.write("bestScore;"+menu.getScore().getBestScore());
        bw.newLine();
    }

    /**
     * Saves the current number of coins to the save file.
     *
     * @param player the player whose coin count is saved
     * @throws IOException if writing fails
     */
    public void saveCoins(Player player) throws IOException {
        bw.write("coins;"+player.getCoinCounter().getCoinsCount());
        bw.newLine();
    }

    /**
     * Saves the state of all player skins (name and type).
     *
     * @param shop the shop containing player skins
     * @throws IOException if writing fails
     */
    public void savePlayerSkins(Shop shop) throws IOException {
        for(int i = 0;i<shop.getPlayerSkinsPan().getSkins().size();i++){
            bw.write(shop.getPlayerSkinsPan().getSkins().get(i).getName()+";"+shop.getPlayerSkinsPan().getSkins().get(i).getType());
            bw.newLine();
        }
    }

    /**
     * Saves the state of all platform skins (name and type).
     *
     * @param shop the shop containing platform skins
     * @throws IOException if writing fails
     */
    public void savePlatfromsSkins(Shop shop) throws IOException {
        for(int i = 0;i<shop.getPlatformSkinsPan().getSkins().size();i++) {
            bw.write(shop.getPlatformSkinsPan().getSkins().get(i).getName()+";"+shop.getPlatformSkinsPan().getSkins().get(i).getType());
            bw.newLine();
        }
    }

    /**
     * Saves the state of all background skins (name and type).
     *
     * @param shop the shop containing background skins
     * @throws IOException if writing fails
     */
    public void saveBackgroundSkins(Shop shop) throws IOException  {
        for(int i = 0;i<shop.getBackgroundSkinsPan().getSkins().size();i++) {
            bw.write(shop.getBackgroundSkinsPan().getSkins().get(i).getName()+";"+shop.getBackgroundSkinsPan().getSkins().get(i).getType());
            bw.newLine();
        }
    }

    /**
     * Saves the path (way) of the currently equipped background skin.
     *
     * @param shop the shop containing background skins
     * @throws IOException if writing fails
     */
    public void saveSelectedBackWay(Shop shop) throws IOException {
        for(int i = 0;i<shop.getBackgroundSkinsPan().getSkins().size();i++){
            if(shop.getBackgroundSkinsPan().getSkins().get(i).getType() == Type.EQUIP){
                if(shop.getBackgroundSkinsPan().getSkins().get(i) instanceof BackgroundSkin bs){
                    bw.write("selectWay;"+bs.getWay());
                    bw.newLine();
                }
            }
        }

    }
}
