/**
 * @author Will Czifro
 */
public enum ContentType {

    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    APPLICATION_JSON("application/json"),
    APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded");

    private String value;

    ContentType(String value) {
        this.value = value;
    }

    public static ContentType toContentType(String str) {
        // just illustrating, only 'application/x-www-form-urlencoded' is supported
//        if (TEXT_PLAIN.toString().equals(str))
//            return TEXT_PLAIN;
//        if (TEXT_HTML.toString().equals(str))
//            return TEXT_HTML;
//        if (APPLICATION_JSON.toString().equals(str))
//            return APPLICATION_JSON;
        if (APPLICATION_X_WWW_FORM_URLENCODED.toString().equals(str))
            return APPLICATION_X_WWW_FORM_URLENCODED;
        return null;
    }

    public String toString() {
        return value;
    }
}
