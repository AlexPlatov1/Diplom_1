import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BunParamTest {

    private String name;
    private float price;

    public BunParamTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{index}: Bun(name={0}, price={1})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300f},
                {"special bun", 150f}
        });
    }

    @Test
    public void testBunConstructorAndGetters() {
        Bun bun = new Bun(name, price);
        assertEquals("Name should match", name, bun.getName());
        assertEquals("Price should match", price, bun.getPrice(), 0.0001);
    }
}