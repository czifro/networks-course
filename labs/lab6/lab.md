# Lab 6: Inside Secure Connections

As a disclaimer before starting this lab, encrypting public channels is not something you should do on your own. Using anything custom invites security risks. Always consider using a community developed/managed method. OpenSSL is widely used. [This codereview answer](http://codereview.stackexchange.com/a/1327), [and this codereview answe](http://stackoverflow.com/a/5310811/1998487), gives indication on the difficulty of ensuring a connection is secure. This lab will only act as an introduction to secure communication and what that looks like over the wire.

This lab will require using wireshark, so download that for your platform. This lab will use both RSA and AES to encrypt a connection. RSA will be used to encrypt the connection initially so that an AES key can be exchanged. Once the exchange is complete, RSA will be switched out for AES. The reason for this is to increase performance. An asymmetrical encryption (RSA) will take longer than a symmetrical encryption (AES). 

The application will function very much like the first two labs. However, there is a protocol to key exchanging so it draws on labs 3-5. The following are instructions for the lab:

- `RSAKey` instructions: [RSAKey.md](RSAKey.md)
- `AESKey` instructions: [AESKey.md](AESKey.md)
- `*Packet` instructions: [Packets.md](Packets.md)

Once you have finished filling in the code, do the following to build the application:
 1. `javac -d out -cp ../lib/jsocket_0.5.0-beta3.jar src/*.java`
 2. `cd out`
 3. run as client: `java SessionTest 0`
 4. run as server: `java SessionTest 1`

Start up wireshark and do the following instructions:

- `Wireshark` instructions: [Wireshark.md](Wireshark.md)

Your rubric is as follows:

|Items |Points|
|:-----:|:-----:|
|source code| /60|
|wireshark.log| /40|

Submit your code and log in a zip folder named your last name and first initial lab6.zip, i.e. {last}{fi}lab6.zip.