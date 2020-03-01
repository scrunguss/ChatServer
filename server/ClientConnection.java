package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection extends Thread {

    private Socket socket;
    private long UserID;
    private MessageMap messageMap;
    private ThreadManager threadManager;
    private BufferedReader reader;
    private int CHAT_GROUP_CODE;
    private int DOD_GROUP_CODE;
    private int GENERAL_GROUP_CODE;

    public ClientConnection(Socket socket, MessageMap messageMap, ThreadManager threadManager,int CHAT_GROUP_CODE,int DOD_GROUP_CODE, int GENERAL_GROUP_CODE) {
        System.out.print("Client Connected, UID : ");
        this.socket = socket;
        this.messageMap = messageMap;
        this.threadManager = threadManager;
        this.CHAT_GROUP_CODE = CHAT_GROUP_CODE;
        this.DOD_GROUP_CODE = DOD_GROUP_CODE;
        this.GENERAL_GROUP_CODE = GENERAL_GROUP_CODE;
    }

    public long getUserID(){
        return UserID;
    }

    /* private void addToGroup(){
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
            sender.println("Select a room : ");
            sender.println("1. General Chat Room");
            sender.println("2. Chat Bot Room");
            sender.println("3. Dungeons of Doom Room");
            boolean valid = false;
            while(!valid){
                String choice = reader.readLine();
                try{
                    int code = Integer.parseInt(choice);
                    if(code == CHAT_GROUP_CODE){
                        sender.println("Welcome to Chat Bot Room!");
                        threadManager.addToGroup(this, code);
                        valid = true;
                    }
                    else if(code == DOD_GROUP_CODE){
                        sender.println("Welcome to Dungeons of Doom Game Room!");
                        threadManager.addToGroup(this, code);
                        valid = true;
                    }
                    else if(code == GENERAL_GROUP_CODE){
                        break;
                    }
                } catch(NumberFormatException e){
                }
                if(!valid){
                    sender.println("Invalid room choice, try again : "); 
                }
            }
        } catch (IOException e){
            System.out.println("Client Disconnected..");
            return;
        }
    } */



    @Override
    public void run() {
        UserID = Thread.currentThread().getId();
        System.out.println(Long.toString(UserID));
        threadManager.assign(UserID, socket);
        //addToGroup();
        while(true){
            try {
                String message;
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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