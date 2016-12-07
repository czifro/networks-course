# SimpleDatagram Implementation

This class is importing the `Datagram` interface from the third party lib:

```java
import jsocket.datagram.Datagram;
```

This class has partially been implemented for you since setters and getters are very rudimentary. You will be focusing on implementing `SimpleDatagram::receivePacket`, `SimpleDatagram::receive`, and the three overloaded `SimpleDatagram::send` methods. These instructions go in the order of their appearance in code.

According to the `DataSocket` [documentation](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramSocket.html#receive-java.net.DatagramPacket-), `DataSocket::receive` takes `DatagramPacket` as an argument. Also note the exceptions it can throw. The only exception we are concerned with is `IOException` since it is not a runtime exception. In the `catch` block, wrap the exception with `jsocket.exceptions.DatagramConnectionException`. This is to simply make it a runtime exception so that any code that uses this class does not need to have a `try catch` block every time this method is called.

Look at the [docs](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramPacket.html#DatagramPacket-byte:A-int-) for creating a new packet to receive the incoming data. Use the set `bufferSize` to specify how many bytes to read in. Once a packet has been successfully read, update `lastPacket` and then return the packet.

Next is implementing `SimpleDatagram::receive`. This should be very straight forward, so no explanation will be provided. The first of the three `SimpleDatagram::send` methods takes a `DatagramPacket` as an argument. The `DatagramPacket` should be constructed using this [constructor](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramPacket.html#DatagramPacket-byte:A-int-java.net.InetAddress-int-). According to the [docs](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramSocket.html#send-java.net.DatagramPacket-), `DatagramSocket::send` takes a `DatagramPacket` as an argument and throws an `IOException`. Handle the exception as above. 

For the next `SimpleDatagram::send`, the parameters will need to be wrapped by a `DatagramPacket` before being send. Do a null check for the `InetAddress` and if null, throw a `DatagramConnectionException` with a reason for the exception. The third `SimpleDatagram::send` will require slightly more code. This method attempts to use the set `defaultAddress` or the address from `lastPacket` to send the data to. If both are null, throw a `DatagramConnectionException`. If the `defaultAddress` is the only thing null, use `lastPacket` to get the address and port. Cache the address and port before sending the data.

You should now have a fully functional `SimpleDatagram` class that can be plugged in anywhere.