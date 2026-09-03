import org.example.ArithmeticCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticCalculatorTest {

    private ArithmeticCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new ArithmeticCalculator();
    }

    // ---------- Тесты для сложения ----------
    @Test
    @DisplayName("Сложение положительных чисел")
    void testAddPositiveNumbers() {
        assertEquals(5, ArithmeticCalculator.add(2, 3));
        assertEquals(10, ArithmeticCalculator.add(4, 6));
    }

    @Test
    @DisplayName("Сложение отрицательных чисел")
    void testAddNegativeNumbers() {
        assertEquals(-5, ArithmeticCalculator.add(-2, -3));
        assertEquals(-10, ArithmeticCalculator.add(-4, -6));
    }

    @Test
    @DisplayName("Сложение с нулём")
    void testAddWithZero() {
        assertEquals(5, ArithmeticCalculator.add(5, 0));
        assertEquals(-3, ArithmeticCalculator.add(0, -3));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "-2, -3, -5",
            "5, 0, 5",
            "0, 0, 0",
            "100, -50, 50"
    })
    @DisplayName("Параметризованный тест сложения")
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, ArithmeticCalculator.add(a, b));
    }

    // ---------- Тесты для вычитания ----------
    @Test
    @DisplayName("Вычитание положительных чисел")
    void testSubtractPositiveNumbers() {
        assertEquals(2, ArithmeticCalculator.subtract(5, 3));
        assertEquals(10, ArithmeticCalculator.subtract(15, 5));
    }

    @Test
    @DisplayName("Вычитание с отрицательными числами")
    void testSubtractNegativeNumbers() {
        assertEquals(1, ArithmeticCalculator.subtract(-2, -3));
        assertEquals(-10, ArithmeticCalculator.subtract(-15, -5));
    }

    // ---------- Тесты для умножения ----------
    @Test
    @DisplayName("Умножение положительных чисел")
    void testMultiplyPositiveNumbers() {
        assertEquals(6, ArithmeticCalculator.multiply(2, 3));
        assertEquals(20, ArithmeticCalculator.multiply(4, 5));
    }

    @Test
    @DisplayName("Умножение на ноль")
    void testMultiplyByZero() {
        assertEquals(0, ArithmeticCalculator.multiply(5, 0));
        assertEquals(0, ArithmeticCalculator.multiply(0, 7));
    }

    @Test
    @DisplayName("Умножение отрицательных чисел")
    void testMultiplyNegativeNumbers() {
        assertEquals(6, ArithmeticCalculator.multiply(-2, -3));
        assertEquals(-6, ArithmeticCalculator.multiply(-2, 3));
    }

    // ---------- Тесты для деления ----------
    @Test
    @DisplayName("Деление положительных чисел")
    void testDividePositiveNumbers() {
        assertEquals(2.5, ArithmeticCalculator.divide(5, 2), 0.0001);
        assertEquals(3.0, ArithmeticCalculator.divide(9, 3), 0.0001);
    }

    @Test
    @DisplayName("Деление отрицательных чисел")
    void testDivideNegativeNumbers() {
        assertEquals(2.5, ArithmeticCalculator.divide(-5, -2), 0.0001);
        assertEquals(-2.5, ArithmeticCalculator.divide(5, -2), 0.0001);
    }

    @Test
    @DisplayName("Деление на ноль должно выбрасывать исключение")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class,
                () -> ArithmeticCalculator.divide(5, 0));
    }

    @Test
    @DisplayName("Деление нуля на число")
    void testDivideZeroByNumber() {
        assertEquals(0.0, ArithmeticCalculator.divide(0, 5), 0.0001);
    }
}