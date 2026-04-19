
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTypeTests {

    @Test
    void sauceTypeTest() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    void fillingTypeTest() {
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
}