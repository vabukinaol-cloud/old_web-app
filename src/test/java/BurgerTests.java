
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTests {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient0;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @BeforeEach
    void makeBurger() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Установка булочек")
    void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun, "Булочка не была установлена");
    }

    @Test
    @DisplayName("Добавление ингредиента")
    void addIngredientTest() {
        burger.addIngredient(ingredient0);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredient0));
    }

    @Test
    @DisplayName("Удаление ингредиента")
    void removeIngredientTest() {
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertFalse(burger.ingredients.contains(ingredient0));
    }

    @Test
    @DisplayName("Перемещение ингредиента")
    void moveIngredientTest() {
        burger.addIngredient(ingredient0); // index 0
        burger.addIngredient(ingredient1); // index 1
        burger.moveIngredient(0, 1);

        assertEquals(ingredient0, burger.ingredients.get(1));
    }

    @Test
    @DisplayName("Расчет стоимости бургера")
    void getPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient0);

        when(bun.getPrice()).thenReturn(100f);
        when(ingredient0.getPrice()).thenReturn(50f);

        float expectedPrice = 100f * 2 + 50f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    @DisplayName("Проверка формирования чека")
    void getReceiptTest() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient0.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient0.getName()).thenReturn("chili");
        when(ingredient0.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient0);

        String receipt = burger.getReceipt();

        assertAll(
                () -> assertTrue(receipt.contains("(==== black bun ====)")),
                () -> assertTrue(receipt.contains("= sauce chili =")),
                () -> assertTrue(receipt.contains("Price: 250,000000"))
        );
    }
    @Test
    @DisplayName("Цена бургера без ингредиентов")
    void getPriceWithoutIngredientsTest() {
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);

        float price = burger.getPrice();

        assertEquals(200f, price, 0.001f);
    }
    @Test
    @DisplayName("Цена бургера с несколькими ингредиентами")
    void getPriceMultipleIngredientsTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);

        when(bun.getPrice()).thenReturn(100f);
        when(ingredient0.getPrice()).thenReturn(50f);
        when(ingredient1.getPrice()).thenReturn(70f);

        float expected = 100f * 2 + 50f + 70f;

        assertEquals(expected, burger.getPrice(), 0.001f);
    }
    @Test
    @DisplayName("Перемещение ингредиента в середину списка")
    void moveIngredientMiddleTest() {
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 2);

        assertEquals(ingredient0, burger.ingredients.get(2));
    }
    @Test
    @DisplayName("Удаление последнего ингредиента")
    void removeLastIngredientTest() {
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);

        burger.removeIngredient(1);

        assertEquals(1, burger.ingredients.size());
        assertFalse(burger.ingredients.contains(ingredient1));
    }

}