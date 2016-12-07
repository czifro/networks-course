/**
 * Enum of all HTTP 1.1 status codes
 * @author Will Czifro
 */
public enum StatusCode {

    Continue(100, "Continue"), SwitchingProtocols(101, "Switching Protocols"),
    Ok(200, "OK"), Created(201, "Created"), Accepted(202, "Accepted"),
    NonAuthoritativeInfo(203, "Non-Authoritative Information"), NoContent(204, "No Content"),
    ResetContent(205, "Reset Content"), PartialContent(206, "Partial Content"),
    MultipleChoices(300, "Multiple Choices"), MovedPermanently(301, "Moved Permanently"),
    Found(302, "Found"), SeeOther(303, "See Other"), NotModified(304, "Not Modified"),
    UseProxy(305, "Use Proxy"), Unused(306, "(Unused)"),
    TemporaryRedirect(307, "Temporary Redirect"), BadRequest(400, "Bad Request"),
    Unauthorized(401, "Unauthorized"), PaymentRequired(402, "Payment Required"),
    Forbidden(403, "Forbidden"), NotFound(404, "Not Found"),
    MethodNotAllowed(405, "Method Not Allowed"), NotAcceptable(406, "Not Acceptable"),
    ProxyAuthRequired(407, "Proxy Authentication Required"), RequestTimeout(408, "Request Timeout"),
    Conflict(409, "Conflict"), Gone(410, "Gone"), LengthRequired(411, "Length Required"),
    PreconditionFailed(412, "Precondition Failed"), RequestEntityTooLarge(413, "Request Entity Too Large"),
    RequestUriTooLong(414, "Request-URI Too Long"), UnsupportedMediaType(415, "Unsupported Media Type"),
    RequestedRangeNotSat(416, "Requested Range Not Satisfiable"), ExpectationFailed(417, "Expectation Failed"),
    InternalServerError(500, "Internal Server Error"), NotImplemented(501, "Not Implemented"),
    BadGateway(502, "Bad Gateway"), ServiceUnavailable(503, "Service Unavailable"),
    GatewayTimeout(504, "Gateway Timeout"), HttpVersionNotSupported(505, "HTTP Version Not Supported");

    /**
     * Integer representation of status code
     */
    public final int _intValue;

    /**
     * String representation of status code
     */
    private final String _strValue;

    StatusCode(final int intValue, final String strValue) {
        _intValue = intValue;
        _strValue = strValue;
    }

    public int asInt() {
        return _intValue;
    }

    public String asString() {
        return _strValue;
    }
}