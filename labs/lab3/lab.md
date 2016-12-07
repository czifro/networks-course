# Lab 3: Creating a TCP Socket

This lab will take a UDP socket and impose a TCP protocol on top of it. This is to better understand how a TCP connection works. The following are the concepts covered by this lab that TCP implements:

1. Sliding Window: TCP uses a sliding window to send multiple packets at a time to provide faster transfers.
2. Data Integrity: TCP insures that all packets arrive to the receiver uncorrupted.

There are some features that will be left out:

1. Congestion Control: Tahoe, Reno, and New Reno are but a few congestion control algorithms. Since we are already doing sliding window, which is a component of this, it will be easier for now to just use a constant window size.
2. Multiple Connections per Port: This will not be covered.

In this lab, you will implement `NaiveTcpConnection` using the `jsocket.datagram.DatagramImpl` from the third party `lib`. `NaiveTcpConnection` can be a simple implementation. An advanced implementation would incorporate more features and be more efficient. The main focus is protocol. The `Packet`, `SlidingWindow`, and `ReceiveBuffer` are helper classes for implementing the protocol.

An important thing to think about when implementing a network protocol is the data that is sent between two nodes. If there is not an agreed upon format, two nodes are incapable of understanding any received nodes. The message format is outlined in [Packet.md](Packet.md).

Connecting to a server will follow a simple protocol. The server side will use `NaiveTcpConnection::waitForConnection`, and the client will use `NaiveTcpConnection::connect`. Using `NaiveTcpConnection::receive` and `NaiveTcpConnection::send`, the client and server will exchange messages similarly to the TCP protocol. See the following for instructions:

- `NaiveTcpConnection` instructions: [NaiveTcpConnection.md](NaiveTcpConnection.md)
- `Packet` instructions: [Packet.md](Packet.md)
- `ReceiveBuffer` instructions: [ReceiveBuffer.md](ReceiveBuffer.md)
- `SlidingWindow` instructions: [SlidingWindow.md](SlidingWindow.md)

Once you have finished filling in the code, do the following to build the application:
 1. `javac -d out -cp ../lib/jsocket_0.5.0-beta3.jar src/*.java`
 2. `cd out`
 3. run as client: `java NaiveTcpConnectionTest 0`
 4. run as server: `java NaiveTcpConnectionTest 1`
 
You will be graded on a finished application and screenshot as proof it runs. If code fails to compile and run properly, you will receive a 0.
  
|Items |Points|
|:-----:|:-----:|
|source code| /90|
|screenshot| /10|
 
Submit your code and a screenshot of the message exchange in a zip folder named your last name and first initial lab3.zip, i.e. {last}{fi}lab3.zip.