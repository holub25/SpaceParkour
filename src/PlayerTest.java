import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assertFalse(player.died(platforms));
    }

    @Test
    void diedTestTrue() {
        platform = new Platform(0,-1200,180,20);
        platforms.add(platform);

        assertTrue(player.died(platforms));
    }
}