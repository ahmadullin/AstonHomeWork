import org.example.TriangleArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    private static final double DELTA = 0.0001; // точность для double

    @Test
    @DisplayName("Площадь прямоугольного треугольника 3-4-5")
    void testRightTriangle() {
        assertEquals(6.0, TriangleArea.calculateArea(3, 4, 5), DELTA);
    }

    @Test
    @DisplayName("Площадь равностороннего треугольника со стороной 2")
    void testEquilateralTriangle() {
        double expected = Math.sqrt(3);
        assertEquals(expected, TriangleArea.calculateArea(2, 2, 2), DELTA);
    }

    @Test
    @DisplayName("Площадь равнобедренного треугольника")
    void testIsoscelesTriangle() {
        assertEquals(12.0, TriangleArea.calculateArea(5, 5, 6), DELTA);
    }

    @Test
    @DisplayName("Несуществующий треугольник (сумма двух сторон меньше третьей)")
    void testInvalidTriangleSidesTooSmall() {
        assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.calculateArea(1, 1, 3));
    }

    @Test
    @DisplayName("Несуществующий треугольник (вырожденный)")
    void testDegenerateTriangle() {
        assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.calculateArea(2, 3, 5));
    }

    @Test
    @DisplayName("Отрицательная сторона должна выбрасывать исключение")
    void testNegativeSide() {
        assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.calculateArea(-1, 2, 3));
    }

    @Test
    @DisplayName("Нулевая сторона должна выбрасывать исключение")
    void testZeroSide() {
        assertThrows(IllegalArgumentException.class,
                () -> TriangleArea.calculateArea(0, 2, 3));
    }
}