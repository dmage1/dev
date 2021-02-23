package Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.Database;
import util.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * 9. Using Answers for complex mocks
 *
 * thenReturn returns a predefined value every time. With an Answer object you can
 * calculate a response based on the arguments given to your stubbed method.
 */
@ExtendWith(MockitoExtension.class)
public class MockitoDoAnswerWhenTest {

    @Mock
    Database databaseMock;

    @Test
    public void testUpdateNameUsingDoAnswer() {
        doAnswer(invocation -> {
            int id = invocation.getArgument(0);
            String name = invocation.getArgument(1);
            System.out.println("called for id: "+id+" and name: "+name);

            assertEquals(1, id);
            assertEquals("void mock test", name);

            return null;
        }).when(databaseMock).updateName(anyInt(),anyString());

        Service service  = new Service(databaseMock);
        service.updateName(1,"void mock test");

        verify(databaseMock, times(1)).updateName(1,"void mock test");
    }
}
