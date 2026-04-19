
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTests {

    @ParameterizedTest(name = "Ингредиент {1} типа {0} за {2} денег")
    @CsvSource({
            "FILLING, hot sauce, 100.0",
            "FILLING, sour cream, 200.0",
            "SAUCE, cutlet, 100.0"
    })
    @DisplayName("Проверка полей ингредиента")
    void ingredientFieldsTest(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }
}