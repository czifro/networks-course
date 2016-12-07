import java.util.*;

/**
 * @author Will Czifro
 */
public class ReceiveBuffer {

    private PriorityQueue<Packet> buffer = new PriorityQueue<>();

    public void addPacket(Packet packet) {
        if (!buffer.contains(packet))
            buffer.add(packet);
    }

    public boolean isComplete() {
        return false; // FIXME
    }

    public byte[] getBytes(int length) {
        return null; // FIXME
    }
}
