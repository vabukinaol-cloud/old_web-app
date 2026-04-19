
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTests {

    private static Stream<Arguments> getParameters() {
        return Stream.of(
                Arguments.of("black bun", 100.0F),
                Arguments.of("white bun", 200.0F),
                Arguments.of("red bun", 300.0F)
        );
    }

    @ParameterizedTest(name = "{0} за {1} денег")
    @MethodSource("getParameters")
    @DisplayName("Проверка получения имени и цены булочки")
    void bunTest(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName(), "Имя булочки не совпадает");
        assertEquals(price, bun.getPrice(), 0.001f, "Цена булочки не совпадает");
    }
}