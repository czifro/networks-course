import java.util.Objects;

/**
 * Representation of an HTTP route for executing a request
 * @author Will Czifro
 */
public class Route {

    private String route;
    private HttpMethod method;

    public Route(String route, HttpMethod method) {
        this.route = route;
        this.method = method;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Route))
            return false;

        Route r = (Route) o;

        return r.method == method && r.route.equals(route);
    }

    public int hashCode() {
        return method.hashCode() * route.hashCode();
    }
}
