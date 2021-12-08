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
    public int port = 8080;
    public ServerSocket serverSocket = new ServerSocket(port);
    //HashMap<String, Socket> clientConnections = new HashMap<>();

    public ThreadedServer(ServerUI s) throws IOException {
            System.out.println("Server started on"+port);
            //accept
        try{
            //server will be running infinitely until closed
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                //clientConnections.put(socket.getInetAddress().getHostAddress(), socket);
//                new ServerThread(socket).start();
//                serverSocket.close();
                System.out.println("A new client has connected");
                Client.ClientHandler clientHandler = new Client.ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException e){
            closeServerSocket();
        }
        }

    public void closeServerSocket(){
        try{
            if(serverSocket != null) {
                serverSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

        /**
         * Runs the server.
         */
        public static void main(String[] args) throws IOException {
            new ThreadedServer(s);
        }

}

