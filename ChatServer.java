import java.net.*;
import java.io.*;

public class ChatServer {
    private static int DEFAULT = 14001;

    public static void main(String[] args) {
        ServerSocket socket = null;
        Socket clientSocket = null;
        try {
            socket = new ServerSocket(DEFAULT);
            System.out.println("Server socket " + DEFAULT + " opened...");
        } catch (IOException e) {
            System.err.println("Port not accessible, exiting...");
            System.exit(1);
        }

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
            new ClientConnection(clientSocket).start();
            

        }

    }
}