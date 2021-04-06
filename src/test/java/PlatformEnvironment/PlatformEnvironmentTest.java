package PlatformEnvironment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Platform Environment Test")
public class PlatformEnvironmentTest {
    private final Map<String, String> env = System.getenv();

    @Test
    @DisplayName("Single Environment Variable Test")
    void retrieveEnvironmentVariableTest() {
        String envKey = "NUMBER_OF_PROCESSORS";
        String envValue = System.getenv(envKey);

        assertEquals("6", envValue);
    }

    @Test
    @DisplayName("Environment Variables Test")
    void retrieveEnvironmentVariablesTest() {
        assertNotNull(env);
        assertEquals(63, env.size());
    }

}
