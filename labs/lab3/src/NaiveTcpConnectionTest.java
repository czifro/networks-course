import jsocket.util.FilterFunctionType;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * @author Will Czifro
 */
public class NaiveTcpConnectionTest {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Sorry, you need to pass an argument.");
            return;
        }
        AppType appType = AppType.toAppType(args[0]);
        if (appType == null) {
            System.out.println("Sorry, invalid arguments. Use 0 to specify client and 1 to specify server");
            return;
        }

        new NaiveTcpConnectionTest(appType).run();
    }

    private AppType appType;
    private NaiveTcpConnection tcp;
    private int port = 50000;

    public NaiveTcpConnectionTest(AppType appType) {
        this.appType = appType;
    }

    public void run() {
        Helper helper = new Helper(new Scanner(System.in));
        if (appType == AppType.SERVER) {
            tcp = new NaiveTcpConnection(port);
        }
        else {
            System.out.println("Please enter an IP address: ");
            String ip = helper.getKbdInput();
            tcp = new NaiveTcpConnection(port, ip);
        }

        tcp.setBufferSize(128);

        if (appType == AppType.SERVER) {
            System.out.println("Waiting for connection...");
            tcp.waitForConnection();
            System.out.println("Got connection");
        }
        else {
            System.out.println("Trying to connect...");
            tcp.connect();
            System.out.println("Got connected");
        }

        System.out.println("Enter \"quit\" to end session");
        String msg;
        while(true) {
            msg = "";
            if (appType == AppType.SERVER) {
                msg = helper.getKbdInput();
                tcp.send(msg.getBytes());
                if (msg.toLowerCase().equals("quit"))
                    break;
                byte[] rec = tcp.receive();
                msg = helper.convertBytesToUTF8(rec);
                if (msg.toLowerCase().equals("quit")) {
                    System.out.println("Client ended session");
                    break;
                }
                helper.displayMsg(msg);
            }
            else {
                byte[] rec = tcp.receive();
                msg = helper.convertBytesToUTF8(rec);
                if (msg.toLowerCase().equals("quit")) {
                    System.out.println("Server ended session");
                    break;
                }
                helper.displayMsg(msg);
                msg = helper.getKbdInput();
                tcp.send(msg.getBytes());
                if (msg.toLowerCase().equals("quit"))
                    break;
            }
        }

        tcp.close();
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
