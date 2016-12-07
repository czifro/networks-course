import java.util.HashMap;
import java.util.Map;

/**
 * @author Will Czifro
 */
public class HttpHeaders {

    /**
     * Headers are sent as a key -> value pair, so they should be kep that way
     */
    private Map<String, String> headers = new HashMap<>();

    public boolean add(String key, String value) {
        if (!headers.containsKey(key)) {
            headers.put(key, value);
            return true;
        }
        return false;
    }

    public String get(String key) {
        if (!headers.containsKey(key))
            return null;
        return headers.get(key);
    }
}
