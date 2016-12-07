import sun.security.rsa.RSAKeyPairGenerator;
import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;

/**
 * @author Will Czifro
 */
public class RSAKey implements CryptoKey {

    Cipher encryptCipher, decryptCipher;

    PublicKey remotePublicKey;

    KeyPair keyPair;

    public RSAKey(KeyPair keyPair) {
        this.keyPair = keyPair;
        initEncryptCipher();
    }

    public void initEncryptCipher() {
        try {
            // todo: fix me
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public void initDecryptCipher() {
        try {
            // todo: fix me
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public Cipher getEncryptCipher() {
        return encryptCipher;
    }

    public Cipher getDecryptCipher() {
        return decryptCipher;
    }

    public void updateEncryptIV(long dataLen) {}

    public void updateDecryptIV(byte[] newIV) {}

    public byte[] getIV() {
        return new byte[0];
    }

    public void addPublicKey(PublicKey publicKey) {
        remotePublicKey = publicKey;
        initDecryptCipher();
    }

    public void addPublicKeyFromPacket(RSAKeyPacket packet) {
        packet.parseBytes();
        try {
            BigInteger mod = new BigInteger(packet.modulus);
            BigInteger exp = new BigInteger(packet.exponent);
            RSAPublicKeyImpl publicKey = new RSAPublicKeyImpl(mod, exp);
            addPublicKey(publicKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] generateSharedKey() {
        try {
            SecureRandom rand = SecureRandom.getInstanceStrong();
            String key = "";
            while (key.length() != 16) key = new BigInteger(80, rand).toString(32);
            return key.getBytes();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public RSAPublicKeyImpl getPublic() {
        return (RSAPublicKeyImpl)keyPair.getPublic();
    }

    public RSAKeyPacket getPublicAsPacket() {
        return null; // todo: fix me
    }

    public static RSAKey createNewKey() {
        RSAKeyPairGenerator generator = new RSAKeyPairGenerator();
        KeyPair keyPair = generator.generateKeyPair();
        return new RSAKey(keyPair);
    }

    enum KeySize {
        BIT_LENGTH_1024(1024),
        BIT_LENGTH_2048(2048),
        BIT_LENGTH_4096(4096);


        private int value;
        KeySize(int v) {
            value = v;
        }

        public int toInt() {
            return value;
        }
    }
}
