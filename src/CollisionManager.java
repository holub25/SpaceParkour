import java.awt.*;
import java.util.ArrayList;

public class CollisionManager {
    public CollisionManager() {
    }

    public boolean isOnPlatform(Player player, ArrayList<Platform> platforms){
        for(int i = 0;i<platforms.size();i++){
            Rectangle newPosition = new Rectangle(platforms.get(i).getX(),platforms.get(i).getY()-5,platforms.get(i).getWidth(),platforms.get(i).getHeight());
            if(player.getBounds().intersects(newPosition)){
                return true;
            }
        }
        return false;
    }

    }
