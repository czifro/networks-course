import jsocket.util.FilterFunctionType;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author Will Czifro
 */
public class StringHelper {

    private static SecureRandom random = new SecureRandom();

    public static String createRandomString(int length, int numBits) {
        String str = new BigInteger(numBits, random).toString(32);
        while (str.length() != length) str = new BigInteger(numBits, random).toString(32);
        return str;
    }

    public static String bytesToString(byte[] bytes, boolean useBase64Decoder) {
        try {
            String str = new String(bytes);
            if (useBase64Decoder) {
                byte[] decoded = new BASE64Decoder().decodeBuffer(str);
                str = new String(decoded);
            }
            return FilterFunctionType.NULL_CHARS.getFunc().apply(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] stringToBytes(String str, boolean useBase64Encoder) {
        if (useBase64Encoder) {
            str = new BASE64Encoder().encode(str.getBytes());
        }
        return str.getBytes();
    }
}
