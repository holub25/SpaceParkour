package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Components.Player.Player;
import Components.Platform;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Platform platform;
    private ArrayList<Platform> platforms;

    @BeforeEach
    void setUp() {
        this.player = new Player(300,400,29,45,5,10,-25);
        platforms = new ArrayList<>();
    }

    @Test
    void diedTestFalse() {
        platform = new Platform(0,500,180,20);
        platforms.add(platform);

        Assertions.assertFalse(player.died(platforms));
    }

    @Test
    void diedTestTrue() {
        platform = new Platform(0,-1200,180,20);
        platforms.add(platform);

        Assertions.assertTrue(player.died(platforms));
    }
}