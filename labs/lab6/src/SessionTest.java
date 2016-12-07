import java.io.IOException;

/**
 * @author Will Czifro
 */
public class SessionTest {

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

        switch (appType) {
            case CLIENT:
                new Client().run();
                break;
            case SERVER:
                new Server().run();
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
