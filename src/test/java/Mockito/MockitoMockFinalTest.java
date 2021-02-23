package Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import util.FinalClass;

/**
 * 10. Mocking final classes and static methods
 *
 * Since the mockito-inline library replaced the mockito-core library it is possible
 * to mock final classes and static methods.
 */
@ExtendWith(MockitoExtension.class)
public class MockitoMockFinalTest {

    @Test
    public void testMockFinal(@Mock FinalClass finalMocked)  {
        assertNotNull(finalMocked);
    }

    // Mockito's inline mock maker supports static mocks based on the Instrumentation API.
    // You can simply enable this mock mode, by placing the 'mockito-inline' artifact where you are currently
    // using 'mockito-core'.Note that Mockito's inline mock maker is not supported on Android.

    @Test
    public void testMockFinalViaMockStatic()  {
        MockedStatic<FinalClass> mockStatic = Mockito.mockStatic(FinalClass.class);
        assertNotNull(mockStatic);
    }
}