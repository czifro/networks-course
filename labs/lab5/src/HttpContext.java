import jsocket.socket.Socket;

import java.util.ArrayList;

/**
 * @author Will Czifro
 */
public class HttpContext implements Runnable {

    private Socket socket;

    private HttpParser parser;

    private HttpRequest request;

    public HttpContext(Socket socket) {
        this.socket = socket;
        this.parser = new HttpParser(this.socket);
    }

    public void run() {
        try {
            request = parser.parseIncomingRequest();
            parser.parseHttpBody(request);
            Router router = Router.DEFAULT_ROUTER;
            Object o = router.executeRequest(request);
            if (o instanceof StatusCode) {
                if (o == StatusCode.NotFound) {
                    Exceptions.createResponseWithBody(
                            socket,
                            "'" + request.getRoute().getRoute() + "' not found",
                            (StatusCode) o,
                            parser.isCrnl()
                    );
                }
            }
            String s = o == null ? "" : o.toString();

            writeResponse(s, StatusCode.Ok, (parser.isCrnl() ? "\r\n" : "\n"));
            socket.close();
        } catch (Throwable t) {
            Exceptions.createResponseWithBody(
                    socket,
                    "Internal Server Error",
                    StatusCode.InternalServerError,
                    parser.isCrnl()
            );
        }
    }

    private void writeResponse(String msg, StatusCode code, String eol) {
        String response = "";

        // FIXME

        socket.send(response.getBytes());
    }
}
