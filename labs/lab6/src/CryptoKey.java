import javax.crypto.Cipher;

/**
 * @author Will Czifro
 */
public interface CryptoKey {

    Cipher getEncryptCipher();

    Cipher getDecryptCipher();

    void updateEncryptIV(long dataLen);

    void updateDecryptIV(byte[] newIV);

    byte[] getIV();
}
