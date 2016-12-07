# RSAKey Instructions

The following explain how to finish `RSAKey.java`:

`RSAKey::initEncryptCipher`:
Use `Cipher::getInstance(String)` to instantiate `encryptCipher` to an `RSA` based cipher. `encryptCipher` will need to be initialized to encrypt mode and the private key from `keyPair`. Look up the Java docs to do this.

`RSAKey::initDecryptCipher`:
This is practically the same as `RSAKey::initEncryptCipher` except that `decryptCipher` will need to be set to decrypt mode and will use `remotePublicKey`.

`RSAKey::getPublicAsPacket`:
Get the public key from `keyPair` as an `RSAPublicKeyImpl` and convert the modulus and exponent of that key into `byte` arrays and wrap them in an `RSAKeyPacket`.

`RSAKey::addPublicKeyFromPacket(RSAKeyPacket)`:
Take the modulus and exponent fields from the passed in packet wrap them with `RSAPublicKeyImpl`. Pass this new object to `RSAKey::addPublicKey`.

`RSAKey::getSharedKey`:
This has already been done for you. It should be noted that this is not the best practice. Best practice would be to do a Diffie-Hellman key exchange. For the sake of simplicity, a random string is generated and used as the shared key. 