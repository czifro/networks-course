import jsocket.socket.SocketImpl;

import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Will Czifro
 */
public class SimpleFtpTest {

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

        new SimpleFtpTest(appType).run();
    }

    private AppType appType;
    private SimpleFtp ftp;

    public SimpleFtpTest(AppType appType) {
        this.appType = appType;
    }

    public void run() {
        if (appType == AppType.SERVER)
            serverRun();
        else
            clientRun();
    }

    private void serverRun() {
        try {
            Scanner kbd = new Scanner(System.in);
            File file;
            while (true) {
                System.out.println("Please enter the name of the file to transfer: ");
                String filename = kbd.nextLine();
                file = new File(filename);
                if (file.exists())
                    break;
            }
            System.out.println("Waiting for client...");
            ServerSocket server = new ServerSocket(50000);
            ftp = new SimpleFtp(new SocketImpl(server.accept()));
            System.out.println("Sending to client...");
            ftp.sendFile(file);
            System.out.println("Successfully transferred file...");
            ftp.close();
            server.close();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private void clientRun() {
        try {
            Scanner kbd = new Scanner(System.in);
            String path;
            while (true) {
                System.out.println("Please enter directory path to save file: ");
                path = kbd.nextLine();
                if (new File(path).canWrite())
                    break;
            }
            System.out.println("Please enter an IP address to connect to: ");
            String ip = kbd.nextLine();
            System.out.println("Trying to connect...");
            ftp = new SimpleFtp(new SocketImpl(new Socket(InetAddress.getByName(ip), 50000)));
            System.out.println("Receiving file...");
            ftp.receiveFile(path);
            System.out.println("Successfully received file...");
            ftp.close();
        } catch (Throwable t) {
            throw new RuntimeException(t);
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
