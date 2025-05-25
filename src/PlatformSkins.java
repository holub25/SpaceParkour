import java.util.ArrayList;
import java.util.Random;

public class PlatformSkins extends ComponentSkin{
    private ArrayList<String> platformSkins;
    private String name;
    private int lastNum;
    public PlatformSkins(int price, Type type,String name,int lastNum) {
        super(price, type);
        this.platformSkins = new ArrayList<>();
        this.name = name;
        this.lastNum = lastNum;
        addPlatformSkin();
    }
    public void addPlatformSkin(){
        for(int i = 1;i<lastNum;i++){
            platformSkins.add("skins//platforms//"+name+"//"+name+"-"+i+".png");
        }
    }

    public ArrayList<String> getPlatformSkins() {
        return platformSkins;
    }
}
