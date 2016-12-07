# Exceptions Instructions

Understanding the structure of an HTTP response is important in order to compose a response. The following is the HTTP response grammar:

```
<HTTP_RESPONSE> = <SCHEME_VERSION> <STATUS_CODE> <EOL>
                  <HEADERS>
                  <EOL>
                  (<BODY>)?

  <SCHEME_VERSION> = <SCHEME> <FORWARD_SLASH> <VERSION>
    <SCHEME> = <STRING>
    <VERSION> = <FLOAT>
    
  <STATUS_CODE> = <INTEGER> <SPACE> <STRING>
  
  <HEADERS> = <HEADERKEY><DEL><HEADERVAL><EOL>
             (<HEADERS>)*
    <HEADERKEY> = <STRING>
    <DEL> = <COLON>(<SPACE>)?
    <HEADERVAL> = <STRING>
    
  <EOL> = '\r\n' // if request used '\n', use that instead.
  <BODY> = <BYTE_ARRAY>
```

`Exceptions::createResponseWithBody`:
Create a response that matches the grammar above. A `String` can make it easier to compose the response. Use `Exceptions::serverHeader`, `Exceptions::contentTypeHeader`, and `Exceptions::contentLengthHeader` to add headers to the response. Add `msg` to the response then use `jsocket.socket.Socket::send` to send the response.