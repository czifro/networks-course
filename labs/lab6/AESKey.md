# AESKey Instructions

The following explain how to finish `AESKey.java`:

`AESKey::new(byte[])`:
The argument will need to be wrapped by a `SecretKeySpec` that is based on the AES algorithm. The `encryptCipher` will need to be based on "AES/CBC/PKCS5Padding". This will allow for an initialization vector (IV) to be used with the encryption process. Once the `encryptCipher` has been initialized, retrieve the IV from the `AlgorithmParameters`. The IV will be wrapped by `IvParameterSpec`. This class contains the IV as a `byte[]`. Use this IV to initialize the `decryptCipher` (which will also need to be based on "AES/CBC/PKCS5Padding"). The purpose of the initialization vector is to act similarly to a salt in a hashing algorithm. The IV needs to be updated before each encryption. It is safe to transmit the IV in plain text across the wire. 

`AESKey::updateEncryptIV(long)` and `AESKey::updateDecryptIV(long)`:
This has been done for you, however, the method used is not best practice. Best practice is to use the previous IV and modify it in a way that treats it like a counter. So with each usage, the counter gets incremented. However, for the sake of simplicity, a random 16 byte string is generated each time. It should be noted that an IV has a limit. This means that a key is good for as many times the IV can be incremented. At which point, a new key should be generated. This lab is not concerned with that. 