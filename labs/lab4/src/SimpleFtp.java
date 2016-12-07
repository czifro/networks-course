import jsocket.socket.Socket;
import jsocket.util.ByteUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Will Czifro
 */
public class SimpleFtp {

    private Socket conn;
    private int fileChunkSize = 1024;

    public SimpleFtp(Socket conn) {
        this.conn = conn;
    }

    public File receiveFile(String saveLocation) {
        try {
            return null; // FIXME
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendFile(File file) {
        try {
            // FIXME
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        conn.close();
    }

    static class Helper {

        public static boolean checksumsMatch(byte[] checksum1, byte[] checksum2) {
            if (checksum1.length != checksum2.length)
                return false;
            for (int i = 0; i < checksum1.length; ++i) {
                if (checksum1[i] != checksum2[i])
                    return false;
            }
            return true;
        }
    }
}
