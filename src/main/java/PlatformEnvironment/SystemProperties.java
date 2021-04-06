package PlatformEnvironment;

import java.util.Properties;

/**
 * System Properties
 *
 * In Properties, we examined the way an application can use Properties objects to maintain its configuration.
 *
 * The Java platform itself uses a Properties object to maintain its own configuration.
 *
 * The System class maintains a Properties object that describes the configuration of the current working environment.
 *
 * System properties include information about the current user, the current version of the Java runtime, and the
 * character used to separate components of a file path name.
 */
public class SystemProperties {
    private static final Properties properties = System.getProperties();

    public static void main(String[] args) {

        displayApplicationProperties();

        // Java 8
        properties.forEach((k, v) -> System.out.println(k + ":" + v));

        // Classic way to loop a map
        //for (Map.Entry<Object, Object> entry : properties.entrySet()) {
        //    System.out.println(entry.getKey() + " : " + entry.getValue());
        //}

        // No good, output is truncated, long lines end with ...
        //properties.list(System.out);
    }

    private static void displayApplicationProperties() {
        // cd C:\workspace\dev\target\classes then run
        // java -DNAME="myName" -DVERSION="1.0" -DLOCATION="home" PlatformEnvironment.SystemProperties

        System.out.println("*** " + System.getProperty("NAME"));
        System.out.println("*** " + System.getProperty("VERSION"));
        System.out.println("*** " + System.getProperty("LOCATION"));
    }
}
