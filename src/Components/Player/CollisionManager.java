package Components.Player;

import Components.Platform;
import Components.Player.Player;

import java.awt.*;
import java.util.ArrayList;

/**
 * Manages collisions between the player and platforms.
 */
public class CollisionManager {
    public CollisionManager() {
    }

    /**
     * Determines if the player is standing on any of the platforms. The method uses a slightly offset rectangle above the platform
     * to check whether the player has just landed or is standing on top.
     *
     * @param player player object whose position is being checked
     * @param platforms list of platforms the player can potentially collide with
     * @return true if the player collides with a platform, false otherwise
     */
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
