import jsocket.socket.Socket;

/**
 * @author Will Czifro
 */
public class Exceptions {

    public static void malformedRequest(Socket socket, boolean crnl) {
        createResponseWithBody(socket, "Malformed Request", StatusCode.BadRequest, crnl);
    }

    public static void unsupportedMethod(Socket socket, String method, boolean crnl) {
        createResponseWithBody(socket, "'" + method + "' is not supported", StatusCode.MethodNotAllowed, crnl);
    }

    public static void unsupportedMediaType(Socket socket, String contentType, boolean crnl) {
        createResponseWithBody(socket, "'" + contentType + "' is not supported", StatusCode.UnsupportedMediaType, crnl);
    }

    public static void createResponseWithBody(Socket socket, String msg, StatusCode code, boolean crnl) {
        String eol = crnl ? "\r\n" : "\n";
        String response = "";

        // FIXME

        throw new RuntimeException(msg);
    }

    private static String serverHeader(String eol) {
        return "server: Simple-Server" + eol;
    }

    private static String contentTypeHeader(String eol) {
        return "content-type: text/plain" + eol;
    }

    private static String contentLengthHeader(int length, String eol) {
        return "content-length: " + length + eol;
    }
}
