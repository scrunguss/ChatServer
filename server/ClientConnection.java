package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection extends Thread {

    private Socket socket;
    public long UserID;
    private MessageMap messageMap;
    private PrintWriter sender;
    private BufferedReader reader;
    private boolean messageAdded = false;
    private boolean messageSent = false;

    public ClientConnection(Socket socket, MessageMap messageMap) {
        System.out.print("Client Connected, UID : ");
        this.socket = socket;
        this.messageMap = messageMap;
    }

    public long getUserID(){
        return UserID;
    }

    @Override
    public void run() {
        UserID = Thread.currentThread().getId();
        System.out.println(Long.toString(UserID));
        while(true){
            if(messageMap.isMessageAdded() == true && messageSent == false){
                sender.println(messageMap.getLast());
                messageAdded = false;
                messageSent = true;
            }
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                sender = new PrintWriter(socket.getOutputStream(), true);
                String message;
                message = reader.readLine();
                if(!message.equals("")){
                    System.out.println(message);
                    messageMap.add(UserID, message);
                    messageMap.setMessageAdded(true);
                    messageSent = false;
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