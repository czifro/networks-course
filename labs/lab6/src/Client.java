import jsocket.socket.SocketImpl;

import java.io.IOException;

/**
 * @author Will Czifro
 */
public class Client extends Session {

    protected void doKeyExchange() {
        // todo: fix me
    }

    protected String processPacket(Packet packet) {
        String msg = super.processPacket(packet);
        if (msg != null)
            return msg;

        useEncryption = ((ActionPacket)packet).useEncryption;
        if (useEncryption) {
            ConsoleUtil.writeMessage("Request to encrypt connection", "SERVER");
            ConsoleUtil.write("Switching to an encrypted connection...\n");
            if (aes == null) {
                ConsoleUtil.write("Need to do key exchange...\n");
                doKeyExchange();
                ConsoleUtil.write("Exchange finished, yielding to server...\n");
            }
        }
        else {
            ConsoleUtil.writeMessage("Request to decrypt connection", "SERVER");
            ConsoleUtil.write("Switching to unencrypted connection...\n");
        }

        return null;
    }

    public void run() throws IOException {
        super.run();
        ConsoleUtil.write("Enter ip address to connect to:\n");
        String ip = ConsoleUtil.readKbd();

        ConsoleUtil.write("Establishing connection...\n");
        sock = new SocketImpl(new java.net.Socket(ip, port));
        ConsoleUtil.write("Successfully connected...\n");
        super.commands();

        while (true) {
            String msg = processPacket(receivePacket());
            if (msg == null) continue; // a request to encrypt/decrypt connection happened, yield to server again
            if (msg.equalsIgnoreCase("quit")) {
                ConsoleUtil.writeMessage("Ending session", "SERVER");
                ConsoleUtil.write("Server has disconnected...\n");
                break;
            }
            ConsoleUtil.writeMessage(msg, "SERVER");
            Packet packet = createPacket();
            if (packet instanceof ActionPacket) {
                if (((ActionPacket) packet).useEncryption) {
                    if (aes == null) {
                        ConsoleUtil.write("Sending encryption request to server, waiting for server to initiate exchange...\n");
                        sendPacket(packet);
                        continue;
                    }

                }
            }
            sendPacket(packet);
            if (quit) {
                ConsoleUtil.write("Ending session...\n");
                break;
            }
        }
        sock.close();
    }
}
