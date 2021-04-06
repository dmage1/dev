package PlatformEnvironment;

/**
 * Command-Line Arguments.
 *
 * A Java application can accept any number of arguments from the command line.
 *
 * This allows the user to specify configuration information when the application is launched.
 *
 * When an application is launched, the runtime system passes the command-line arguments to
 * the application's main method via an array of Strings.
 */
public class CommandLineArguments {
    public static void main(String[] args) {
        // cd C:\workspace\dev\target\classes then run
        // java PlatformEnvironment.CommandLineArguments Hello From "the Command Line"
        for (String s: args) {
            System.out.println(s);
        }
    }
}
