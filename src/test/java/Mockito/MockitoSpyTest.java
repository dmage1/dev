package Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 5. Wrapping Java objects with Spy
 *
 * @Spy or the spy() method can be used to wrap a real object.
 *
 * Every call, unless specified otherwise, is delegated to the object.
 * ~ used to spy on an existing instance
 */
@ExtendWith(MockitoExtension.class)
class MockitoSpyTest {

    @Test
    public void whenNotUseSpyAnnotation_thenCorrect() {
        List<String> spyList = spy(new ArrayList<>());

        // Used the real method spiedList.add() to add elements to the spiedList.
        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertEquals(2, spyList.size());

        // Stubbed the method spiedList.size() to return 100 instead of 2 using doReturn()
        doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
    }

    @Spy
    List<String> spiedList = new ArrayList<>();

    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        spiedList.add("one");
        spiedList.add("two");

        verify(spiedList).add("one");
        verify(spiedList).add("two");

        assertEquals(2, spiedList.size());

        doReturn(100).when(spiedList).size();
        assertEquals(100, spiedList.size());
    }

    // another example similar to whenNotUseSpyAnnotation_thenCorrect()
    @Test
    public void testLinkedListSpyCorrect() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // this would not work as delegate it called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)
        // when(spy.get(0)).thenReturn("foo");

        // you have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
        assertEquals("foo", spy.get(0));
    }
}
