import jsocket.util.ByteUtil;

import java.util.Arrays;

/**
 * @author Will Czifro
 */
public class Packet implements Comparable<Packet> {

    public static int SINGLE = -2;
    public static int END = -1;
    public static int START = 0;
    public static int ACK = 1;
    public static int PACKET = 2;
    public static int CONNECT = 3;

    public Packet() {}

    public Packet(byte[] rawData, int packetSize) {
        // FIXME
    }

    public int packetId;

    public int packetType;

    public byte[] data;

    public int checksum;

    public boolean acked; // not included in getBytes nor parsing

    public long transmittedAt = 0;

    public byte[] getBytes() {
        return null; //FIXME
    }

    public static Packet createConnectPacket(int packetSize) {
        Packet packet = new Packet();
        packet.packetId = 0;
        packet.packetType = Packet.CONNECT;
        packet.data = new byte[packetSize];
        packet.checksum = 0;
        return packet;
    }

    public static Packet createAckPacket(Packet packet, int packetSize) {
        Packet ack = new Packet();
        ack.packetId = packet.packetId;
        ack.packetType = Packet.ACK;
        ack.data = new byte[packetSize];
        ack.checksum = 0;
        return ack;
    }

    public int compareTo(Packet o) {
        if (o.packetId == packetId) return 0;
        if (o.packetId < packetId) return 1;
        return -1;
    }
}
