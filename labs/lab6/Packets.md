# *Packet Instructions

This contains instructions for finishing all `*Packet.java` files. This will only show you the structure of each packet.

Note that each packet has a type signified by a `char`. `ActionPacket` uses 'a', `RSAKeyPacket` uses 'r', `AESKeyPacket` uses 'R', `MessagePacket` uses 'm', and `EncryptedMessagePacket` uses 'e'. You will notice that `Packet::createPacket(byte[])` uses this to help wrap raw data with the appropriate class. This means that the first byte will already be parsed for you.

Finish `Packet::composeBytes` and `Packet::parseBytes` for each subclass of `Packet`.

```
ActionPacket => | type | action |
  type => size of 1 byte, value = 'a'
  action => size of 1 byte, value = 0 for true, 1 for false
  This is used to toggle encryption.
  
RSAKeyPacket => | type | modulusLen | modulus | exponentLen | exponent |
  type => size of 1 byte, value = 'r'
  modulusLen => size of 4 bytes, integer
  modulus => size of {modulusLen} bytes, byte[]
  exponentLen => size of 4 bytes, integer
  exponent => size of {exponentLen} bytes, byte[]
  
AESKeyPacket => | type | secretLen | secret |
  type => size of 1 byte, value = 'R'
  secretLen => size of 4 bytes, integer
  secret => size of {secretLen} bytes, byte[]
  
EncryptedMessagePacket => | type | messageLen | message | ivLen | iv |
  type => size of 1 byte, value = 'e'
  messageLen => size of 4 bytes, integer
  message => size of {messageLen} bytes, byte[]
  ivLen => size of 4 bytes, integer
  iv => size of {ivLen} bytes, byte[]
```

