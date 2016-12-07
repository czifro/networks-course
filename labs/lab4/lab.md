# Lab 4: Doing Simple File Transfers

This lab will go over implementing an FTP like protocol. This is to better understand protocols and how to design and implement them. 

The application will ask the server user to choose a file before waiting for a a connection. From the client's side, the application will ask for a directory path to save the file then ask for an IP address to connect to. The server will then transfer the file to the client. Once the file has been transferred, it will be located where specified. This lab will be using a stream based socket to simplify things, plus, a TCP connection is typically used in FTP applications.

Further details will be outlined in the following instructions:

- `SimpleFtp` instructions: [SimpleFtp.md](SimpleFtp.md)
- `FileMeta` instructions: [FileMeta.md](FileMeta.md)
- `FileChunkMeta` instructions: [FileChunkMeta.md](FileChunkMeta.md)

Once you have finished filling in the code, do the following to build the application:
 1. `javac -d out -cp ../lib/jsocket_0.5.0-beta3.jar src/*.java`
 2. `cd out`
 3. run as client: `java SimpleFtpTest 0`
 4. run as server: `java SimpleFtpTest 1`
 
You will be graded on a finished application, screenshot of console as proof it runs, and another screenshot of file contents as proof the file did not corrupt in transmission. If code fails to compile and run properly, you will receive a 0.
  
|Items |Points|
|:-----:|:-----:|
|source code| /80|
|console screenshot| /10|
|file screenshot| /10|
 
Submit your code and a screenshot of the message exchange in a zip folder named your last name and first initial lab4.zip, i.e. {last}{fi}lab4.zip.