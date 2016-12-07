# Lab 2: Introduction to Socket Programming - Part 2
 
 This lab is a continuation of the previous lab. This lab will be focusing on utilizing the UDP network protocol.
 
 The third party library will be used again. Provided for you in the `src` directory are two Java classes. Using the library and the two classes, you will create a very simple socket communication application.
 
 You will take interface `jsocket.datagram.Datagram` and provide your own implementation in the class `SimpleDatagram`. This lab will assist in filling out the stubbed out code. In the `SimpleDatagramTest` class, you will startup as a server or client. Starting up as a server will be very different than the TCP lab. Unlike the TCP lab, there will not be a `java.net.ServerSocket` to receive connections. Instead, a single `java.net.DatagramSocket` will receive all packets coming in on the port it is bound to. This is due to the way UDP works.
 
 The application you are creating is the same as the previous lab. It will start out by asking for an IP address to connect to if it starts up as a client. Use `InetAddress` to handle the string. The server will go straight to waiting for a packet to come in. Similarly to the previous lab, you will need to set a buffer size to read any data from the packet. Once you have setup the `SimpleDatagram` instance, you can begin handling message exchanging. The server should wait for a message to come in. This is since the UDP connection is not actively connected to another computer. The server is re-actively connected to the client. Once the server has received the message, send keyboard input back as response. The client is the reverse of the server. Once the session has finished, close the connection.
 
- `SimpleDatagram` instructions: [SimpleDatagram.md](SimpleDatagram.md)
- `SimpleDatagramTest` instructions: [SimpleSocketTest.md](SimpleDatagramTest.md)
 
Once you have finished filling in the code, do the following to build the application:
 1. `javac -d out -cp ../lib/jsocket_0.5.0-beta3.jar src/SimpleDatagramTest.java src/SimpleDatagram.java`
 2. `cd out`
 3. run as client: `java SimpleDatagramTest 0`
 4. run as server: `java SimpleDatagramTest 1`

You will be graded on a finished application and screenshot as proof it runs. If code fails to compile and run properly, you will receive a 0.
 
|Items |Points|
|:-----:|:-----:|
|source code| /90|
|screenshot| /10|

Submit your code and a screenshot of the message exchange in a zip folder named your last name and first initial lab2.zip, i.e. {last}{fi}lab2.zip.