/**
 * An enum of the supported HTTP methods is purely for easier coding
 * @author Will Czifro
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String val;

    HttpMethod(String val) {
        this.val = val;
    }

    public static HttpMethod toHttpMethod(String val) {
        switch (val) {
            case "GET":
                return GET;
            case "POST":
                return POST;
            case "PUT":
                return PUT;
            case "DELETE":
                return DELETE;
            default:
                return null;
        }
    }
}
