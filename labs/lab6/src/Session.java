import jsocket.socket.Socket;
import jsocket.util.ByteUtil;

import java.io.IOException;

/**
 * @author Will Czifro
 */
public abstract class Session {

    int port = 50000;
    Socket sock;
    CryptoKey rsa;
    CryptoKey aes;
    boolean useEncryption;
    boolean quit;

    protected Packet receivePacket() {
        byte[] raw = sock.receiveAll(4);
        int packetSize = ByteUtil.byteArrayToInt(raw);
        raw = sock.receiveAll(packetSize);
        Packet packet = Packet.createPacket(raw);
        packet.parseBytes();
        return packet;
    }

    protected void sendPacket(Packet packet) {
        byte[] data = packet.composeBytes();
        byte[] dataLen = ByteUtil.intToByteArray(data.length, 4);
        sock.send(dataLen);
        sock.send(data);
    }

    protected String processPacket(Packet packet) {
        final char type = packet.type;
        byte[] data = new byte[0];

        switch (type) {
            case 'e':
                data = Crypto
                        .decrypt(((EncryptedMessagePacket)packet).message, aes, ((EncryptedMessagePacket) packet).iv);
            case 'm':
                if (data.length == 0) {
                    data = ((MessagePacket)packet).message;
                }
                return StringHelper.bytesToString(data, useEncryption);
        }
        return null;
    }

    protected Packet createPacket() {
        String msg;
        while (true) {
            msg = ConsoleUtil.readKbd();
            if ((msg.equalsIgnoreCase("encrypt") && useEncryption) ||
                    (msg.equalsIgnoreCase("decrypt") && !useEncryption)) {
                ConsoleUtil.write("Requested action is already set...");
                continue;
            }
            break;
        }
        if (msg.equalsIgnoreCase("encrypt") || msg.equalsIgnoreCase("decrypt")) {
            ActionPacket ap = new ActionPacket();
            ap.useEncryption = !useEncryption;
            useEncryption = !useEncryption;
            return ap;
        }
        quit = msg.equalsIgnoreCase("quit");

        if (useEncryption) {
            byte[] encoded = StringHelper.stringToBytes(msg, useEncryption);
            byte[] cipher = Crypto.encrypt(encoded, aes);
            EncryptedMessagePacket emp = new EncryptedMessagePacket();
            emp.message = cipher;
            emp.messageLen = cipher.length;
            emp.iv = aes.getIV();
            emp.ivLen = emp.iv.length;
            return emp;
        }

        MessagePacket mp = new MessagePacket();
        mp.message = msg.getBytes();
        mp.messageLen = mp.message.length;
        return mp;
    }

    public void run() throws IOException {
        ConsoleUtil.write("Creating RSA key...\n");
        rsa = RSAKey.createNewKey();
        ConsoleUtil.write("Finished creating RSA key...\n");
        // overrides should still call this
    }

    protected final void commands() {
        String txt = "The following commands are allowed: \n";
        txt += "\tquit => ends session\n";
        txt += "\tencrypt => encrypts connection (first time use performs handshake)\n";
        txt += "\tdecrypt => decrypts connection\n";
        ConsoleUtil.write(txt);
    }

    protected abstract void doKeyExchange();
}
