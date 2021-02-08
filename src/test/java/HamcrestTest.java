import domain.Person;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HamcrestTest {

    @Test
    void assertThatTest() {
        boolean a = true;
        boolean b = true;

        assertThat(a, equalTo(b));
        assertThat(a, is(equalTo(b)));
        assertThat(a, is(b));
    }

    @Test
    void demoJUnitVsHamcrest() {
        int expected = 5;
        int actual = 5;
        int actualBad = 3;

        // JUnit for equals check
        assertEquals(expected, actual);
        // Hamcrest for equals check
        assertThat(actual, is(equalTo(expected)));

        // JUnit for not equals check
        assertNotEquals(expected, actualBad);
        // Hamcrest for not equals check
        assertThat(actualBad, is(not(equalTo(expected))));
    }

    @Test
    void instanceOfTest() {
        assertThat(1, instanceOf(Integer.class));
        // shortcut for instanceOf
        assertThat(Long.valueOf(1), isA(Long.class));
    }

    @Test
    public void objectMatcherTests() {
        // check that toString method of an Object returns a specified String:
        Person person=new Person("George", "Washington");
        String str=person.toString();
        assertThat(person,hasToString(str));

        // check that one class is a sub-class of another
        assertThat(Person.class, typeCompatibleWith(Object.class));
    }

    @Test
    public void beanMatcherTests() {
        // Check if the bean has the property, firstName
        Person person=new Person("George", "Washington");
        assertThat(person, hasProperty("firstName"));

        // Check if Person has the lastName property, initialized to Washington
        assertThat(person, hasProperty("lastName", equalTo("Washington")));

        // Check if two Person objects are constructed with the same values
        Person person1 = new Person("George", "Washington");
        Person person2 = new Person("George", "Washington");
        assertThat(person1, samePropertyValuesAs(person2));
    }
    
    @Test
    public void listTests() {
        // Check to find out if a Collection is empty
        List<String> emptyList = Collections.emptyList();
        assertThat(emptyList, empty());

        // Check the size of a Collection
        List<Integer> list = Arrays.asList(5, 2, 4);
        assertThat(list, hasSize(3));

        // Ensure the order is correct
        assertThat(list, contains(5, 2, 4));

        // Check if a Collection contains given members, regardless of order
        assertThat(list, containsInAnyOrder(2, 4, 5));

        // Assert that the Collection members are in given order
        assertThat(list, contains(5, 2, 4));

        // Check every item greater than one
        assertThat(list, everyItem(greaterThan(1)));

        // Check if an array has a single given element
        String[] hamcrestMatchers = { "beans", "text", "number" };
        assertThat(hamcrestMatchers, hasItemInArray("text"));
        // Use an alternative matcher for the same test
        assertThat("text", isOneOf(hamcrestMatchers));
        // We can do the same with a different matcher like so
        assertThat("beans", isIn(hamcrestMatchers));

        // Check if the array contains given elements regardless of order
        arrayContainingInAnyOrder("beans", "number", "text");

        // check if it contains a given key
        Map<String, String> map = new HashMap<>();
        map.put("George", "Washington");
        map.put("Benjamin", "Franklin");
        assertThat(map, hasKey("George"));
        // and a given value
        assertThat(map, hasValue("Washington"));
        // and finally a given entry (key, value)
        assertThat(map, hasEntry("George", "Washington"));
    }

    @Test
    public void combiningMatchersTest () {
        List<Integer> list = Arrays.asList(42, 52, 62);
        assertThat(list, both(hasSize(3)).and(contains(42, 52, 62)));
    }

    @Test
    public void numberTests () {
        assertThat(1, greaterThan(0));
        assertThat(5, greaterThanOrEqualTo(5));
        assertThat(-1, lessThan(0));
        assertThat(-1, lessThanOrEqualTo(5));
        assertThat(1.2, closeTo(1, 0.5));
    }

    @Test
    public void textTests () {
        // Check if a String is empty
        String emptyStr = "";
        assertThat(emptyStr, isEmptyString());

        // Check if a String is empty or null
        String nullStr = null;
        assertThat(nullStr, isEmptyOrNullString());

        // Check for equality of two Strings while ignoring white space
        String str1 = "text";
        String str2 = " text ";
        assertThat(str1, equalToIgnoringWhiteSpace(str2));

        // Check for the presence of one or more sub-strings in a given String in a given order
        String str = "calligraphy";
        assertThat(str, stringContainsInOrder(Arrays.asList("call", "graph")));

        // Check for equality of two Strings regardless of case
        String a = "foo";
        String b = "FOO";
        assertThat(a, equalToIgnoringCase(b));
    }

    @Test
    public void coreAPITests() {
        // readability with the is construct on a matcher:
        String str1 = "text";
        String str2 = " text ";
        assertThat(str1, is(equalToIgnoringWhiteSpace(str2)));

        // The 'is' construct on a simple data type:
        String str3 = "text";
        String str4 = " texts ";
        assertThat(str3, not(equalToIgnoringWhiteSpace(str4)));

        // The 'not' construct on a simple data type:
        assertThat(str1, not(str2));

        // Check if a String contains a given sub-string:
        String str5 = "calligraphy";
        String str6 = "call";
        assertThat(str5, containsString(str6));

        // Check if a String starts with given sub-string
        assertThat(str5, startsWith(str6));

        // Check if a String ends with given sub-string
        assertThat(str5, endsWith("phy"));

        // Check if two Objects are of the same instance
        assertThat(str1, sameInstance(str1));

        // Check if an Object is an instance of a given class
        assertThat(str1, instanceOf(String.class));

        // Check if all members of a Collection meet a condition
        List<Integer> list = Arrays.asList(1, 2, 3);
        int baseCase = 0;
        assertThat(list, everyItem(greaterThan(baseCase)));
    }

}