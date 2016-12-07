# Lab 5: Handling HTTP Requests

This lab focuses on creating a server application that parses HTTP requests. This is the third lab in the protocol series. The task for this lab will be to parse a raw HTTP request into a manageable/usable object as well a composing an HTTP responses. Doing this allows for security checks to be put in place so that a valid HTTP request can be processed. To do this, it is important to understand the structure. 

Look to the following instructions:

- `HttpParser` instructions: [HttpParser.md](HttpParser.md)
- `Exceptions` instruction: [Exceptions.md](Exceptions.md)
- `HttpContext` instructions: [HttpContext.md](HttpContext.md)

Once you have finished filling in the code, do the following to build the application:
 1. `javac -d out -cp ../lib/jsocket_0.5.0-beta3.jar src/*.java`
 2. `cd out`
 3. run as server: `java HttpServer`
 4. use `curl` to interact with the server: https://curl.haxx.se/docs/httpscripting.html
 
You will be graded on a finished application, screenshot of the `curl` commands working with the server. If code fails to compile and run properly, you will receive a 0.
  
|Items |Points|
|:-----:|:-----:|
|source code| /80|
|screenshot| /10|
 
Submit your code and a screenshot of the message exchange in a zip folder named your last name and first initial lab5.zip, i.e. {last}{fi}lab5.zip.