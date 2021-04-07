package Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The Java Set interface, java.util.Set, represents a collection of objects where each object in the Java Set is
 * unique. In other words, the same object cannot occur more than once in a Java Set.
 *
 * The Java Set interface is a standard Java interface, and it is a subtype of the Java Collection interface,
 * meaning Set inherits from Collection.
 *
 * http://tutorials.jenkov.com/java-collections/set.html
 */
@DisplayName("Collections Set Test")
public class SetTest {

    enum Game { CRICKET, HOCKEY, TENNIS }

    private final Set<String> setA = new HashSet<>();
    private final Set<Game> setB = EnumSet.allOf(Game.class);
    private final Set<String> setC = new LinkedHashSet<>();
    private final Set<String> setD = new TreeSet<>();

    private final String element1 = "element 1";
    private final String element2 = "element 2";
    private final String element3 = "element 3";
    private final String element4 = "element 4";

    @BeforeEach
    public void init() {
        // Add Elements
        setA.add(element1);
        setA.add(element2);
    }

    @AfterEach
    public void tearDown() {
        // Remove All Elements
        setA.clear();
    }

    @Test
    @DisplayName("Set.of()")
    void setOf() {
        // Since Java 9 the Set interface contains a set of static factory methods that can create unmodifiable
        // (immutable) Set instances.
        Set set1 = Set.of();
        assertEquals(0, set1.size(), "List size should be 0");

        Set<String> set2 = Set.of("val1", "val2", "val3");
        assertEquals(3, set2.size(), "List size should be 3");
    }

    @Test
    @DisplayName("Set Size")
    void size() {
        setA.add(element3);
        assertEquals(3, setA.size(), "Set size should be two");
    }

    @Test
    @DisplayName("Checking if Set Contains Element")
    void contains() {
        assertTrue(setA.contains(element2), "set should element");
    }

    @Test
    @DisplayName("Remove Elements From a Java Set")
    void remove() {
        assertEquals(2, setA.size(), "Set size should be 2");
        setA.remove(element2);
        assertEquals(1, setA.size(), "Set size should be 1");
    }

    @Test
    @DisplayName("Remove All Elements From a Java Set")
    void clear() {
        setA.clear();
        assertEquals(0, setA.size(), "Set size should be 0");
    }

    @Test
    @DisplayName("Retain All Elements From One List in Another")
    void retainAll() {
        Set<String> otherSet = new HashSet<>();
        otherSet.add(element1);
        otherSet.add(element3);
        otherSet.add(element4);

        setA.retainAll(otherSet);

        // Only element1 in both
        assertEquals(1, setA.size(), "Set size should be 1");
    }

    @Test
    @DisplayName("Check if Set is Empty")
    void isEmpty() {
        setA.clear();
        assertTrue(setA.isEmpty(), "Set size should not be empty");
    }

    @Test
    @DisplayName("Convert Set to List")
    void asList() {
        Set<String> set = new HashSet<>();
        set.add("123");
        set.add("456");

        setA.addAll(set);
        assertEquals(4, setA.size(), "Set size should be 4");
    }

    @Test
    @DisplayName("Iterate Set using Iterator")
    void iterator() {
        Iterator<String> iterator = setA.iterator();
        while(iterator.hasNext()) {
            String element = iterator.next();
            assertNotNull(element, "Should not be null");
        }
    }

    @Test
    @DisplayName("Iterate Set using For-Each Loop")
    void forEach() {
        for(Object element : setA) {
            assertNotNull(element, "Should not be null");
        }
    }

    @Test
    @DisplayName("Iterate Set using Java Stream API")
    void forEachStream() {
        Stream<String> stream = setA.stream();
        stream.forEach( element -> {  assertNotNull(element, "Should not be null"); });
    }

}
