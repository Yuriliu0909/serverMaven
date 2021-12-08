package server;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread(Socket s) {
        this.socket = s;
    }

    public void run() {
        try {
            //Client request
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String req = bufferedReader.readLine();
            System.out.println("Client asks for: "+req);
            //response
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("A client request received at " + socket);
            //pass xml
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }


}