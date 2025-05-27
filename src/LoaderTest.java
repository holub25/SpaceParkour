import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {

    private Loader loader;
    private Game game;
    private Frame frame;
    private Menu menu;
    private Shop shop;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.frame = new Frame();
        this.menu = new Menu(frame);
        this.shop = new Shop(frame);
        this.game = new Game(frame);
        this.loader = new Loader(menu,game.getPlayer(),shop,frame);
    }

    @Test
    void typeSelect() {
        assertEquals(Type.EQUIP,loader.typeSelect("EQUIP"));
        assertEquals(Type.OWN,loader.typeSelect("OWN"));
        assertEquals(Type.BUY,loader.typeSelect("BUY"));
        assertEquals(Type.EXPENSIVE,loader.typeSelect("EXPENSIVE"));
    }

    @Test
    void typeSelectWrong() {
        assertNull(loader.typeSelect("UNKNOWN"));
        assertNull(loader.typeSelect("equip"));
        assertNull(loader.typeSelect(""));
        assertNull(loader.typeSelect(null));
    }
}