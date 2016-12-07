# SimpleSocketTest Implementation

Most of this class has been implemented for you. You will need to implement Main::run method. Go to following code block:

```java
if (appType == AppType.SERVER) {
    // FIXME: accept java.net.Socket connection and instantiate simpleSock

    // ENDFIXME
    System.out.println("Received connection...");
}
else {
    System.out.print("Please enter an ip address: ");
    String ip = kbd.nextLine();
    // FIXME: instantiate the java.net.Socket connection and simpleSock

    // ENDFIXME
    System.out.println("Established connection");
}
```

Please look at at the following links to see how to do this:

`ServerSocket`: https://docs.oracle.com/javase/7/docs/api/java/net/ServerSocket.html
`Socket`: https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html 

Next you will configure the socket with a default buffer size. Recommended size is 512. Feel free to increase for long messages to be sent and received. Now go to the following code block:

```java
System.out.println("Enter \"quit\" to end session");
String msg;
while(true) {
    msg = "";
    if (appType == AppType.SERVER) {
        // FIXME: start sending and receiving data and update console output
    }
    else {
        // FIXME: reversal of if block
    }
}
```

For the body of the if statement, this will be how the server will function. In the else block, you will do the same as the if block just in reverse order since this is the client functionality. In the if block, use the helper class to get input from the keyboard and assign it to `msg`. Convert `msg` to a `byte[]` and use `simpleSock` to send the bytes. Check if `msg` equals the keyword for ending the session and break out of the loop if it is. Otherwise, receive the `byte[]` coming from the client. Use the helper class to convert it to a UTF-8 string. UTF-8 is used because that is a character encoding that all platforms and languages support. You will notice that the string is processed by a function. This is in case the string contains null characters. The function removes null characters from the string. Next you will need to check if `msg` equals the keyword for ending the session and if it is let the user know and break out of the loop. Otherwise, notify user of what client sent using the helper class. This is the end of the if block. For the else block, you will do the receive steps first, then do the steps for sending. After the while loop, close `simpleSock` as well as `server`.