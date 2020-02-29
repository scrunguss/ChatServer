package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConnection extends Thread {

    private Socket socket;
    public long UserID = Thread.currentThread().getId();

    public ClientConnection(Socket socket) {
        System.out.println("Client Connected, UID : "+Long.toString(UserID));
        this.socket = socket;
    }

    @Override
    public void run() {
        while(true){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                message = reader.readLine();
                if(!message.equals("")){
                    System.out.println(message);
                }             
            } catch (IOException | NullPointerException e) {
                try{
                    socket.close();
                    System.out.println("Client Disconnected..");
                    return;
                } catch (IOException f){
                    System.out.println("Socket did not cleanly close..");
                    return;
                }
            }
        }
    }
}