package Mockito;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.Database;

/**
 * 6. Verify the calls on the mock objects
 *
 * Mockito keeps track of all the method calls and their parameters to the mock object.
 *
 * You can use the verify() method on the mock object to verify that the specified conditions are met.
 *
 * For example, you can verify that a method has been called with certain parameters.
 *
 * This kind of testing is sometimes called behavior testing.
 *
 * Behavior testing does not check the result of a method call,
 * but it checks that a method is called with the right parameters.
 */
@ExtendWith(MockitoExtension.class)
public class MockitoVerifyTest {

    @Test
    public void testVerify(@Mock Database database) {
        // create and configure mock
        when(database.getUniqueId()).thenReturn(43);

        // call method testing on the mock with parameter 12
        database.setUniqueId(12);
        database.getUniqueId();
        database.getUniqueId();

        // now check if method testing was called with the parameter 12
        verify(database).setUniqueId(ArgumentMatchers.eq(12));

        // was the method called twice?
        verify(database, times(2)).getUniqueId();

        // other alternatives for verifiying the number of method calls for a method
        verify(database, never()).isAvailable();
        verify(database, never()).setUniqueId(13);
        verify(database, atLeastOnce()).setUniqueId(12);
        verify(database, atLeast(2)).getUniqueId();

        // more options are
        // times(numberOfTimes)
        // atMost(numberOfTimes)
        // This let's you check that no other methods where called on this object.
        // You call it after you have verified the expected method calls.
        verifyNoMoreInteractions(database);
    }
}
