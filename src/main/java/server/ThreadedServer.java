package server;

import GUI.ServerUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


/**
     * A TCP server that runs on port 9090.  When a client connects, it
     * sends the client the current date and time, then closes the
     * connection with that client.  Arguably just about the simplest
     * server you can write.
     */
    public class ThreadedServer {
    static ServerUI s;
    //HashMap<String, Socket> clientConnections = new HashMap<>();

    public ThreadedServer(ServerUI s) throws IOException {
            int port = 9090;
            ServerSocket listener = new ServerSocket(port);
            System.out.println("Server started on"+port);
            //accept
            while(true) {
                Socket socket = listener.accept();
                //clientConnections.put(socket.getInetAddress().getHostAddress(), socket);
                new ServerThread(socket).start();
                listener.close();
            }
        }

        /**
         * Runs the server.
         */
        public static void main(String[] args) throws IOException {
            new ThreadedServer(s);
        }
}
