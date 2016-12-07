# ReceiveBuffer Implementation

This goes over implementing `ReceiveBuffer::isComplete`. `ReceiveBuffer::getBytes` is pretty straight forward, concatenate all the byte[] from each `Packet` into one array (since `NaiveTcpConnection` has a set buffer size, be sure to return an array of that buffer size).

`ReceiveBuffer::isComplete`:
Iterate over the the queue of packets and make sure that there is a START packet and an end packet and that there is not a missing packet id. Note: packets are sorted in ascending order by packet id. If any of these conditions are not met, the buffer is not complete.