# NaiveTcpConnection Implementation

This will go over implementing `NaiveTcpConnection::waitForConnection`, `NaiveTcpConnection::connect`, `NaiveTcpConnection::receive`, `NaiveTcpConnection::send`, and the partial implementation of `NaiveTcpConnection.Helper::beginPersistentReceive`. 

`NaiveTcpConnection::waitForConnection`: 
This should wait for a `java.net.DatagramPacket` to come in. Once received try and convert the data into a `Packet`. If the packet is a CONNECT packet, send back an ACK using `Packet::createAckPacket`. If the conversion fails or the packet is not a CONNECT, loop back to waiting for a `java.net.DatagramPacket`. Cache the address and port from the received `DatagramPacket` using `DatagramImpl::setDefaultAddressAndPort`.

`NaiveTcpConnection::connect`: 
This should try and connect to a node. Send out a CONNECT packet created by `Packet::createConnectPacket`. Before attempting a receive, set a timeout so that if an ACK is't received after a certain time, a CONNECT packet should be resent. If the packet received does not come from the address that it sent a CONNECT to (`Helper::addressesAreEqual`), discard the packet and resend CONNECT. Reset the timeout with 0.

`NaiveTcpConnection::receive`:
A `ReceiveBuffer` can be used to track the incoming packets using the `ReceiveBuffer::isComplete` method. Look at [ReceiveBuffer.md](ReceiveBuffer.md) for more details. Receive a `DatagramPacket` and ensure that it came from the connected sender. After parsing the data, generate a checksum from the data and make sure it matches the packet's checksum, otherwise discard the packet and loop back. Use `Helper::send` to send back an ACK.

`NaiveTcpConnection::send`:
A `SlidingWindow` will make it easier to send multiple packets and keep packets in order. More info on [SlidingWindow](SlidingWindow.md). Use `SlidingWindow` as an iterator. Use the `Consumer<Packet[]>` to send a window. The `Consumer<Packet[]>` should use `Helper::doRetransmission` to determine if a packet needs to be resent. Be sure to update the packet's transmission stamp. After sending a window, try to pull an ACK from `Helper.acks`. If an ack came back, notify the `SlidingWindow` and grab the next window and send. Loop until no ACKs get pulled, then loop back to get next window.

`NaiveTcpConnection.Helper::beginPersistentReceive`:
The `Runnable receiver` needs to be finished. After receiving a packet and address checking, try and parse the data and confirm it is an ACK. Then add it to `Helper.acks`. You will notice there is a set timeout. This is to prevent the thread from getting permanently blocked. This will allow `Helper::endPersistentReceive` to stop listening for ACKs.