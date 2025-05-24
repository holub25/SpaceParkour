import java.util.ArrayList;
import java.util.Random;

public class PlatformSkins extends ComponentSkin{
    private ArrayList<String> platformSkins;
    public PlatformSkins(int price, Type type) {
        super(price, type);
        this.platformSkins = new ArrayList<>();
    }
    public void addPlatformSkin(String skin,int lastNum){
        for(int i = 1;i<lastNum;i++){
            platformSkins.add("skins//"+skin+"-"+i+".png");
        }
    }

    public ArrayList<String> getPlatformSkins() {
        return platformSkins;
    }
}
