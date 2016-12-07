# Lab 1: Introduction to Socket Programming - Part 1

This lab is part 1 of 2 in the introduction to socket programming. This lab will be focusing on utilizing the TCP network protocol.  

A third party library will be utilized by this lab. It can be found in the `lib` folder one directory up. More info on this library can be found here: [https://github.com/czifro-tech/jsocket/tree/vNext_0_5_0](https://github.com/czifro-tech/jsocket/tree/vNext_0_5_0). Provided for you in the `src` directory are two Java classes. Using the library and the two classes, you will create a very simple socket communication application.

You will take the interface `jsocket.socket.Socket` and provide your own implementation in the class `SimpleSocket`. This lab will assist in filling out the stubbed out code. In the `SimpleSocketTest` class, you will startup as a server or client. If you startup as a server, you will need to listen for an incoming connection and accept it. This incoming connection will be a `java.net.Socket` object. You will use `SimpleSocket` to help handle this new connection. In a loop, you will go back and forth between receiving a message and sending a message. Once you have finished, run the code and with another student exchange messages back and forth.

You will notice that the program asks for an IP address. You will need to know the IP address of the machine you are trying to connect to. There are two ways to handle the inputted IP address. One is to is an `java.net.InetAddress` as an argument for the `java.net.Socket` constructor. The other is to simply leave it a string and pass that to `java.net.Socket`. In either instance, both will be wrapped by a `java.net.InetSocketAddress` object. You may choose which ever method you like.

- `SimpleSocket` instructions: [SimpleSocket.md](SimpleSocket.md)
- `SimpleSocketTest` instructions: [SimpleSocketTest.md](SimpleSocketTest.md)

Once you have finished filling in the code, do the following to build the application:
 1. `javac -d out -cp ../lib/jsocket_0.5.0-beta3.jar src/SimpleSocketTest.java src/SimpleSocket.java`
 2. `cd out`
 3. run as client: `java SimpleSocketTest 0`
 4. run as server: `java SimpleSocketTest 1`

You will be graded on a finished application and screenshot as proof it runs. If code fails to compile and run properly, you will receive a 0.
 
|Items |Points|
|:-----:|:-----:|
|source code| /90|
|screenshot| /10|

Submit your code and a screenshot of the message exchange in a zip folder named your last name and first initial lab1.zip, i.e. {last}{fi}lab1.zip.