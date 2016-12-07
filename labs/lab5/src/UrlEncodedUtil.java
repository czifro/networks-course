import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will Czifro
 */
public class UrlEncodedUtil {

    /**
     * Converts url encoded data to a {@link Map}
     * @param urlEncodedData
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> toMapObject(String urlEncodedData) throws UnsupportedEncodingException {
        String[] params = urlEncodedData.split("&");

        Map<String, String> data = new HashMap<>();

        for (String param : params) {
            String key = param.substring(0, param.indexOf('='));
            String value = param.substring(param.indexOf('=')+1);
            data.put(key, URLDecoder.decode(value, "UTF-8"));
        }

        return data;
    }
}