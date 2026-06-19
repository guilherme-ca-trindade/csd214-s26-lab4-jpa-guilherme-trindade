package bookstore.pojos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NintendoHardwareTest {
    private NintendoHardware hardware1;
    private NintendoHardware hardware2;
    private NintendoHardware hardware3;

    @BeforeEach
    void setUp() {
        hardware1 = new NintendoHardware("Pro Controller", 69.99, 15, "Switch", "Controller", "Black");
        hardware2 = new NintendoHardware("Pro Controller", 69.99, 15, "Switch", "Controller", "Black");
        hardware3 = new NintendoHardware("Joy-Con Set", 79.99, 12, "Switch", "Joy-Con", "Blue/Red");
    }

    @Test
    void testEquality() {
        assertEquals(hardware1, hardware2);
        assertEquals(hardware1.hashCode(), hardware2.hashCode());
    }

    @Test
    void testInequality() {
        assertNotEquals(hardware1, hardware3);
    }


    @Test
    void testConstructor() {
        assertEquals("Pro Controller", hardware1.getName());
        assertEquals(69.99, hardware1.getPrice());
        assertEquals(15, hardware1.getCopies());
        assertEquals("Switch", hardware1.getPlatform());
        assertEquals("Controller", hardware1.getHardwareType());
        assertEquals("Black", hardware1.getColor());
    }

    @Test
    void testSellItemDecrementsStock() {
        int initialStock = hardware1.getCopies();
        hardware1.sellItem();
        assertEquals(initialStock - 1, hardware1.getCopies());
    }
}
