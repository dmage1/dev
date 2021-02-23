package Mockito;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 *  3. Using doReturn(…).when(…).methodCall
 *
 *  The doReturn(…).when(…).methodCall call chain works similar to when(…).thenReturn(…).
 *
 *  It is useful for mocking methods which give an exception during a call, e.g., if you use use functionality
 *  like Wrapping Java objects with Spy.
 */
class MockitoDoReturnWhenTest {

    // demonstrates of the spy function
    @Test
    public void testMockitoDoreturnWhen() {
        Properties properties = new Properties();
        Properties spyProperties = spy(properties);
        doReturn("42").when(spyProperties).get("shoeSize");

        String value = (String) spyProperties.get("shoeSize");

        assertEquals("42", value);
    }
}
