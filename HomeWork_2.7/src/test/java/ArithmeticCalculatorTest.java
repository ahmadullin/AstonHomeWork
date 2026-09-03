import org.example.ArithmeticCalculator;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticCalculatorTest {

    @Test
    public void testAdd() {
        assertEquals(ArithmeticCalculator.add(2, 3), 5);
    }

    @Test
    public void testSubtract() {
        assertEquals(ArithmeticCalculator.subtract(5, 3), 2);
    }

    @Test
    public void testMultiply() {
        assertEquals(ArithmeticCalculator.multiply(2, 3), 6);
    }

    @Test
    public void testDivide() {
        assertEquals(ArithmeticCalculator.divide(5, 2), 2.5, 0.0001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDivideByZero() {
        ArithmeticCalculator.divide(5, 0);
    }
}