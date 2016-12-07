import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;

/**
 * @author Will Czifro
 */
public class RouteAction {

    private Method method;
    private Object instance;

    public RouteAction(Method method, Object instance) {
        this.method = method;
        this.instance = instance;
    }

    /**
     * Invokes a controller method according to the data
     * attached to the request.
     * @param request
     * @return
     */
    public Object invoke(HttpRequest request) {
        try {
            Parameter[] parameters = method.getParameters();
            Object[] args = new Object[parameters.length];
            if (args.length == 0) {
                return method.invoke(instance, new Object[0]);
            }
            if (request.getMethod() == HttpMethod.GET)
                initializeArgs(args, parameters, request.getRoute().routeDataValues());
            else
                initializeArgs(args, parameters, request.getBody().values());
            return method.invoke(instance, args);
        } catch (Throwable t) {
            throw new RuntimeException();
        }
    }

    /**
     * Casts a data value to the type of the parameter
     * only used for primitive types.
     * @param s
     * @param p
     * @return
     */
    private Object castParameterToPrimitive(String s, Parameter p) {
        if (p.getType().equals(Integer.TYPE))
            return Integer.parseInt(s);
        if (p.getType().equals(Double.TYPE))
            return Double.parseDouble(s);
        if (p.getType().equals(Boolean.TYPE))
            return Boolean.parseBoolean(s);
        // append more checks here
        return null;
    }

    /**
     * Initialize arguments for invoking method
     * attempts to cast data to a primitive type
     * otherwise, defaults to string.e
     * @param args
     * @param parameters
     * @param data
     */
    private void initializeArgs(Object[] args, Parameter[] parameters, Collection<String> data) {
        String[] vals = data.toArray(new String[data.size()]);
        for (int i = 0; i < args.length; ++i) {
            // try and caste to primitive type
            int stringTypeIndex = 0;
            for (int j = 0; j < parameters.length; ++j) {
                if (parameters[j].getType() == String.class) {
                    stringTypeIndex = j;
                    continue;
                }
                try {
                    args[i] = castParameterToPrimitive(vals[i], parameters[j]);
                    parameters = removeAt(parameters,j);
                    break;
                } catch (Throwable t) {
                    continue;
                }
            }
            if (args[i] == null) {
                args[i] = vals[i];
                parameters = removeAt(parameters, stringTypeIndex);
            }
        }
    }

    private Parameter[] removeAt(Parameter[] parameters, int index) {
        Parameter[] parameters1 = new Parameter[parameters.length-1];
        for (int i = 0, j = 0; i < parameters1.length; ++i, ++j) {
            if (i != index)
                parameters1[i] = parameters[j];
            else
                ++j;
        }
        return parameters1;
    }
}
