import javax.crypto.spec.IvParameterSpec;
import java.math.BigInteger;

/**
 * @author Will Czifro
 */
public class IvParameterUtil {

    private static int BLOCK_SIZE = 16;

    public static IvParameterSpec createNewIvParameterSpec() {
        byte[] iv = StringHelper.createRandomString(16, 80).getBytes();
        return new IvParameterSpec(iv);
    }

    public static IvParameterSpec wrap(byte[] iv) {
        return new IvParameterSpec(iv);
    }
}
