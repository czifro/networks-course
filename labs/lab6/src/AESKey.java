import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

/**
 * @author Will Czifro
 */
public class AESKey implements CryptoKey {

    Cipher encryptCipher, decryptCipher;
    SecretKey key;

    public AESKey(byte[] key) {
        try {
            // todo: fix me
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidKeyException | InvalidParameterSpecException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public Cipher getEncryptCipher() {
        return encryptCipher;
    }

    public Cipher getDecryptCipher() {
        return decryptCipher;
    }

    public void updateEncryptIV(long dataLen) {
        try {
            IvParameterSpec iv = IvParameterUtil.createNewIvParameterSpec();
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, iv);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDecryptIV(byte[] newIV) {
        try {
            IvParameterSpec iv = IvParameterUtil.wrap(newIV);
            decryptCipher.init(Cipher.DECRYPT_MODE, key, iv);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getIV() {
        return encryptCipher.getIV();
    }
}
