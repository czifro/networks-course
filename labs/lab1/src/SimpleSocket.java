import jsocket.socket.Socket;


/**
 * A wrapper class that simplifies sending and receiving data on a socket connection
 */
public class SimpleSocket implements Socket {

    private java.net.Socket conn = null;
    // Add the preferred classes for reading and writing bytes

    //
    private int bufferSize = 0;

    public SimpleSocket(java.net.Socket conn) {
        // FIXME: initialize private variables
    }

    /**
     * Sets the buffer size for default read operations
     * @param i
     */
    public void setBufferSize(int i) {
        this.bufferSize = i;
    }

    /**
     * Receives bytes into byte[] with length of bufferSize
     * @return byte[]
     */
    public byte[] receive() {
        // FIXME: Read in bufferSize number of bytes and return byte[]

        return null;
    }

    /**
     * Receives bytes into byte[] with length of i
     * @param i
     * @return byte[]
     */
    public byte[] receiveAll(int i) {
        // FIXME: Read in i number of bytes and return byte[]
        return null;
    }

    /**
     * Sends bytes across output stream
     * @param bytes
     */
    public void send(byte[] bytes) {
        // FIXME: write all bytes to stream
    }

    /**
     * Closes streams and socket connection.
     */
    public void close() {
        // FIXME: attempt to close streams
    }
}
