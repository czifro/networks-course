import java.util.Arrays;

/**
 * @author Will Czifro
 */
public class SlidingWindow {

    private Packet[] packets;
    private int windowSize;
    private int pos = 0;

    public SlidingWindow(Packet[] packets, int windowSize) {
        this.packets = packets;
        this.windowSize = windowSize;
    }

    public Packet[] getWindow() {
        return null; // FIXME
    }

    /**
     * Finds the packet and sets the acked flag
     * returns true if {@code packet} is at beginning of window
     * This can help signal that the next window needs to be
     * retrieved.
     * @param packet
     * @return
     */
    public boolean ackPacket(Packet packet) {
        return false; // FIXME
    }
}
