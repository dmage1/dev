package Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Since Map is an interface you need to instantiate a concrete implementation of the Map interface in order to use it.
 *
 * HashMap maps a key and a value. It does not guarantee any order of the elements stored internally in the map.
 *
 * TreeMap also maps a key and a value. It guarantees the order in which keys or values are iterated - which is the
 * sort order of the keys or values.
 *
 * The HashMap implementation is typically the fastest of the two Map implementations, so whenever you don't need to
 * sort the elements in the Map you can just use a HashMap. Otherwise use a TreeMap.
 *
 * http://tutorials.jenkov.com/java-collections/map.html
 */
@DisplayName("Collections Map Test")
public class MapTest {

    private final Map<String, String> mapA = new HashMap<>();

    private final String key1 = "key1";
    private final String key2 = "key2";
    private final String key3 = "key3";
    private final String key4 = "key4";

    private final String element1 = "element 1";
    private final String element2 = "element 2";
    private final String element3 = "element 3";
    private final String element4 = "element 4";

    @BeforeEach
    public void init() {
        // Add Elements
        mapA.put(key1, element1);
        mapA.put(key2, element2);
        mapA.put(key3, element3);
    }

    @AfterEach
    public void tearDown() {
        // Remove All Elements
        mapA.clear();
    }

    @Test
    @DisplayName("Map Size")
    void size() {
        mapA.put(key4, element4);
        assertEquals(4, mapA.size(), "Map size should be two");
    }

    @Test
    @DisplayName("Checking if Map Contains Element")
    void contains() {
        assertTrue(mapA.containsKey(key2), "map should contain key");
        assertTrue(mapA.containsValue(element2), "map should contain value");
    }

    @Test
    @DisplayName("Remove Elements From a Java Map")
    void remove() {
        assertEquals(3, mapA.size(), "Map size should be 2");
        mapA.remove(key2);
        assertEquals(2, mapA.size(), "Map size should be 1");
    }

    @Test
    @DisplayName("Remove All Elements From a Java Map")
    void clear() {
        mapA.clear();
        assertEquals(0, mapA.size(), "Map size should be 0");
    }

    @Test
    @DisplayName("Get or Default Value")
    void getOrDefault() {
        String value = mapA.getOrDefault("E", "default value");
        assertEquals("default value", value, "Map size return default value");
    }

    @Test
    @DisplayName("Check if Map is Empty")
    void isEmpty() {
        mapA.clear();
        assertEquals(true, mapA.isEmpty(),  "Map size should not be empty");
    }

    @Test
    @DisplayName("compute()")
    void compute() {
        /*
         * The compute() method will call the lambda expression internally, passing the key object and whatever value
         * is stored in the Map for that key object, as parameters to the lambda expression.
         *
         * Whatever value the lambda expression returns is stored instead of the currently stored value for that key.
         */
        String expected = element1.toUpperCase();
        String result = mapA.compute(key1, (key, value) -> value == null ? null : element1.toUpperCase());

        assertEquals(expected, result, "Should compute");
        assertEquals(expected, mapA.get(key1), "Should compute");
    }

    @Test
    @DisplayName("computeIfAbsent()")
    void computeIfAbsent() {
        /*
         * The Map computeIfAbsent() method works similarly to the compute() method, but the lambda expression is only
         * called if no entry exists already for the given key.
         *
         * The value returned by the lambda expression is inserted into the Map. If null is returned,
         * no entry is inserted.
         *
         * If an exception is thrown by the lambda expression, no entry is inserted either.
         */
        String expected = "abc";
        String result = mapA.computeIfAbsent("123", (key) -> "abc");

        assertEquals(expected, result, "Should compute If Absent");
        assertEquals(expected, mapA.get("123"), "Should compute If Absent");
    }

    @Test
    @DisplayName("computeIfPresent()")
    void computeIfPresent() {
        /*
         * The Map computeIfPresent() method works oppositely of computeIfAbsent(). It only calls the lambda expression
         * passed as parameter to it, if an entry already exists in the Map for that key.
         *
         * The value returned by the lambda expression will be inserted into the Map instance. If the lambda expression
         * returns null, the entry for the given key is removed.
         *
         * If the lambda expression throws an exception, the exception is rethrown, and the current entry for the given
         * key is left unchanged.
         *
         * Note: The compute() is a bit similar to computeIfPresent() except that when the key isn't existent in the
         * map, calling the compute() will raise an exception.
         */
        String expected = element1.toUpperCase();
        String result = mapA.computeIfPresent(key1, (key, value) -> value == null ? null : value.toUpperCase());

        assertEquals(expected, result, "Should compute If Present");
        assertEquals(expected, mapA.get(key1), "Should compute If Present");
    }

    @Test
    @DisplayName("merge()")
    void merge() {
        /*
         * The Map merge() method takes a key, a value, and a lambda expression implementing the BiFunction interface
         * as parameters.
         *
         * If the Map does not have an entry for the key, or if the value for the key is null, the value passed as
         * parameter to the merge() method is inserted for the given key.
         *
         * If, however, an existing value is already mapped to the given key, the lambda expression passed as parameter
         * is called instead. The lambda expression thus gets a chance to merge the existing value with a new value.
         *
         * The value returned by the lambda expression is then inserted into the Map for the given key. If the lambda
         * expression returns null, the entry for the given key is removed.
         */
        String expected = "123-abc";
        String result = mapA.merge(key1, "123", (oldValue, newValue) -> newValue + "-abc");

        assertEquals(expected, result, "Should be merge value");
        assertEquals(expected, mapA.get(key1), "Should be merge value");
    }

    @Test
    @DisplayName("Inserting All Elements From Another Map")
    void retainAll() {
        Map<String, String> mapB = new HashMap<>();
        mapB.put(key4, element4);

        mapA.putAll(mapB);

        assertEquals(4, mapA.size(), "Set size should be 4");
    }

    @Test
    @DisplayName("Iterate Map using Iterator")
    void iterator() {
        Iterator<String> iterator = mapA.keySet().iterator();
        while(iterator.hasNext()) {
            String key   = iterator.next();
            String value = mapA.get(key);
            assertNotNull(key, "Should not be null");
            assertNotNull(value, "Should not be null");
        }
    }

    @Test
    @DisplayName("Iterate Map using For-Each Loop")
    void forEach() {
        for(Object key : mapA.keySet()) {
            String value = mapA.get(key);
            assertNotNull(key, "Should not be null");
            assertNotNull(value, "Should not be null");
        }
    }

    @Test
    @DisplayName("Iterate Map using Java Stream API")
    void forEachStream() {
        Stream<String> stream = mapA.keySet().stream();
        stream.forEach( key -> {  assertNotNull(mapA.get(key), "Should not be null"); });
    }

}
