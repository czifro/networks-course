# SimpleSocket Implementation

You will notice that this class imports from a third party library:

```java
import jsocket.socket.Socket;
```

This library provides many interfaces and implementations, but you will be using the base interface and implementing it with `SimpleSocket`. As you can see, there are fields provided already. However, to handle the input and output streams from the connection, you will need to specify the data types that will be used to do this. This lab uses `DataInputStream` and `DataOutputStream`. These types are machine-independent, which is beneficial for cross-platform cross-language use cases. If you would like to use other types to handle this, feel free to do so. Just be mindful this interface takes and returns byte arrays.

Add declarations of both `DataInputStream` and `DataOutputStream` like such:

```java
private java.net.Socket conn = null;
// Add the preferred classes for reading and writing bytes
private DataInputStream in;
private DataOutputStream out;
//
private int bufferSize = 0;
```

In the constructor, you will need to initialize the declared variables. First add a try catch block to handle the exception that may occur if retrieving the streams fails.

```java
try {

} catch(Exception e) {
////// fixme
}
```

Inside the try block set the `java.net.Socket` field to the `java.net.Socket` argument. The `java.net.Socket` class, provides methods for acquiring both input and output streams

```java
conn.getInputStream()
conn.getOutputStream()
```

Use these method calls to instantiate `in` and `out`. If it fails to acquire either of the streams, you will need to make a call to the close method to attempt to close any open stream and the open connection. You will also need to throw a new exception. With libraries, it is recommended to throw runtime exceptions. The reason is because exceptions that occur inside the library cannot be recovered from by user code. If accessing the streams fails, there is no way to recover from that. Throw a new instance of the following class:

`jsocket.exceptions.InstantiationException(Throwable throwable)`

This class is a runtime exception. Using runtime exceptions also simplify the user’s code. Keep this in mind should you develop a library.

The next method has already been done for you. The following two methods used for returning bytes from the input stream need to be implemented next. Both methods will share the same implementation except one will use the already set buffer size and the other will use the passed in size for reading in a certain number of bytes. The following method can be used to read in a byte array:

`in.read(/*empty byte[]*/, /*start position in byte[]*/, /*length to read in*/);`

This method will take an existing byte[] and populate it with data from the stream. This method also throws an exception. So you should surround this with try catch block and use the following exception to convert the exception to a runtime exception: 

`jsocket.exceptions.SocketStreamException(String string, Throwable throwable)`

Once you have a populated byte array, simply return it.

Next you will provide an implementation for writing a byte[] to the stream. The following method call will write bytes to the stream:

`out.write(/*byte[]*/, /*start position in byte[]*/, /*number of bytes to write*/);`

This method also throws an exception, so follow the same steps above.

The last method to implement will close the streams and connection. In a try catch finally block, you will attempt to close the two stream objects and connection. Remember that in the constructor, an exception may occur, before the streams are instantiated. You should do null checks, and if not null, make a call to close stream/connection (this covers the use case this method gets called twice). Do the same thing as above with the exception. In the finally block, set all three objects to null.

You should now have a usable socket wrapper.