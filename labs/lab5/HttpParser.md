# HttpParser Instructions

In order to parse an HTTP request, understanding the structure is paramount. The following is the grammar for an HTTP request:

```
<HTTP_REQUEST> = <METHOD> <ROUTE> <SCHEME_VERSION> <EOL>
                 <HEADERS>
                 <EOL>
                 (<BODY>)?      // '?' means optional
    
  <METHOD> = <STRING> <SPACE>

  <ROUTE> = <ROOT> (<ROUTE>)*|(<QUERY>|<SPACE>) // '|' means option, '*' means zero or more
    <ROOT> = <FORWARD_SLASH> <STRING>
    <QUERY> = <QUESTION_MARK> <KEYVALPAIR> ((<AMPERSAND> <KEYVALPAIR>)*|<SPACE>) 
      <KEYVALPAIR> = <KEY><EQUAL><VALUE>
        <KEY> = <STRING>
        <VALUE> = <STRING>

  <SCHEME_VERSION> = <SCHEME> <FORWARD_SLASH> <VERSION>
    <SCHEME> = <STRING>
    <VERSION> = <FLOAT>

  <HEADERS> = <HEADERKEY><DEL><HEADERVAL><EOL>
              (<HEADERS>)*
    <HEADERKEY> = <STRING>
    <DEL> = <COLON>(<SPACE>)?
    <HEADERVAL> = <STRING>
    
  <EOL> = '\r\n'
  <BODY> = <BYTE_ARRAY>
```

An advanced parser would user this grammar and meticulously make sure the request is valid, which is best for security checks. This lab will not go that in depth. Instead you will be looking for key characters and splitting the raw message up at key characters. ' ', ':', and '\r\n' are the key characters to look out for. The following are instructions for implementing each part of the `HttpParser`.

`HttpParser::readInRequest`:
Create a `byte[]` buffer set to `maxHeaderSize` in size. Use `jsocket.socket.SocketImpl::receive` to read in a `byte[]`. The buffer size has been set to 1, so the `byte[]` will contain single `byte`. Add the `byte` to the buffer then check for back to back end of line characters. The request will likely use '\r\n' at the end of each line. However, you should also support '\n'. A `byte` called `cr` will contain '\r' and another called `nl` will contain '\n'. Back to back `nl` characters will be easier to check, so do that first. The other involves more checks. Two `nl`s takes two checks while two `crnl`s takes four checks. Be sure to account for this when indexing the array to avoid a < 0 index. If the `crnl` check matches, set the `boolean crnl` to true. Record the ending index (+1 to keep the last character) and return the buffer with just the range 0 to end index. If you did not reach the end of the request and the buffer is full, use the following line of code: 

```
Exceptions.createResponseWithBody(socket, "Request too big", StatusCode.BadRequest, crnl);
```

The request was to big to handle. If this happens frequently, double the value of `maxHeaderSize`. In production, you will want to make sure the buffer size is at a manageable size.

`HttpParser::parseMethod(byte[])`:
According to the grammar above, the method is followed by a ' ' (space). Iterate over the raw message to locate the space. Before you do, capture the value of `cursor` to retain the starting point. Update `cursor` iterate over the array. Once you have found it break from the looping. If the `cursor` reached the end of the array, do the following:

```
Exceptions.malformedRequest(socket, crnl);
```

Otherwise, convert the range from start to the `cursor` to a `String` and then use `HttpMethod::toHttpMethod` to convert to a `HttpMethod` instance. If `HttpMethod::toHttpMethod` returns null, do the following:

```
Exceptions.unsupportedMethod(socket, method, crnl);
```

`method` is the string value of the `byte` range. If your server does not support a certain method, you need to let the client know. Finally, return the `HttpMethod` instance.

`HttpParser::parserRoute(byte[])`:
According to the grammar, the next thing is the route. Again, look for the space. Do notice the `++cursor`. This is to make sure the previous space is not caught by the search for the next space. If the next space cannot be found, make a call to `Exceptions::malformedRequest`. Be sure to capture the value of `cursor` to know where the route begins. Convert the range to a `String`. Create a new instance of `HttpRoute` then call `HttpRoute::parse`. `HttpRoute::parse` will finish parsing the route.

`HttpParser::parseHeaders(byte[])`:
After having parsed the scheme and version, the headers are the last thing to parse. The `EOL` is the separator between headers. Two `EOL`s in a row is the end of the request. The terminating condition for the `while` loop has been done for you. The `while` loop is to find all headers. An inner loop is needed to find the end of a single header. Locate the `EOL`. Use the captured start position and the end position to grab the range for the single header and store it in a temp `byte[]`. Iterate over the temp variable to locate the ':'. With the captured start position of this temp var and the end position of the header key, convert that range to a `String`. Shift the temp cursor of the ':'. If the temp cursor points to a ' ' (space), shift off of that. Capture the start of the header value and find the `EOL` position. Convert that range to a `String` and use `HttpHeaders::add(String,String)` to store the header key and value. `cursor` has been updated for you.

`HttpParser::parseIncomingRequest`:
Most of this method is already implemented. Parsing the scheme and version need to be finished. After extracting the `byte` range of the scheme and version, split that on the '/'. Covert the scheme `byte` range to a `String` and the version `byte` range to a `Double`. If the `String` does not equal 'HTTP', do the following:

```
Exceptions.createResponseWithBody(
    socket,
    "'" + scheme + "' not supported",
    StatusCode.BadRequest,
    crnl
);
```

If the version is not 1.1, do the following:

```
Exceptions.createResponseWithBody(
    socket,
    "'" + version + "' not supported",
    StatusCode.HttpVersionNotSupported,
    crnl
);
```

`HttpParser::parserHttpBody`:
This is already finished for you. 