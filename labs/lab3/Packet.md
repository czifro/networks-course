# Packet Implementation

Structure:

```
 -----------------------------------------
| packetId | packetType | data | checksum |
 -----------------------------------------

packetId -> 4 bytes
packetType -> 4 bytes
data -> arbitrary size
checksum -> 4 bytes
```

`Packet::new(byte[], int)` and `Packet::getBytes` need to be implemented to support the above structure. The `jsocket.util.ByteUtil` class can be helpful in converting `byte[] -> int` and vice versa. 

Any ACK will have a `packetId` of the original packet. Any received ACK should be validated with matching `packetId`. In addition, all sequence of packets must start with a START packet type and end with a END packet type. This is more for simplicity of code. The middle packets can be a PACKET type.