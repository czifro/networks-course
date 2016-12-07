# Instructions for implementing FileChunkMeta

The following is the structure of `FileChunkMeta` as it is being transmitted:

```
      FileChunkMeta
  -----------------------
 | chunkId | chunkLength |
  -----------------------
  
 chunkId -> 4 bytes
 chunkLength -> 4 bytes

```

This structure is guaranteed to be 8 bytes. This structure will precede the transmission of an actual file chunk. `FileChunkMeta::new` and `FileChunkMeta::getBytes` need to be implemented.