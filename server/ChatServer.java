package server;

import java.net.*;
import java.io.*;

public class ChatServer {
    private static int DEFAULT = 14001;

    public static void main(String[] args) {
        ServerSocket socket = null;
        Socket clientSocket = null;
        try {
            Integer port = DEFAULT;;
            if(args.length != 0){
                if(args[0].equals("-csp")){                   
                    try{
                         port = Integer.parseInt(args[1]);
                    } catch(Exception e){
                        System.out.println("Invalid port number, using default..");
                    }
                }
                else{
                    System.out.println("Invalid argument.");
                }
            }
            socket = new ServerSocket(port);
            System.out.println("Server socket " + port + " opened...");
        } catch (IOException e) {
            System.err.println("Port not accessible, exiting...");
            System.exit(1);
        }
        MessageMap messageMap = new MessageMap();
        //ThreadManager threadManager = new ThreadManager(messageMap);
        //threadManager.start();
        while (true) {
            try {
                clientSocket = socket.accept();
            } catch (IOException e) {
                System.out.println("Socket error occurred, exiting...");
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(1);
            }
            ClientConnection newClient = new ClientConnection(clientSocket,messageMap);
            newClient.start();
            //threadManager.add(newClient);
        }

    }
}