import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    Bun bun;
    private static final String BUN_NAME = "white";
    private static final float BUN_PRICE = 100;

    @BeforeEach
    void setUp() {
        bun = new Bun(BUN_NAME, BUN_PRICE);
    }

    @Test
    @DisplayName("Получение имени бургера")
    void getName() {
        assertEquals(BUN_NAME, bun.getName());
    }

    @Test
    @DisplayName("Получение цены бургера")
    void getPrice() {
        assertEquals(BUN_PRICE, bun.getPrice());
    }
}