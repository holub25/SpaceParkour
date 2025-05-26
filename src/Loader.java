import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
    private FileReader fr;
    private BufferedReader br;

    public Loader(Menu menu,Player player,Shop shop) {
        try {
            fr = new FileReader("Saves\\dataSave");
            br = new BufferedReader(fr);

            fileReading(menu,player,shop);

            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void fileReading(Menu menu,Player player,Shop shop) throws IOException {
        String line;
        while ((line = br.readLine())!=null){
            loadBestScore(line,menu);
            loadCoins(line,player,shop);
            loadPlayerSkins(line,shop);
            loadPlatformSkins(line,shop);
            loadBackgroundSkins(line,shop);
        }
    }
    public void loadBestScore(String line,Menu menu){
        if((line.split(";")[0]).equalsIgnoreCase("bestScore")){
            menu.getScore().setBestScore(Integer.parseInt(line.split(";")[1]));
            menu.getScore().updateBestScore();
        }
    }

    public void loadCoins(String line,Player player,Shop shop) throws IOException {
        if((line.split(";")[0]).equalsIgnoreCase("coins")){
            int coins = Integer.parseInt(line.split(";")[1]);
            player.getCoinCounter().setCoinsCount(coins);
            shop.updateCoinText(coins);
        }
    }
    public Type typeSelect(String value){
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
    public void loadPlayerSkins(String line,Shop shop){
        for(int i = 0;i<shop.getPlayerSkinsPan().getSkins().size();i++){
            if(line.split(";")[0].equalsIgnoreCase(shop.getPlayerSkinsPan().getSkins().get(i).getName())){
                Type type = typeSelect(line.split(";")[1]);
                shop.getPlayerSkinsPan().getSkins().get(i).setType(type);
            }
        }
    }
    public void loadPlatformSkins(String line,Shop shop){
        for(int i = 0;i<shop.getPlatformSkinsPan().getSkins().size();i++){
            if(line.split(";")[0].equalsIgnoreCase(shop.getPlatformSkinsPan().getSkins().get(i).getName())){
                Type type = typeSelect(line.split(";")[1]);
                shop.getPlatformSkinsPan().getSkins().get(i).setType(type);
            }
        }
    }
    public void loadBackgroundSkins(String line,Shop shop){
        for(int i = 0;i<shop.getBackgroundSkinsPan().getSkins().size();i++){
            if(line.split(";")[0].equalsIgnoreCase(shop.getBackgroundSkinsPan().getSkins().get(i).getName())){
                Type type = typeSelect(line.split(";")[1]);
                shop.getBackgroundSkinsPan().getSkins().get(i).setType(type);
            }
        }
    }
}
