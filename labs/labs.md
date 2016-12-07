# Labs

These are the network labs for CSCD 330 Networks at EWU. The labs are programming that go over different concepts seen in Networking. The labs are split into segments:

1. Introduction to Socket Programming:
    This segment gives an introduction to programming with TCP and UDP.
    - [Lab 1](lab1/lab.md):
        This introduction uses stream based sockets to utilize a TCP connection. It is a very basic lab that does message exchanging.
    - [Lab 2](lab2/lab.md):
        This introduction uses datagram based sockets to utilize a UDP connection. It functions the same way as lab 1
2. Understanding Protocols:
    This segment goes over protocols and there importance in networking in the following programming labs
    - [Lab 3](lab3/lab.md):
        This lab implements a naive version of the TCP protocol over a UDP connection.
    - [Lab 4](lab4/lab.md):
        This lab implements a simple FTP protocol
    - [Lab 5](lab5/lab.md):
        This lab implements a simple HTTP server for a Web API application
3. Cryptography:
    This segment goes over cryptography in networking
    - [Lab 6](lab6/lab.md):
        This lab goes over encrypting a connection. AES will be the utilized algorithm
4. Client/Server Model:
    This segment looks at a client/server model and the complexities that come with that
    - Lab 7:
        This lab focuses on the client side
    - Lab 8:
        This lab focuses on the server counterpart to lab 7
5. Full Application:
    This segment will require the use of all previous concepts
    - Lab 9:
        This lab goes over creating a network based game.