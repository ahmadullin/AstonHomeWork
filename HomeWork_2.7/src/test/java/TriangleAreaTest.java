import org.example.TriangleArea;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testRightTriangle() {
        assertEquals(TriangleArea.calculateArea(3, 4, 5), 6.0, DELTA);
    }

    @Test
    public void testEquilateralTriangle() {
        assertEquals(TriangleArea.calculateArea(2, 2, 2), Math.sqrt(3), DELTA);
    }

    @Test
    public void testIsoscelesTriangle() {
        assertEquals(TriangleArea.calculateArea(5, 5, 6), 12.0, DELTA);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidTriangle() {
        TriangleArea.calculateArea(1, 1, 3);  // ← ИСПРАВЛЕНО
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDegenerateTriangle() {
        TriangleArea.calculateArea(2, 3, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeSide() {
        TriangleArea.calculateArea(-1, 2, 3);
    }
}