import jsocket.datagram.DatagramImpl;
import java.net.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * This a naive implementation of TCP using a UDP
 * as the underlying network protocol. This
 * provides a simple implementation of a
 * Sliding Window to ensure all packets make it
 * in order, Data Integrity to ensure a packet's
 * data does not change in propogation, and
 * Keep Alive to ensure the connection stays
 * active.
 *
 * Sending while receiving is not supported.
 * To support this would require a more
 * advanced implementation. Since this is
 * a naive implementation, a call to {@code receive}
 * or {@code send} will lock and prevent the other
 * until it has finished.
 *
 * @author Will Czifro
 */
public class NaiveTcpConnection {

    private int port;
    private InetAddress address;
    private DatagramImpl conn;

    private int bufferSize;
    private int packetSize = 5;
    private int windowSize = 4;
    private long retransmissionInterval = 500L; // every 500 millis, retransmit packet
    private int packetLoss = 25; //%

    private Helper helper = new Helper();

    private final Object locker = new Object();

    public NaiveTcpConnection(int port) {
        try {
            this.port = port;
            DatagramSocket socket = new DatagramSocket(port);
            conn = new DatagramImpl(socket);
            // size of data chunk + 4 bytes for pId + 4 bytes for pType + 4 bytes for checksum
            conn.setBufferSize(packetSize + 4 + 4 + 4);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public NaiveTcpConnection(int port, String ip) {
        try {
            this.port = port;
            address = InetAddress.getByName(ip);
            conn = new DatagramImpl(new DatagramSocket(port+1),
                    address,
                    port);
            // size of data chunk + 4 bytes for pId + 4 bytes for pType + 4 bytes for checksum
            conn.setBufferSize(packetSize + 4 + 4 + 4);
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Waits for an incoming connection
     */
    public void waitForConnection() {
        while (true) {
            try {
                // FIXME
            } catch (Throwable t) {
            }
        }
    }

    /**
     * Attempts to connect out to address set by constructor
     */
    public void connect() {
        if (address == null) {
            throw null;
        }

        while (true) {
            try {
                // FIXME
            } catch (Throwable t) {
            }
        }
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * Using a {@link ReceiveBuffer} this will receive
     * all data as {@link Packet}s and return a byte[]
     * of size {@code bufferSize}
     * @return
     */
    public byte[] receive() {
        synchronized (locker) {
            return null; // FIXME
        }
    }

    /**
     * Using a {@link SlidingWindow}, data will be sent in
     * {@link Packet}s. Retransmission will be sent if an
     * ACK has not been received within a set
     * {@code retransmissionInterval}. A second thread is
     * used to listen for ACKs.
     * @param data
     */
    public void send(byte[] data) {
        synchronized (locker) {
            helper.beginPersistentReceive();
            // FIXME
            helper.endPersistentReceive();
        }
    }

    public void close() {
        conn.close();
    }

    private class Helper {

        Thread persistentReceive;
        Random rand = new Random();

        public ConcurrentLinkedQueue<Packet> acks =
                new ConcurrentLinkedQueue<>();
        boolean stopPersistentReceive = false;

        /**
         * Generates a checksum from a data chunk
         * @param data
         * @return
         */
        public int simpleChecksum(byte[] data) {
            int sum = 0;
            for (byte b : data)
                sum += b;
            return sum;
        }

        /**
         * Based on {@code packetSize}, raw data will be split up into
         * packets. Packets may be padded if segment is smaller than
         * {@code packetSize}.
         * @param data
         * @return
         */
        public Packet[] breakDataIntoPackets(byte[] data) {
            int randomPacketIdStart = rand.nextInt(Integer.MAX_VALUE / 4);
            int packetCount = (int) Math.ceil(((double) data.length) / ((double) packetSize));
            Packet[] packets = new Packet[packetCount];

            for (int i = 0; i < packetCount; ++i) {
                Packet packet = new Packet();
                packet.data = Arrays.copyOfRange(data, i * packetSize, (i * packetSize) + packetSize);
                packet.packetId = i + randomPacketIdStart;
                packet.packetType = packetCount == 1 ? Packet.SINGLE :
                        Packet.PACKET;
                if (i == 0) packet.packetType = Packet.START;
                if (i == packetCount - 1) packet.packetType = Packet.END;
                packet.checksum = helper.simpleChecksum(packet.data);
                packets[i] = packet;
            }
            return packets;
        }

        /**
         * Determines if packet needs to be (re)transmitted.
         * @param packet
         * @return
         */
        public boolean doRetransmission(Packet packet) {
            return packet.transmittedAt != 0 &&
                    packet.transmittedAt - System.currentTimeMillis() > retransmissionInterval &&
                    !packet.acked;
        }

        /**
         * Sends a packet with packet loss potential
         * @param packet
         * @param conn
         */
        public void send(Packet packet, DatagramImpl conn) {
            if (rand.nextInt(100) > packetLoss)
                conn.send(packet.getBytes());
        }

        /**
         * Starts a Thread to receive ACK packets
         */
        public void beginPersistentReceive() {
            Runnable receive = () -> {
                conn.setTimeout(250);
                while (!stopPersistentReceive) {
                    try {
                        // FIXME
                    } catch (Throwable t) {
                        //System.out.println(t.getClass().getSimpleName() + " was thrown");
                    }
                }
                conn.setTimeout(0);
            };
            stopPersistentReceive = false;
            persistentReceive = new Thread(receive);
            persistentReceive.setName("Persistent Receive");
            persistentReceive.start();
        }

        public void endPersistentReceive() {
            stopPersistentReceive = true;
        }

        public boolean addressesAreEqual(InetAddress address1, InetAddress address2) {
            return address1.toString().contains(address2.toString());
        }

    }
}
