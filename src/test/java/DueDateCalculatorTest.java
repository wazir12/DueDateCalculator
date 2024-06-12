
import org.emarsys.DueDateCalculator;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

public class DueDateCalculatorTest {

    private DueDateCalculator calculator;

    @Before
    public void setUp() {
        calculator = new DueDateCalculator();
    }

    @Test
    public void testSameDayWithinWorkingHours() {
        LocalDateTime submitTime = LocalDateTime.of(2024, 6, 12, 10, 0); // Wed, 10 AM
        int turnaroundHours = 2;
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 6, 12, 12, 0); // Wed, 12 PM
        assertEquals(expectedDueDate, calculator.calculateDueDate(submitTime, turnaroundHours));
    }

    @Test
    public void testMultipleDays() {
        LocalDateTime submitTime = LocalDateTime.of(2024, 6, 13, 15, 0); // Thursday, 3 PM
        int turnaroundHours = 4;
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 6, 14, 11, 0); // Fri, 11 AM
        assertEquals(expectedDueDate, calculator.calculateDueDate(submitTime, turnaroundHours));
    }

    @Test
    public void testAcrossWeekend() {
        LocalDateTime submitDateTime = LocalDateTime.of(2024, 6, 7, 16, 0); // Friday, 4 PM
        int turnaroundHours = 2;
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 6, 10, 10, 0); // Monday, 10 AM
        assertEquals(expectedDueDate, calculator.calculateDueDate(submitDateTime, turnaroundHours));
    }

    @Test
    public void testOvernight() {
        LocalDateTime submitDateTime = LocalDateTime.of(2024, 6, 13, 16, 0); // Thursday, 4 PM
        int turnaroundHours = 4;
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 6, 14, 12, 0); // Friday, 12 PM
        assertEquals(expectedDueDate, calculator.calculateDueDate(submitDateTime, turnaroundHours));
    }

    @Test
    public void testStartOfWorkingHours() {
        LocalDateTime submitDateTime = LocalDateTime.of(2024, 6, 12, 9, 0); // Wed, 9 AM
        int turnaroundHours = 1;
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 6, 12, 10, 0); // Wed, 10 AM
        assertEquals(expectedDueDate, calculator.calculateDueDate(submitDateTime, turnaroundHours));
    }

    @Test
    public void testEndOfWorkingHours() {
        LocalDateTime submitDateTime = LocalDateTime.of(2024, 6, 12, 17, 0); // Wed, 5 PM
        int turnaroundHours = 1;
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 6, 13, 10, 0); // Thursday, 10 AM
        assertEquals(expectedDueDate, calculator.calculateDueDate(submitDateTime, turnaroundHours));
    }

    @Test
    public void testHoliday() {
        LocalDateTime submitDateTime = LocalDateTime.of(2024, 6, 17, 14, 0); // Monday, 2 PM
        int turnaroundHours = 4;
        // Assuming June 18th is a holiday (Tuesday)
        LocalDateTime expectedDueDate = LocalDateTime.of(2024, 6, 19, 10, 0); // Wed, 10 AM
        assertEquals(expectedDueDate, calculator.calculateDueDate(submitDateTime, turnaroundHours));
    }

}
