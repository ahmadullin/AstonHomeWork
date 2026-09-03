import org.example.FactorialCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    @Test
    @DisplayName("Факториал 0 должен быть равен 1")
    void testFactorialZero() {
        assertEquals(1, FactorialCalculator.factorial(0));
    }

    @Test
    @DisplayName("Факториал 1 должен быть равен 1")
    void testFactorialOne() {
        assertEquals(1, FactorialCalculator.factorial(1));
    }

    @Test
    @DisplayName("Факториал 5 должен быть равен 120")
    void testFactorialFive() {
        assertEquals(120, FactorialCalculator.factorial(5));
    }

    @Test
    @DisplayName("Факториал 10 должен быть равен 3628800")
    void testFactorialTen() {
        assertEquals(3628800, FactorialCalculator.factorial(10));
    }

    @Test
    @DisplayName("Факториал отрицательного числа должен выбрасывать исключение")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> FactorialCalculator.factorial(-5));
    }
}