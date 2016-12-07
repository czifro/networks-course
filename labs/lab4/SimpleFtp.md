# Instructions for implementing SimpleFtp

This file will only show/explain the protocol. You will be expected to implement the protocol with only the following illustration:

```
   Client                       Server
  | <----------------------- FileMeta |
  |                                   |
  |     below runs until finished     |
  |                                   |
  | <------------------ FileChunkMeta |
  | <-------------------------- Chunk |
  |                                   |
  |      file transfer finished       |
  |                                   |
  | Checksum -----------------------> |
  |                                   |
  | <------------ Validation Response |
  |                                   |
  |      restart if not valid         |
```

Look to [FileMeta.md](FileMeta.md) and [FileChunkMeta.md](FileChunkMeta.md) to understand how to receive and send them.

As you are reading in the file, you need to create a checksum of the entire file. [MessageDigest](https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html) can be used to create a checksum. Use MD5 as the algorithm. [DigestInputStream](https://docs.oracle.com/javase/7/docs/api/java/security/DigestInputStream.html) and [DigestOutputStream](https://docs.oracle.com/javase/7/docs/api/java/security/DigestOutputStream.html) can help create a digest on the file as bytes are being streamed in or out.

Before sending a checksum from the client, send the length of the checksum in 4 bytes (`jsocket.util.ByteUtil` can help), then send the checksum. With this, the server can, with full confidence, read in 4 bytes, then use that value to read in the remaining bytes. The static method `SimpleFtp.Helper::checksumsMatch` to compare checksums. The server will notify the client whether or not the checksums matched. If there is a mismatch, the file will need to be transmitted.