import jsocket.util.FilterFunctionType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.function.Function;

/**
 * @author Will Czifro
 */
public class SimpleDatagramTest {

    private DatagramSocket server = null;

    private int port = 50000;

    private AppType appType = null;

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

        SimpleDatagramTest main = new SimpleDatagramTest(appType);
        main.run();
    }

    public SimpleDatagramTest(AppType appType) throws IOException {
        this.appType = appType;

        if (appType == AppType.SERVER)
            initialize();
    }

    private void initialize() throws IOException {
        server = new DatagramSocket(port);
    }

    public void run() throws IOException {
        Scanner kbd = new Scanner(System.in);
        SimpleDatagram simpleDatagram;
        Helper helper = new Helper(kbd);
        if (appType == AppType.SERVER) {
            // FIXME: instantiate simpleDatagram using server
        }
        else {
            System.out.println("Please enter an ip address: ");
            String ip = kbd.nextLine();
            DatagramSocket conn;
            // FIXME: instantiate simpleDatagram using conn

            // ENDFIXME
            System.out.println("Ready to send and receive...");
        }

        // FIXME: set the buffer size for simpleDatagram

        System.out.println("Enter \"quit\" to end session");
        String msg;
        byte[] data;
        while(true) {
            msg = "";
            if (appType == AppType.SERVER) {
                // FIXME: start receiving and sending data and update console output
            }
            else {
                // FIXME: reversal of if block
            }
        }

        // FIXME: close simpleDatagram

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
