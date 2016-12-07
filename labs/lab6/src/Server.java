import jsocket.socket.SocketImpl;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Will Czifro
 */
public class Server extends Session {

    protected void doKeyExchange() {
        // todo: fix me
    }

    protected String processPacket(Packet packet) {
        String msg = super.processPacket(packet);
        if (msg != null)
            return msg;

        useEncryption = ((ActionPacket)packet).useEncryption;
        if (useEncryption) {
            ConsoleUtil.writeMessage("Request to encrypt connection", "CLIENT");
            ConsoleUtil.write("Switching to an encrypted connection...\n");
            if (aes == null) {
                ConsoleUtil.write("Need to do key exchange, signaling CLIENT...\n");
                sendPacket(packet);
                doKeyExchange();
            }
        }
        else {
            ConsoleUtil.writeMessage("Request to decrypt connection", "CLIENT");
            ConsoleUtil.write("Switching to unencrypted connection...\n");
        }
        return null;
    }

    public void run() throws IOException {
        super.run();
        ConsoleUtil.write("Waiting for connection...\n");
        ServerSocket server = new ServerSocket(port);
        sock = new SocketImpl(server.accept());

        ConsoleUtil.write("Got connection...\n");
        super.commands();

        while (true) {
            Packet packet = createPacket();
            if (packet instanceof ActionPacket) {
                if (((ActionPacket)packet).useEncryption) {
                    if (aes == null) {
                        ConsoleUtil.write("Sending encryption request to client, beginning exchange...\n");
                        sendPacket(packet);
                        doKeyExchange();
                        continue;
                    }
                }
                sendPacket(packet);
                continue; // client has yielded
            }
            sendPacket(packet);
            if (quit) {
                ConsoleUtil.write("Ending session...");
                break;
            }
            String msg = processPacket(receivePacket());
            if (msg == null) continue; // a request to encrypt/decrypt connection happened, client has yielded
            if (msg.equalsIgnoreCase("quit")) {
                ConsoleUtil.writeMessage("Ending session", "CLIENT");
                ConsoleUtil.write("Client has disconnected...\n");
                break;
            }
            ConsoleUtil.writeMessage(msg, "CLIENT");
        }

        sock.close();
        server.close();
    }
}
