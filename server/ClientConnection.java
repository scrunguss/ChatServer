package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConnection extends Thread {

    private Socket socket;
    private long UserID;
    private MessageMap messageMap;
    private ThreadManager threadManager;
    private BufferedReader reader;

    public ClientConnection(Socket socket, MessageMap messageMap, ThreadManager threadManager) {
        System.out.print("Client Connected, UID : ");
        this.socket = socket;
        this.messageMap = messageMap;
        this.threadManager = threadManager;
    }

    public long getUserID(){
        return UserID;
    }

    @Override
    public void run() {
        UserID = Thread.currentThread().getId();
        System.out.println(Long.toString(UserID));
        threadManager.assign(UserID, socket);
        while(true){
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                message = reader.readLine();
                if(!message.equals("")){
                    System.out.println(message);
                    messageMap.add(UserID, message);
                    threadManager.broadcast(message,UserID);
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