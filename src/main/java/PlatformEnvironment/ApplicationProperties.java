package PlatformEnvironment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties
 *
 * An application can use Properties objects to maintain its configuration.
 *
 * Properties are configuration values managed as key/value pairs.
 * In each pair, the key and value are both String values.
 *
 * The key identifies, and is used to retrieve, the value,
 * much as a variable name is used to retrieve the variable's value.
 */
public class ApplicationProperties {
    public static void main(String[] args) throws IOException {
        // saveProperties();
        Properties appProperties = loadProperties();
        appProperties.setProperty("Alpha", "Centauri");
        displayProperties(appProperties);
        appProperties.remove("Alpha");
    }

    private static void saveProperties() {
        Properties appProperties = new Properties();
        try {
            FileOutputStream out = new FileOutputStream("appProperties");
            appProperties.setProperty("propKey","propValue");
            appProperties.store(out, "---No Comment---");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties loadProperties() {
        // load properties
        Properties appProperties = new Properties();
        try {
            FileInputStream in = new FileInputStream("appProperties");
            appProperties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appProperties;
    }

    private static void displayProperties(Properties appProperties) {
        appProperties.forEach((k, v) -> System.out.format("%s=%s%n", k, v));
    }
}
