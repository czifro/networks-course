import jsocket.util.ByteUtil;

import java.util.Arrays;

/**
 * @author Will Czifro
 */
public class EncryptedMessagePacket extends MessagePacket {

    public EncryptedMessagePacket() {
        this(new byte[0]);
    }

    public EncryptedMessagePacket(byte[] raw) {
        super(raw, 'e');
    }

    public int ivLen;

    public byte[] iv;

    public byte[] composeBytes() {
        return null; // todo: fix me
    }

    public void parseBytes() {
        // todo: fix me
    }
}



















