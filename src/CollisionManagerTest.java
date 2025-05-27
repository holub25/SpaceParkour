import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CollisionManagerTest {
    private CollisionManager collisionManager;
    private Player player;
    private ArrayList<Platform> platforms;

    @BeforeEach
    void setUp() {
        this.player = new Player(300,400,29,45,5,10,-25);
        this.platforms = new ArrayList<>();
        this.collisionManager = new CollisionManager();
    }

    @Test
    void isOnPlatformTestTrue() {
        Platform platform = new Platform(300,445,180,20);
        platforms.add(platform);
        boolean result = collisionManager.isOnPlatform(player,platforms);
        assertTrue(result);

    }

    @Test
    void isOnPlatformTestFalse() {
        Platform platform = new Platform(300,500,180,20);
        platforms.add(platform);
        boolean result = collisionManager.isOnPlatform(player,platforms);
        assertFalse(result);
    }
}