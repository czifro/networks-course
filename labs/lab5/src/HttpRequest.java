/**
 * Object representation of an HTTP request
 * @author Will Czifro
 */
public class HttpRequest {

    private HttpMethod method;
    private HttpHeaders headers;
    private HttpRoute route;
    private HttpBody body;

    private String scheme;
    private double version;

    public HttpRequest(HttpMethod method, HttpHeaders headers, HttpRoute route, String scheme, double version) {
        this.method = method;
        this.headers = headers;
        this.route = route;
        this.scheme = scheme;
        this.version = version;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public HttpRoute getRoute() {
        return route;
    }

    public String getScheme() {
        return scheme;
    }

    public double getVersion() {
        return version;
    }

    public HttpBody getBody() {
        return body;
    }

    public void setBody(HttpBody body) {
        this.body = body;
    }
}
