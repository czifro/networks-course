
import jsocket.util.FilterFunctionType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Function;

/**
 * @author Will Czifro
 */
public class SimpleSocketTest {

    /**
     * If application is running as server,
     * this will accept incoming connections
     */
    private ServerSocket server = null;

    /**
     * The TCP connection needs to be open on a specific port
     * This should be a high enough port to not conflict with system processes
     */
    private int port = 50000;

    /**
     * This will be used to specify client or server app
     */
    private AppType appType = null;

    /**
     * There will be a single argument used to specify if app
     * will run as client or server
     * @param args
     */
    public static void main(String [] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Sorry, you need to pass an argument.");
            return;
        }
        AppType appType = AppType.toAppType(args[0]);
        if (appType == null) {
            System.out.println("Sorry, invalid arguments. Use 0 to specify client and 1 to specify server");
            return;
        }

        SimpleSocketTest main = new SimpleSocketTest(appType);
        main.run();
    }

    public SimpleSocketTest(AppType appType) throws IOException {
        this.appType = appType;

        // if the appType is a server, we need to setup app appropriately
        if (appType == AppType.SERVER)
            initialize();
    }

    private void initialize() throws IOException {
        server = new ServerSocket(port);
    }

    public void run() throws IOException {
        Scanner kbd = new Scanner(System.in);
        SimpleSocket simpleSock;
        Helper helper = new Helper(kbd);
        Socket conn = new Socket();
        if (appType == AppType.SERVER) {
            // FIXME: accept java.net.Socket connection and instantiate mySock

            // ENDFIXME
            System.out.println("Received connection...");
        }
        else {
            System.out.print("Please enter an ip address: ");
            String ip = kbd.nextLine();
            // FIXME: instantiate the java.net.Socket connection and simpleSock

            // ENDFIXME
            System.out.println("Established connection");
        }

        // FIXME: set the buffer size for simpleSock

        System.out.println("Enter \"quit\" to end session");
        String msg;
        while(true) {
            msg = "";
            if (appType == AppType.SERVER) {
                // FIXME: start sending and receiving data and update console output
            }
            else {
                // FIXME: reversal of if block
            }
        }

        // FIXME: close socket and if server is not null, close that
    }

    private class Helper {

        private Scanner input = null;

        /**
         *
         * @param input
         */
        public Helper(Scanner input) {
            this.input = input;
        }

        /**
         * Byte array will be converted to a UTF-8 string.
         * UTF-8 is used since this best supports cross platform and cross language use cases
         * @param data
         * @return
         */
        public String convertBytesToUTF8(byte[] data) {
            try {
                return cleanString(new String(data, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        private String cleanString(String str) {
            // library provides functions to filter certain characters from string
            // this is useful for cross platform and cross language use cases
            Function<String, String> func = FilterFunctionType.NULL_CHARS.getFunc();
            return func.apply(str);
        }

        /**
         * Gets input from the keyboard, displays a prompt
         * @return
         */
        public String getKbdInput() {
            System.out.print(":> ");
            return input.nextLine();
        }

        /**
         * Writes message to console
         * @param str
         */
        public void displayMsg(String str) {
            System.out.println("~> " + str);
        }
    }

    private enum AppType {
        CLIENT,
        SERVER;

        public static AppType toAppType(String val) {
            if (val.equals("0"))
                return AppType.CLIENT;
            else if (val.equals("1"))
                return AppType.SERVER;
            return null;
        }
    }
}
