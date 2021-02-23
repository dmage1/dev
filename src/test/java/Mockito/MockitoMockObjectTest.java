package Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.Database;
import util.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * 0. Creating mock objects
 *
 * Mockito provides several methods to create mock objects:
 *
 * - Using the static mock() method.
 *
 * - Using the @Mock annotation.
 *
 * - Using the @ExtendWith(MockitoExtension.class) extension for JUnit 5
 *
 * If you use the @Mock annotation, you must trigger the initialization of the annotated fields.
 * The MockitoExtension does this by calling the static method MockitoAnnotations.initMocks(this).
 */
@ExtendWith(MockitoExtension.class)
public class MockitoMockObjectTest {
    @Mock
    Database databaseMock;

    @Test
    public void testQuery()  {
        // arrange
        when(databaseMock.isAvailable()).thenReturn(true);
        Service t  = new Service(databaseMock);

        // act
        boolean check = t.query("* from t");

        // assert
        assertNotNull(databaseMock);
        assertTrue(check);
    }
}
