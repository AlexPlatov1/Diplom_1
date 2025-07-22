import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.List;

public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    private Burger burger;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(mockBun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        Ingredient ingredientA = mock(Ingredient.class);
        Ingredient ingredientB = mock(Ingredient.class);
        Ingredient ingredientC = mock(Ingredient.class);

        burger.addIngredient(ingredientA);
        burger.addIngredient(ingredientB);
        burger.addIngredient(ingredientC);
        burger.moveIngredient(1, 0);

        assertEquals(ingredientB, burger.ingredients.get(0));
        assertEquals(ingredientA, burger.ingredients.get(1));
        assertEquals(ingredientC, burger.ingredients.get(2));
    }

    @Test
    public void testGetPrice() {
        when(mockBun.getPrice()).thenReturn(50f);
        when(mockIngredient1.getPrice()).thenReturn(10f);
        when(mockIngredient2.getPrice()).thenReturn(20f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = (mockBun.getPrice() * 2) + mockIngredient1.getPrice() + mockIngredient2.getPrice();

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        when(mockBun.getName()).thenReturn("Sesame");
        when(mockBun.getPrice()).thenReturn(50f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getName()).thenReturn("Lettuce");
        when(mockIngredient1.getPrice()).thenReturn(10f);
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient2.getName()).thenReturn("Ketchup");
        when(mockIngredient2.getPrice()).thenReturn(5f);
        Burger testBurger = new Burger();
        testBurger.setBuns(mockBun);
        testBurger.addIngredient(mockIngredient1);
        testBurger.addIngredient(mockIngredient2);
        String receipt = testBurger.getReceipt();
        assertTrue(receipt.contains("Sesame"));
        assertTrue(receipt.contains("Lettuce"));
        assertTrue(receipt.contains("Ketchup"));
        float expectedPrice = (mockBun.getPrice() * 2) + 10f + 5f;
        assertTrue(receipt.contains(String.format("Price: %.2f", expectedPrice)));
    }

    //1 вариант
    @Test
    public void testGetReceipt2() {
        when(mockBun.getName()).thenReturn("Sesame");
        when(mockBun.getPrice()).thenReturn(50f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getName()).thenReturn("Lettuce");
        when(mockIngredient1.getPrice()).thenReturn(10f);
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient2.getName()).thenReturn("Ketchup");
        when(mockIngredient2.getPrice()).thenReturn(5f);

        Burger testBurger = new Burger();
        testBurger.setBuns(mockBun);
        testBurger.addIngredient(mockIngredient1);
        testBurger.addIngredient(mockIngredient2);

        String receipt = testBurger.getReceipt();
        assertTrue(receipt.contains("Sesame"), "Чек должен содержать название булочки");
        assertTrue(receipt.contains("Lettuce"), "Чек должен содержать название ингредиента Lettuce");
        assertTrue(receipt.contains("Ketchup"), "Чек должен содержать название ингредиента Ketchup");

        float expectedPrice = (mockBun.getPrice() * 2) + 10f + 5f;
        String pricePattern = String.format("Price: %.2f", expectedPrice);

        assertTrue(receipt.contains(pricePattern), "Чек должен содержать правильную итоговую цену: " + pricePattern);
    }

    //2 вариант
    @Test
    public void testGetReceipt3() {
        when(mockBun.getName()).thenReturn("Sesame");
        when(mockBun.getPrice()).thenReturn(50f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getName()).thenReturn("Lettuce");
        when(mockIngredient1.getPrice()).thenReturn(10f);
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient2.getName()).thenReturn("KETCHUP");
        when(mockIngredient2.getPrice()).thenReturn(5f);

        Burger testBurger = new Burger();
        testBurger.setBuns(mockBun);
        testBurger.addIngredient(mockIngredient1);
        testBurger.addIngredient(mockIngredient2);

        String receipt = testBurger.getReceipt();
        List<String> lines = Arrays.asList(receipt.split("\n"));
        String[] expectedLines = {
                "(==== Sesame ====)",
                "= filling Lettuce =",
                "= sauce KETCHUP =",
                "(==== Sesame ====)",
                "",
                "Price: 115,000000"
        };

        for (int i = 0; i < Math.min(lines.size(), expectedLines.length); i++) {
            assertEquals(expectedLines[i].trim(), lines.get(i).trim(),
                    "Строка [" + (i+1) + "] не совпадает.");
        }

        assertEquals(expectedLines.length, lines.size(),
                "Количество строк в чеке не совпадает.");
    }
}