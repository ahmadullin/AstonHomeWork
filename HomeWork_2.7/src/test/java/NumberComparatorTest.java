import org.example.NumberComparator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    @Test
    @DisplayName("Сравнение когда первое число больше")
    void testFirstGreater() {
        assertEquals("5 больше 3", NumberComparator.compare(5, 3));
        assertEquals("-1 больше -5", NumberComparator.compare(-1, -5));
        assertEquals("0 больше -10", NumberComparator.compare(0, -10));
    }

    @Test
    @DisplayName("Сравнение когда первое число меньше")
    void testFirstLess() {
        assertEquals("3 меньше 5", NumberComparator.compare(3, 5));
        assertEquals("-5 меньше -1", NumberComparator.compare(-5, -1));
        assertEquals("-10 меньше 0", NumberComparator.compare(-10, 0));
    }

    @Test
    @DisplayName("Сравнение равных чисел")
    void testEqualNumbers() {
        assertEquals("5 равно 5", NumberComparator.compare(5, 5));
        assertEquals("-3 равно -3", NumberComparator.compare(-3, -3));
        assertEquals("0 равно 0", NumberComparator.compare(0, 0));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, 10 больше 5",
            "5, 10, 5 меньше 10",
            "7, 7, 7 равно 7",
            "-1, -1, -1 равно -1",
            "100, 0, 100 больше 0"
    })
    @DisplayName("Параметризованный тест сравнения")
    void testCompareParameterized(int a, int b, String expected) {
        assertEquals(expected, NumberComparator.compare(a, b));
    }
}