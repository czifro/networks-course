import jsocket.socket.Socket;
import jsocket.socket.SocketImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Will Czifro
 */
public class HttpServer {

    private int port = 8080;
    private ServerSocket server;
    private ConcurrentLinkedQueue<HttpContext> httpContexts;
    private final Object locker = new Object();

    public static void main(String[] args) {
        new HttpServer().run();
    }

    public HttpServer() {
        initialize();
        httpContexts = new ConcurrentLinkedQueue<>();
    }

    /**
     * Initialize server to listen on port 8080
     */
    public void initialize() {
        try {
            System.out.println("Initializing server: http://localhost:8080/");
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Server uses a thread-per-request approach. This is not efficient for a
     * production environment, but it is good enough for the sake of this lab
     */
    public void run() {
        Thread processor = new Thread(() -> {
            while (true) {
                HttpContext context = getNewConnection();

                new Thread(context::run).start();
            }
        });

        processor.start();
        while (true) {
            try {
                System.out.println("Waiting for connection...");
                Socket socket = new SocketImpl(server.accept());
                queueNewConnection(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Following methods are for non-blocking queueing of requests
     * Pulling is blocking in case no request is available.
     * Pushing will resume any blocking thread
     * @return
     */
    public HttpContext getNewConnection() {
        synchronized (locker) {
            if (httpContexts.isEmpty()) try {
                locker.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return httpContexts.poll();
        }
    }

    public void queueNewConnection(Socket socket) {
        synchronized (locker) {
            boolean callNotify = httpContexts.isEmpty();

            HttpContext context = new HttpContext(socket);

            httpContexts.add(context);

            if (callNotify) locker.notify();
        }
    }
}
