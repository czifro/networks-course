import java.util.Scanner;

/**
 * @author Will Czifro
 */
public class ConsoleUtil {

    private static Scanner kbd = new Scanner(System.in);

    public static void write(String msg) {
        System.out.print(msg);
    }

    public static void writeMessage(String msg, String tag) {
        write(String.format("{%s}~> %s\n", tag, msg));
    }

    public static String readKbd() {
        write("|> ");
        return kbd.nextLine();
    }

}
