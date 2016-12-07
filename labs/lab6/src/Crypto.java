import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 * @author Will Czifro
 */
public class Crypto {

    public static byte[] encrypt(byte[] data, CryptoKey key) {
        try {
            key.updateEncryptIV(data.length);
            return key.getEncryptCipher().doFinal(data);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Packet encryptToPacket(byte[] data, CryptoKey key) {
        byte[] cipher = encrypt(data, key);
        EncryptedMessagePacket packet = new EncryptedMessagePacket();
        packet.messageLen = data.length;
        packet.message = data;
        byte[] iv = key.getIV();
        packet.ivLen = iv.length;
        packet.iv = iv;
        return packet;
    }

    public static byte[] decrypt(byte[] data, CryptoKey key) {
        try {
            return key.getDecryptCipher().doFinal(data);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decrypt(byte[] data, CryptoKey key, byte[] iv) {
        key.updateDecryptIV(iv);
        return decrypt(data, key);
    }

    public static byte[] decryptFromPacket(EncryptedMessagePacket packet, CryptoKey key) {
        return decrypt(packet.message, key, packet.iv);
    }
}
