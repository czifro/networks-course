# Instructions for implementing FileMeta

The following is the structure for `FileMeta` as it is being transmitted:

```
               FileMeta
  ---------------------------------------
 | filename length | filename | filesize |
  ---------------------------------------

 filename length -> 4 bytes
 filename -> <filename length> bytes
 filesize -> 8 bytes
```

Note that `FileMeta` does not have a field for the filename length. The first 4 bytes are just for reading in the rest of the `FileMeta`. `FileMeta::getBytes` should add filename length to the beginning of the byte array. Since the first 4 bytes are read in order to read the remainder of the bytes, `FileMeta::new` does not need parse the filename length. 