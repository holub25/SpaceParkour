import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveScore(Menu menu) throws IOException {
        bw.write("bestScore;"+menu.getScore().getBestScore());
        bw.newLine();
    }
    public void saveCoins(Player player) throws IOException {
        bw.write("coins;"+player.getCoinCounter().getCoinsCount());
        bw.newLine();
    }
    public void savePlayerSkins(Shop shop) throws IOException {
        for(int i = 0;i<shop.getPlayerSkinsPan().getSkins().size();i++){
            bw.write(shop.getPlayerSkinsPan().getSkins().get(i).getName()+";"+shop.getPlayerSkinsPan().getSkins().get(i).getType());
            bw.newLine();
        }
    }
    public void savePlatfromsSkins(Shop shop) throws IOException {
        for(int i = 0;i<shop.getPlatformSkinsPan().getSkins().size();i++) {
            bw.write(shop.getPlatformSkinsPan().getSkins().get(i).getName()+";"+shop.getPlatformSkinsPan().getSkins().get(i).getType());
            bw.newLine();
        }
    }
    public void saveBackgroundSkins(Shop shop) throws IOException  {
        for(int i = 0;i<shop.getBackgroundSkinsPan().getSkins().size();i++) {
            bw.write(shop.getBackgroundSkinsPan().getSkins().get(i).getName()+";"+shop.getBackgroundSkinsPan().getSkins().get(i).getType());
            bw.newLine();
        }
    }
}
