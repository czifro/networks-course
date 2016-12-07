
import jsocket.datagram.Datagram;
import jsocket.exceptions.DatagramConnectionException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Will Czifro
 */
public class SimpleDatagram implements Datagram {

    private DatagramSocket conn;

    private InetAddress defaultAddress;

    private int defaultPort;

    private int bufferSize;

    private DatagramPacket lastPacket;

    public SimpleDatagram(DatagramSocket conn) {
        this.conn = conn;
    }

    public DatagramPacket receivePacket() {
        // FIXME: Read in a packet from connection
        try {
            DatagramPacket packet =
                    new DatagramPacket(new byte[bufferSize],
                            0,
                            bufferSize);
            conn.receive(packet);
            return (lastPacket = packet); // ***
        } catch (IOException e) {
            throw new DatagramConnectionException(e);
        }
        // ENDFIXME
    }

    public byte[] receive() {
        // FIXME: Read in bytes from received packet
        return receivePacket().getData();
        // ENDFIXME
    }

    /**
     * Returns InetAddress from last received packet
     * @return InetAddress
     */
    public InetAddress getReceivedInetAddress() {
        if (lastPacket == null)
            return null;
        return lastPacket.getAddress();
    }

    /**
     * Returns port packet was received on
     * @return
     */
    public int getReceivedPort() {
        if (lastPacket == null)
            return 0;
        return lastPacket.getPort();
    }

    /**
     * Gets the default InetAddress used for quickly
     * sending bytes
     * When {@code send(byte[],InetAddress,int)} is
     * used, defaultAddress will be updated to
     * provided InetAddress
     * @return InetAddress
     */
    public InetAddress getDefaultInetAddress() {
        return defaultAddress;
    }

    /**
     * Gets the default port used for quickly
     * sending bytes
     * When {@code send(byte[],InetAddress,int)} is
     * used, defaultPort will be updated to
     * provided port
     * @return int
     */
    public int getDefaultPort() {
        return defaultPort;
    }

    /**
     * Conveniently preset the InetAddress and port
     * the packet will be sent to
     * @param address InetAddress
     * @param port int
     */
    public void setDefaultInetAddressAndPort(InetAddress address, int port) {
        defaultAddress = address;
        defaultPort = port;
    }

    /**
     * Set the buffer size for incoming packets
     * @param i buffer size
     */
    public void setBufferSize(int i) {
        this.bufferSize = i;
    }

    /**
     * Sends packet
     * @param packet DatagramPacket
     */
    public void send(DatagramPacket packet) {
        // FIXME: send packet using connection
        try {
            conn.send(packet);
        } catch (IOException e) {
            throw new DatagramConnectionException(e);
        }
        // ENDFIXME
    }

    /**
     * Sends bytes to specified InetAddress on
     * specified port
     * @param bytes byte[]
     * @param address InetAddress
     * @param port int
     */
    public void send(byte[] bytes, InetAddress address, int port) {
        // FIXME: wrap params with DataPacket and send
        if (address == null)
            throw new DatagramConnectionException("Need address to send data to");
        send(new DatagramPacket(bytes, bytes.length,
                address, port));
        // ENDFIXME
    }

    /**
     * Sends bytes
     * Attempts to use the set default InetAddress
     * and port to send packet to. If those are not
     * set, attempts to send to source of last
     * received packet.
     * @param bytes
     */
    public void send(byte[] bytes) {
        // FIXME: use defaultAddress/port to send bytes
        if (defaultAddress == null && lastPacket == null)
            throw new DatagramConnectionException("Need address to send data to");
        if (defaultAddress == null) {
            defaultAddress = lastPacket.getAddress();
            defaultPort = lastPacket.getPort();
        }
        send(bytes, defaultAddress, defaultPort);
        // ENDFIXME
    }

    /**
     * Closes connection
     */
    public void close() {
        conn.close();
    }
}
