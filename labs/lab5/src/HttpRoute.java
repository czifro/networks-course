import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will Czifro
 */
public class HttpRoute {

    private String fullRoute;

    private String[] routeParts;

    private Map<String, String> routeData;

    public HttpRoute(String fullRoute) {
        this.fullRoute = fullRoute;
    }

    /**
     * The route of a request typically uses the following format:
     * /{root-route}/{sub-route}/{endpoint}
     * Sometimes query parameters will be appended to the route.
     * Usually, a '?' separates the route from the params.
     * An '&' is the delimiter for the params. The values for
     * the params are url encoded. Before they can be used, they
     * need to be url decoded.
     */
    public void parse() {
        String route = fullRoute;
        if (fullRoute.contains("?")) {
            try {
                String[] temp = fullRoute.split("\\?");
                if (temp.length != 2)
                    throw new RuntimeException();
                String data = temp[1];
                routeData = UrlEncodedUtil.toMapObject(data);
                route = temp[0];
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        if (!route.contains("/"))
            throw new RuntimeException();
        routeParts = route.substring(1).split("/");
    }

    public String[] getRouteParts() {
        return routeParts;
    }

    public String getRoute() {
        return "/" + String.join("/", routeParts);
    }

    public String getRouteDataValue(String key) {
        if (routeData.containsKey(key))
            return routeData.get(key);
        return null;
    }

    public Collection<String> routeDataValues() {
        if (routeData == null)
            return null;
        return routeData.values();
    }
}
