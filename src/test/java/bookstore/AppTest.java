package bookstore;

import bookstore.pojos.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private final InputStream originalSystemIn = System.in;

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    void testAppFlow_AddAndEditBook() {
        // 1. Build the Clean Script
        StringBuilder script = new StringBuilder();

        // --- ADD BOOK ---
        script.append("1\n");             // Main Menu: Add Items
        script.append("1\n");             // Add Menu: Add Book
        script.append("Dune\n");          // Title
        script.append("Frank Herbert\n"); // Author
        script.append("10\n");            // Copies
        script.append("25.00\n");         // Price
        script.append("99\n");            // Exit Add Menu

        // --- EDIT BOOK ---
        script.append("2\n");             // Main Menu: Edit Items
        script.append("0\n");             // Select Index 0
        script.append("Dune Messiah\n");  // Change Title
        script.append("\n");              // Price: Keep
        script.append("\n");              // Copies: Keep
        script.append("\n");              // Author: Keep

        // --- QUIT ---
        script.append("99\n");            // Quit

        // 2. Inject
        System.setIn(new ByteArrayInputStream(script.toString().getBytes()));

        // 3. Run
        App app = new App() {
            @Override
            public void populate() { /* clean start */ }
        };
        app.run();

        // 4. Verify
        Book expected = new Book("Frank Herbert", "Dune Messiah", 25.00, 10);
        SaleableItem result = app.findItem(expected);

        assertNotNull(result);
        assertEquals("Dune Messiah", ((Book)result).getTitle());
    }

    @Test
    void testAppFlow_AddNintendoGame() throws Exception {
        // 1. Build the Clean Script
        StringBuilder script = new StringBuilder();
        script.append("1\n");               // Main Menu: Add Items
        script.append("5\n");               // Add Menu: Nintendo Game
        script.append("Mario Kart 8\n");    // Enter Game Title
        script.append("49.99\n");           // Enter Price
        script.append("20\n");              // Enter Copies
        script.append("Switch\n");          // Enter Platform
        script.append("Racing\n");          // Enter Genre
        
        script.append("99\n");              // Exit Add Menu
        script.append("99\n");              // Exit Main Menu

        // 2. Inject
        System.setIn(new ByteArrayInputStream(script.toString().getBytes()));

        // 3. Run
        App app = new App() {
            @Override
            public void populate() { /* clean start */ }
        };
        app.run();

        // 4. Verify - Create expected with SAME values
        NintendoGame expected = new NintendoGame("Mario Kart 8", 49.99, 20, "Switch", "Racing");
        SaleableItem result = app.findItem(expected);

        assertNotNull(result, "NintendoGame 'Mario Kart 8' should be found in inventory");
        assertInstanceOf(NintendoGame.class, result);
        assertEquals("Mario Kart 8", ((NintendoGame)result).getTitle());
        assertEquals(49.99, ((NintendoGame)result).getPrice(), 0.01);
        assertEquals(20, ((NintendoGame)result).getCopies());
        assertEquals("Racing", ((NintendoGame)result).getGenre());
        assertEquals("Switch", ((NintendoGame)result).getPlatform());
    }
}