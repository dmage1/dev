import domain.Person;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Calculator;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage.
 */
@DisplayName("JUnit5 Tests")
@Disabled("Disabled JUnit5 Tests until needed")
public class JUnit5Test {

    private final Calculator calculator = new Calculator();
    private final Person person = new Person("Jane", "Doe");

    @BeforeAll
    static void initAll() {
        System.out.println("Method executed before all @Test");
    }

    @BeforeEach
    public void init() {
        System.out.println("Method executed before each @Test");
        assertNotNull(calculator);
        assertNotNull(person);
    }

    @Test
    @DisplayName("Simple addition should work")
    void addition() {
        assertEquals(2, 1+1, "Add one to one should be two");
    }

    @Test
    @DisplayName("Simple subtraction should work")
    void subtraction() {
        assertEquals(1, 2-1, "Subtract two with one should be one");
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void multiplication() {
        assertEquals(6, 3*2, "Multiple one with one should be one");
    }

    @RepeatedTest(value = 5, name = "Repeated Test {currentRepetition}/{totalRepetitions}")
    @DisplayName("Simple Repeated Test should work")
    public void repeatedTest(RepetitionInfo repetitionInfo) {
        System.out.println("Repetition #" + repetitionInfo.getCurrentRepetition());
        assertEquals(5, repetitionInfo.getTotalRepetitions(), "Total repetitions should be five");
    }

    @ParameterizedTest(name = "Year {0} is a leap year.")
    @ValueSource(ints = { 2016, 2020, 2048 })
    @DisplayName("Simple Parameterized Test should work")
    void parameterizedTest(int year) {
        assertEquals(0, year%4);
    }

    @Test
    @DisplayName("Simple GroupedAssertions Test should work")
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
                calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }
    @Test
    void timeoutNotExceededMinutes() {
        // The following assertion succeeds.
        assertTimeout(ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }
    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> "a result");
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededMillis() {
        // The following assertion succeeds.
        assertTimeout(ofMillis(10), () -> {
            // Perform task that takes less than 10 millis
            // Simulate task that takes more than 10 ms; Thread.sleep(100);
        });
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Method executed after each @Test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Method executed after all @Test");
    }
}
