/**
 * @author Will Czifro
 */
public abstract class Packet {

    /**
     * Unparsed packet
     */
    public byte[] raw;

    /**
     * Indication as to type of packet
     */
    public char type;

    protected Packet(byte[] raw, char type) {
        this.raw = raw;
        this.type = type;
    }

    public abstract byte[] composeBytes();

    public abstract void parseBytes();

    public static Packet createPacket(byte[] raw) {
        final char type = (char) raw[0];

        switch (type) {
            case 'a':
                return new ActionPacket(raw);
            case 'r':
                return new RSAKeyPacket(raw);
            case 'R':
                return new AESKeyPacket(raw);
            case 'm':
                return new MessagePacket(raw);
            case 'e':
                return new EncryptedMessagePacket(raw);
            default:
                return null;
        }
    }
}
