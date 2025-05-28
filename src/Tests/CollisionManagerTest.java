package Tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Components.Player.CollisionManager;
import Components.Player.Player;
import Components.Platform;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CollisionManager.
 * ests the isOnPlatform method which checks if the player is standing on a platform.
 */
class CollisionManagerTest {
    private CollisionManager collisionManager;
    private Player player;
    private ArrayList<Platform> platforms;

    /**
     * Setup before each test.
     * Creates player, empty platform list, and CollisionManager instance.
     */
    @BeforeEach
    void setUp() {
        this.player = new Player(300,400,29,45,5,10,-25);
        this.platforms = new ArrayList<>();
        this.collisionManager = new CollisionManager();
    }

    /**
     * Test verifying that isOnPlatform returns true
     * when the player is standing directly on a platform
     */
    @Test
    void isOnPlatformTestTrue() {
        Platform platform = new Platform(300,445,180,20);
        platforms.add(platform);
        boolean result = collisionManager.isOnPlatform(player,platforms);
        assertTrue(result);

    }

    /**
     * Test verifying that isOnPlatform returns false
     * when the player is not standing on any platform.
     */
    @Test
    void isOnPlatformTestFalse() {
        Platform platform = new Platform(300,500,180,20);
        platforms.add(platform);
        boolean result = collisionManager.isOnPlatform(player,platforms);
        assertFalse(result);
    }
}