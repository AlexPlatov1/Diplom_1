import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;
import praktikum.Ingredient;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ingredientParamTest {

    private IngredientType type;
    private String name;
    private float price;

    public ingredientParamTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.FILLING, "pickled cucumber", 1f},
                {IngredientType.FILLING, "tomato", 1f},
                {IngredientType.FILLING, "lettuce leaves", 1f},
                {IngredientType.SAUCE, "ketchup", 1f},
                {IngredientType.SAUCE, "mayonnaise", 1f},
                {IngredientType.SAUCE, "mustard", 1f}
        });
    }

    @Test
    public void testIngredient() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.001);
    }
}