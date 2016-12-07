# SimpleDatagramTest Implementation

Most of this class has been implemented for you. You will need to implement Main::run method. Go to following code block:

```java
if (appType == AppType.SERVER) {
    // FIXME: instantiate simpleDatagram using server
}
else {
    System.out.println("Please enter an ip address: ");
    String ip = kbd.nextLine();
    DatagramSocket conn;
    // FIXME: instantiate simpleDatagram using conn
    
    // ENDFIXME
    System.out.println("Ready to send and receive...");
}
```

Please look at the the `DatagramSocket` documentation to see how to instantiate `conn`:

[DatagramSocket::new](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramSocket.html#DatagramSocket-int-java.net.InetAddress-)

Configure `simpleDatagram` with a buffer size for receiving data. Now go to the following block:

```java
System.out.println("Enter \"quit\" to end session");
String msg;
byte[] data;
while(true) {
    msg = "";
    if (appType == AppType.SERVER) {
        // FIXME: start receiving and sending data and update console output
    }
    else {
        // FIXME: reversal of if block
    }
}
```

In the `if` block, the server will first receive data into `data` and use the `Helper` class to convert the `byte[]` to a `String`. Use the `Helper` class to display the message. Before doing that, check that the message is not the terminating keyword 'quit'. If it is, print to the console letting the user know the client quit. Then set `msg` to the user input captured by the `Helper` class. Check for 'quit', if true, send message and break from loop, otherwise send message. The `else` block is the reverse of the `if` block.

After that, simply close `simpleDatagram` and you are finished.