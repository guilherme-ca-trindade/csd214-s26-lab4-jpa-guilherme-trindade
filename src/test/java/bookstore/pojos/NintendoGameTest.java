package bookstore.pojos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NintendoGameTest {
    private NintendoGame game1;
    private NintendoGame game2;
    private NintendoGame game3;

    @BeforeEach
    void setUp() {
        game1 = new NintendoGame("Mario Odyssey",  59.99, 10, "Switch", "Adventure");
        game2 = new NintendoGame("Mario Odyssey",  59.99, 10, "Switch", "Adventure");
        game3 = new NintendoGame("Zelda BOTW",  59.99, 8, "Switch", "RPG");
    }

    @Test
    void testEquality() {
        // Same attributes should be equal
        assertEquals(game1, game2);
        assertEquals(game1.hashCode(), game2.hashCode());
    }

    @Test
    void testInequality() {
        // Different attributes should not be equal
        assertNotEquals(game1, game3);
        assertNotEquals(game1.hashCode(), game3.hashCode());
    }

    @Test
    void testConstructor() {
        assertEquals("Mario Odyssey", game1.getTitle());
        assertEquals(59.99, game1.getPrice());
        assertEquals(10, game1.getCopies());
        assertEquals("Switch", game1.getPlatform());
        assertEquals("Adventure", game1.getGenre());
    }

    @Test
    void testSellItemDecrementsStock() {
        int initialStock = game1.getCopies();
        game1.sellItem();
        assertEquals(initialStock - 1, game1.getCopies());
    }
}
