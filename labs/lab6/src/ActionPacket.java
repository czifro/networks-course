/**
 * @author Will Czifro
 */
public class ActionPacket extends Packet {

    public ActionPacket() {
        this(new byte[0]);
    }

    public ActionPacket(byte[] raw) {
        super(raw, 'a');
    }

    public boolean useEncryption;

    public byte[] composeBytes() {
        return null; // todo: fix me
    }

    public void parseBytes() {
        // todo: fix me
    }
}
