import jsocket.socket.Socket;

import java.nio.charset.Charset;

/**
 * @author Will Czifro
 */
public class HttpParser {

    private Socket socket;
    private int maxHeaderSize = 1024;
    private int cursor = 0;

    private boolean crnl = false;

    public HttpParser(Socket socket) {
        this.socket = socket;
        socket.setBufferSize(1);
    }

    /**
     * Parses the body according to Content-Length and
     * Content-Type.
     * @param request
     */
    public void parseHttpBody(HttpRequest request) {
        String cl = request.getHeaders().get("content-length");
        if (cl == null)
            return;
        ContentType contentType =
                ContentType.toContentType(request.getHeaders().get("content-type"));
        if (contentType == null) {
            String ct = request.getHeaders().get("content-type");
            Exceptions.unsupportedMediaType(
                socket,
                ct,
                crnl
            );
        }

        int contentLength = Integer.parseInt(cl);

        byte[] raw = socket.receiveAll(contentLength);

        try {
            String rawData = new String(raw, Charset.defaultCharset());
            HttpBody body = new HttpBody(rawData);
            body.parseAsMap();
            request.setBody(body);
        } catch (Throwable t) {
            Exceptions.createResponseWithBody(socket, "Internal Server Error", StatusCode.InternalServerError, crnl);
        }
    }

    public HttpRequest parseIncomingRequest() {
        try {
            HttpMethod method;
            HttpRoute route;
            HttpHeaders headers;
            String scheme = null;
            double version = 0.0;

            /*
             Request format:
             METHOD /route SCHEME/VERSION\n // could use CRNL ending
             HEADERKEY: HEADERVAL\n
             .
             .
             .
             \n // end of headers, everything past this point is body
            */

            byte[] raw = readInRequest();

            method = parseMethod(raw);
            route = parseRoute(raw);

            // FIXME: parse scheme and version


            headers = parseHeaders(raw);

            return new HttpRequest(method, headers, route, scheme, version);
        } catch (Throwable t) {
            Exceptions.malformedRequest(socket, crnl);
            return null;
        }
    }

    private HttpHeaders parseHeaders(byte[] raw) {
        cursor += (crnl ? 2 : 1); // shift to beginning of headers
        boolean throwException = true;
        HttpHeaders headers = new HttpHeaders();

        // grab all headers
        while (true) {
            // FIXME: find end of header
            int start = cursor;


            // if we have not shifted or shifted only once and we are at the end
            if ((start == cursor || start + 1 == cursor) &&
                    cursor + (crnl ? 2 : 1) >= raw.length) {
                if (throwException) // this is in case we have not parsed a header yet
                    Exceptions.malformedRequest(socket, crnl);
                break;
            }
            throwException = false;

            byte[] rawHeader = null; // FIXME: grab range

            // FIXME: find division between header key and header value, ':'
            int i = 0;
            start = i;

            if (i >= rawHeader.length)
                Exceptions.malformedRequest(socket, crnl);

            // FIXME: set headerKey and headerValue and add them to headers
            String headerKey, headerValue;

            cursor += (crnl ? 2 : 1); // shift to beginning of next header
        }
        return headers;
    }

    private HttpRoute parseRoute(byte[] raw) {
        byte SPACE = (byte) ' '; // space is end of route
        ++cursor; // shift off space from previous parsing

        // FIXME

        return null;
    }

    private HttpMethod parseMethod(byte[] raw) {
        byte SPACE = (byte) ' '; // space is end of method

        // FIXME

        return null;
    }

    /**
     * Reads in request, supports NL and CRNL line endings
     * @return raw data
     */
    private byte[] readInRequest() {
        byte nl = ((byte) '\n');
        byte cr = ((byte) '\r');
        byte[] raw = new byte[maxHeaderSize];

        // FIXME

        return null;
    }


    public boolean isCrnl() {
        return crnl;
    }
}
