import jsocket.util.ByteUtil;

import java.util.Arrays;

/**
 * @author Will Czifro
 */
public class RSAKeyPacket extends Packet {

    public RSAKeyPacket() {
        this(new byte[0]);
    }

    public RSAKeyPacket(byte[] raw) {
        super(raw, 'r');
    }

    public int modulusLen;

    public byte[] modulus;

    public int exponentLen;

    public byte[] exponent;


    public byte[] composeBytes() {
        return null; // todo: fix me
    }

    public void parseBytes() {
        // todo: fix me
    }
}
