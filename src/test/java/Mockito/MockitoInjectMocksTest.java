package Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import util.ArticleDatabase;
import util.ArticleListener;
import util.ArticleManager;

import static org.mockito.Mockito.verify;

/**
 * 7. Using @InjectMocks for dependency injection via Mockito
 *
 * You also have the @InjectMocks annotation which tries to do constructor,
 * method or field dependency injection based on the type.
 */
@ExtendWith(MockitoExtension.class)
public class MockitoInjectMocksTest {

    @Mock
    ArticleDatabase database;

    @InjectMocks
    private ArticleManager manager;

    @Test public void shouldDoSomething() {
        // calls addListener with an instance of ArticleListener
        manager.initialize();

        // validate that addListener was called
        verify(database).addListener(Mockito.any(ArticleListener.class));
    }
}
