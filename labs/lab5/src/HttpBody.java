import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

/**
 * Container for the body of an HTTP request
 * @author Will Czifro
 */
public class HttpBody {

    private String rawData;

    private Map<String, String> data;

    public HttpBody(String rawData) {
        this.rawData = rawData;
    }

    /**
     * Attempts to parse body into a {@link Map}
     * Body will be url encoded
     * @return
     * @throws UnsupportedEncodingException
     */
    public Map<String, String> parseAsMap() throws UnsupportedEncodingException {
        if (data == null)
            data = UrlEncodedUtil.toMapObject(rawData);
        return data;
    }

    public String get(String key) {
        if (!data.containsKey(key))
            return null;
        return data.get(key);
    }

    public Collection<String> values() {
        return data.values();
    }
}
