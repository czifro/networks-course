import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will Czifro
 */
public class Router {

    public static Router DEFAULT_ROUTER;

    static {
        DEFAULT_ROUTER = new Router();
        DEFAULT_ROUTER.addValuesController(new ValuesController());
    }

    private Map<Route, RouteAction> routes;

    public Router() {
        routes = new HashMap<>();
    }

    /**
     * Attempts to execute the request based on a route
     * if route cannot be found, StatusCode.NotFound will be returned
     * @param request
     * @return
     */
    public Object executeRequest(HttpRequest request) {
        RouteAction action = routes.get(new Route(request.getRoute().getRoute(), request.getMethod()));
        if (action == null) {
            return StatusCode.NotFound;
        }

        return action.invoke(request);
    }

    /**
     * Add routes to handle a Values controller instance
     * @param vc
     */
    private void addValuesController(ValuesController vc) {
        Method[] methods = vc.getClass().getDeclaredMethods();

        for (Method m : methods) {
            if (m.getName().equals("get")) {
                routes.put(
                        new Route("/api/values", HttpMethod.GET),
                        new RouteAction(m, vc)
                );
            }
            if (m.getName().equals("put")) {
                routes.put(
                        new Route("/api/values", HttpMethod.PUT),
                        new RouteAction(m, vc)
                );
            }
            if (m.getName().equals("delete")) {
                routes.put(
                        new Route("/api/values", HttpMethod.DELETE),
                        new RouteAction(m, vc)
                );
            }
        }
    }
}
