import jsocket.util.ByteUtil;

import java.util.Arrays;

/**
 * @author Will Czifro
 */
public class MessagePacket extends Packet {

    public MessagePacket() {
        this(new byte[0]);
    }

    public MessagePacket(byte[] raw) {
        this(raw, 'm');
    }

    protected MessagePacket(byte[] raw, char type) {
        super(raw, type);
    }

    public int messageLen;

    public byte[] message;

    public byte[] composeBytes() {
        return null; // todo: fix me
    }

    public void parseBytes() {
        // todo: fix me
    }
}
