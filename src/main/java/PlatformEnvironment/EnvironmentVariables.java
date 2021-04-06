package PlatformEnvironment;

import java.util.*;

/**
 * Environment Variables.
 *
 * Many operating systems use environment variables to pass configuration information to applications.
 *
 * On the Java platform, an application uses System.getenv to retrieve environment variable values.
 *
 * Without an argument, getenv returns a read-only instance of java.util.Map, where the map keys are
 * the environment variable names, and the map values are the environment variable values.
 */
public class EnvironmentVariables {

    private static Map<String, String> env = System.getenv();

    public static void main (String[] args) {
        displayEnvironmentVariable("JAVA_HOME8");
        displayEnvironmentVariable("JAVA_HOMEx");
        displayEnvironmentVariables();
        displayEnvironmentVariablesSorted();
    }

    private static void displayEnvironmentVariable(String env) {
        String value = System.getenv(env);
        if (value != null) {
            System.out.format("%s=%s%n",
                    env, value);
        } else {
            System.out.format("%s is"
                    + " not assigned.%n", env);
        }
    }

    private static void displayEnvironmentVariables() {
        // Java 8
        env.forEach((k, v) -> System.out.format("%s=%s%n", k, v));

        // Classic way to loop a map
        //for (Map.Entry<String, String> entry : env.entrySet()) {
        //    System.out.format("dd %s=%s%n", entry.getKey() , entry.getValue());
        //}
    }

    private static void displayEnvironmentVariablesSorted() {
        SortedSet<String> sortedSet = new TreeSet<>(env.keySet()); // Maybe a better way to do ?

        sortedSet.forEach(k -> System.out.format("%s=%s%n", k, env.get(k)));
    }
}
