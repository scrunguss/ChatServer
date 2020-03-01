package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

    public class ThreadManager{

    List<ClientConnection> threadGroup = new ArrayList<ClientConnection>();
    List<Socket> clientSockets = new ArrayList<Socket>();
    private MessageMap messageMap;
    private PrintWriter sender;

    public ThreadManager(MessageMap messageMap){
        this.messageMap = messageMap;
    }

    public void add(Socket clientSocket){
        ClientConnection thread = new ClientConnection(clientSocket,messageMap,this);
        threadGroup.add(thread);
        clientSockets.add(clientSocket);
        thread.start();
    }

    public synchronized void assign(long UserID, Socket socket){
        try {
            sender = new PrintWriter(socket.getOutputStream(), true);
            sender.println("You have been assigned User ID ["+UserID+"]");
        } catch (IOException e) {
            System.out.println("Socket error, Client "+UserID+" not reachable...");
            clientSockets.remove(socket);
        }
    }

    public synchronized void broadcast(String message,long UserID){
        boolean shutDown = false;
        if(message.contains("EXIT")){
            message = "EXIT signal received from User ["+UserID+"], server shutting down...";
            shutDown = true;
        }
        for(int i=0; i<clientSockets.size();i++){
            try{
                sender = new PrintWriter(clientSockets.get(i).getOutputStream(), true);
                sender.println("[User "+UserID+"] "+message);
                if(shutDown){
                    clientSockets.get(i).close();
                }
            } catch (IOException e){
                System.out.println("Socket error on broadcast, removing Client ["+threadGroup.get(i).getUserID()+"] from pool...");
                clientSockets.remove(i);
                i--;
            }
        }
        if(shutDown){
            System.exit(1);
        }
        
    }
}