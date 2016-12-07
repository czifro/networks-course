import jsocket.util.ByteUtil;

import java.util.Arrays;

/**
 * @author Will Czifro
 */
public class AESKeyPacket extends Packet {

    public AESKeyPacket() {
        this(new byte[0]);
    }

    public AESKeyPacket(byte[] raw) {
        super(raw, 'R');
    }

    public int secretLen;

    public byte[] secret;

    public byte[] composeBytes() {
        return null; // todo: fix me
    }

    public void parseBytes() {
        // todo: fix me
    }
}
