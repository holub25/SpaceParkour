package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Panels.Game.Game;
import Panels.Menu;
import Panels.Shop.Shop;
import Skins1.Type;
import Frame.Frame;
import Saves.Loader;

/**
 * Test class for Loader.
 * Tests the typeSelect method that converts a String to the Type enum.
 */
class LoaderTest {

    private Loader loader;
    private Game game;
    private Frame frame;
    private Menu menu;
    private Shop shop;

    /**
     * Setup before each test.
     * Creates new instances of frame, menu, shop, game and loader.
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.frame = new Frame();
        this.menu = new Menu(frame);
        this.shop = new Shop(frame);
        this.game = new Game(frame);
        this.loader = new Loader(menu,game.getPlayer(),shop,frame);
    }

    /**
     * Tests correct conversion of string to Type enum.
     */
    @Test
    void typeSelect() {
        Assertions.assertEquals(Type.EQUIP,loader.typeSelect("EQUIP"));
        Assertions.assertEquals(Type.OWN,loader.typeSelect("OWN"));
        Assertions.assertEquals(Type.BUY,loader.typeSelect("BUY"));
        Assertions.assertEquals(Type.EXPENSIVE,loader.typeSelect("EXPENSIVE"));
    }

    /**
     * Tests invalid or unknown inputs.
     * Expects null return.
     */
    @Test
    void typeSelectWrong() {
        assertNull(loader.typeSelect("UNKNOWN"));
        assertNull(loader.typeSelect("equip"));
        assertNull(loader.typeSelect(""));
        assertNull(loader.typeSelect(null));
    }
}