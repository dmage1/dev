package Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Car;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The Java List interface, java.util.List, represents an ordered sequence of objects. The elements contained in a Java
 * List can be inserted, accessed, iterated and removed according to the order in which they appear internally in the
 * Java List. The ordering of the elements is why this data structure is called a List.
 *
 * Each element in a Java List has an index. The first element in the List has index 0.
 *
 * The Java List interface is a standard Java interface, and it is a subtype of the Java Collection interface,
 * meaning List inherits from Collection.
 *
 * http://tutorials.jenkov.com/java-collections/list.html
 */
@DisplayName("Collections List Test")
public class ListTest {

    private final List<String> listA = new ArrayList<>();
//    private final List<String> listB = new LinkedList<>();
//    private final List<String> listC = new Vector<>();
//    private final List<String> listD = new Stack<>();

    private final String element1 = "element 1";
    private final String element2 = "element 2";
    private final String element3 = "element 3";
    private final String element4 = "element 4";

    @BeforeEach
    public void init() {
        // Add Elements
        listA.add(element1);
        listA.add(element2);
    }

    @AfterEach
    public void tearDown() {
        // Remove All Elements
        listA.clear();
    }

    @Test
    @DisplayName("List Size")
    void size() {
        listA.add(element3);
        assertEquals(3, listA.size(), "List size should be two");
    }

    @Test
    @DisplayName("Find Elements in a List")
    void indexOf() {
        assertEquals(0, listA.indexOf(element1), "element1 should be index 0");
        assertEquals(1, listA.indexOf(element2), "element2 should be index 1");

        assertEquals(-1, listA.indexOf(element3), "element3 should be index -1");
    }

    @Test
    @DisplayName("Find Last Occurrence of Element in a List")
    void lastIndexOf() {
        listA.add(element1);
        assertEquals(2, listA.lastIndexOf(element1), "element1 should be index 2");
    }

    @Test
    @DisplayName("Checking if List Contains Element")
    void contains() {
        listA.add(element1);
        assertTrue(listA.contains(element2), "list should contain element");
    }

    @Test
    @DisplayName("Remove Elements From a Java List")
    void remove() {
        assertEquals(2, listA.size(), "List size should be 3");
        listA.remove(element2);
        assertEquals(1, listA.size(), "List size should be 2");
    }

    @Test
    @DisplayName("Remove All Elements From a Java List")
    void clear() {
        listA.clear();
        assertEquals(0, listA.size(), "List size should be 0");
    }

    @Test
    @DisplayName("Retain All Elements From One List in Another")
    void retainAll() {
        List<String> otherList = new ArrayList<>();
        otherList.add(element1);
        otherList.add(element3);
        otherList.add(element4);

        listA.retainAll(otherList);

        // Only element1 in both
        assertEquals(1, listA.size(), "List size should be 1");
    }

    @Test
    @DisplayName("Check if List is Empty")
    void isEmpty() {
        listA.clear();
        assertTrue(listA.isEmpty(), "List size should not be empty");
    }

    @Test
    @DisplayName("Sublist of List")
    void subList() {
        listA.add(element3);
        listA.add(element4);

        List<String> sublist = listA.subList(1, 3);

        // Only element1 in both
        assertEquals(2, sublist.size(), "List size should be 2");
    }

    @Test
    @DisplayName("Convert List to Set")
    void addAll() {
        Set<String> set = new HashSet<>();
        set.addAll(listA);

        // Only element1 in both
        assertEquals(2, set.size(), "Set size should be 2");
    }

    @Test
    @DisplayName("Convert Array to List")
    void asList() {
        String[] arrValues = new String[]{ "one", "two", "three" };
        List<String> list = Arrays.asList(arrValues);

        // Only element1 in both
        assertEquals(3, list.size(), "Set size should be 2");
    }

    @Test
    @DisplayName("Sort List Using Comparator")
    void sort() {
        List<Car> list = new ArrayList<>();

        list.add(new Car("Volvo V40" , "XYZ 201845", 5));
        list.add(new Car("Citroen C1", "ABC 164521", 4));
        list.add(new Car("Dodge Ram" , "KLM 845990", 2));

        Comparator<Car> carBrandComparator = new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                return car1.brand.compareTo(car2.brand);
            }
        };

        Collections.sort(list, carBrandComparator);
        assertEquals("Citroen C1", list.get(0).brand, "First element should be Citroen");
    }

    @Test
    @DisplayName("Sort List Using Comparator")
    void sortWithJabvaLambda() {
        List<Car> list = new ArrayList<>();

        list.add(new Car("Volvo V40" , "XYZ 201845", 5));
        list.add(new Car("Citroen C1", "ABC 164521", 4));
        list.add(new Car("Dodge Ram" , "KLM 845990", 2));

        Comparator<Car> carBrandComparatorLambda      =
                (car1, car2) -> car1.brand.compareTo(car2.brand);

        Comparator<Car> carNumberPlatComparatorLambda =
                (car1, car2) -> car1.numberPlate.compareTo(car2.numberPlate);

        Comparator<Car> carNoOfDoorsComparatorLambda  =
                (car1, car2) -> car1.noOfDoors - car2.noOfDoors;

        Collections.sort(list, carBrandComparatorLambda);
        assertEquals("Citroen C1", list.get(0).brand, "First element should be Citroen");

        Collections.sort(list, carNumberPlatComparatorLambda);
        assertEquals("ABC 164521", list.get(0).numberPlate, "First element should be ABC 164521");

        Collections.sort(list, carNoOfDoorsComparatorLambda);
        assertEquals(2, list.get(0).noOfDoors, "First element should be 2");
    }

    @Test
    @DisplayName("Iterate List using Iterator")
    void iterator() {
        Iterator<String> iterator = listA.iterator();
        while(iterator.hasNext()) {
            String element = iterator.next();
            assertNotNull(element, "Should not be null");
        }
    }

    @Test
    @DisplayName("Iterate List using For Loop")
    void forLoop() {
        for(int i=0; i < listA.size(); i++) {
            assertNotNull(listA.get(i), "Should not be null");
        }
    }

    @Test
    @DisplayName("Iterate List using For-Each Loop")
    void forEach() {
        for(Object element : listA) {
            assertNotNull(element, "Should not be null");
        }
    }

    @Test
    @DisplayName("Iterate List using Java Stream API")
    void forEachStream() {
        Stream<String> stream = listA.stream();
        stream.forEach( element -> {  assertNotNull(element, "Should not be null"); });
    }
}
