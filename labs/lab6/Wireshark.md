# Wireshark Instructions

The wireshark portion of the lab is simply to capture the packets transmitted between client and server. The goal is to see what messages look like as the go over the way. It is also to illustrate how hackers use this to do "packet sniffing". Packet sniffing is how confidential info can be gather be simply tracking data that is being sent from a client to a server. The only way to mitigate this is to encrypt the connection. However, it is not a simple process. Both parties need to use the same key (symmetrical encryption), otherwise, they will never be able to see the intended message. 

Typically, asymmetrical encryption is used to establish symmetrical encryption. RSA is a common asymmetrical encryption. Asymmetrical requires two key from both parties (referred to as public/private key pair). The private key encrypts while the public key decrypts. Both parties exchange public keys. This allows one side to encrypt with the private key while the other side can decrypt with the shared public key (and the same in the other direction). Asymmetrical encryption can be costly performance wise. This is addressed by switching to symmetrical encryption once the asymmetrical encryption has been established.

An AES algorithm is a symmetrical encryption algorithm. There are others, but this lab uses AES. Using the RSA encryption, both parties use the Diffie-Hellman method to create a shared secret that will be used as the key for AES. To ensure that the same key is generated, both parties exchange hashes of the generated key and check that they match (note: the key is never exchanged and thus is never at risk). Both parties then switch to an AES based encryption. Symmetrical encryption is better in regards to performance. It requires less computations to encrypt and decrypt data compared to asymmetrical. 

For the packet capturing, capture plain text message exchanges, the key exchange, and encrypted message exchanges. Same the output to a file called wireshark.log. Only save the part of the packet that has the data from the application. The log should look as follows

```
<packet type>  // i.e. ActionPacket

<hex values>   // cluster as duples and do no more than 10 duples per row

<ascii values> // try and align the values with the correspondingg hex values

--------------
```

The adapter you will want to capture on is `en0`. Apply the filter "tcp.port == 50000" to capture only the packets sent between the client and server. If you are doing this with `localhost`, use adapter `lo0`. Localhost uses a virtual adapter to loopback traffic. This means that the traffic never actually uses the physical NIC.