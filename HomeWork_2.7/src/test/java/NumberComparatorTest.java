import org.example.NumberComparator;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {

    @Test
    public void testFirstGreater() {
        assertEquals(NumberComparator.compare(5, 3), "5 больше 3");
    }

    @Test
    public void testFirstLess() {
        assertEquals(NumberComparator.compare(3, 5), "3 меньше 5");
    }

    @Test
    public void testEqualNumbers() {
        assertEquals(NumberComparator.compare(5, 5), "5 равно 5");
    }
}