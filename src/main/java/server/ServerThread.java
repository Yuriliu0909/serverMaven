package server;

import javafx.scene.control.TextArea;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread implements Runnable {
    private ArrayList<ServerThread> threadList = new ArrayList<>();
    private Socket socket;
    private PrintWriter output;
    private String clientUsername;
    private TextArea textArea;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private PrintStream writer;


    public ServerThread(Socket socket, TextArea textArea) throws IOException {
        this.socket = socket;
        this.textArea = textArea;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //this.clientUsername = this.bufferedReader.readLine();
        writer = new PrintStream(this.socket.getOutputStream(), true);
        threadList.add(this);
    }

    public void run() {
        try {
            while(socket.isConnected()) {
                //Read client request
                String messageFromClient = this.bufferedReader.readLine();
                //Implement the protocol
                if (messageFromClient != null) {
                    textArea.appendText("\nThe client says : " + messageFromClient + "\n");
                    String[] req = messageFromClient.split(":");
                    String command = req[0];
                    System.out.println(messageFromClient);

                    switch (command) {
                        case "LOGIN":
                            //loginOperation(req);
                            String username = req[1];
                            String password = req[2];
                            ThreadedServer.addSocket(username, socket);
                            writer.println("create new user: " + username);
                            break;
                        case "SEND":
                            String sender = req[1];
                            String receiver = req[2];
                            String message = req[3];
                            Socket receiverSoc = ThreadedServer.getSocket(receiver);
                            sendMessage(receiverSoc, sender + " : " + message);
                            break;
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }


    private void sendMessage(Socket socket, String message) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(message);
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {

        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (socket != null) {
                socket.close();
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }
}




