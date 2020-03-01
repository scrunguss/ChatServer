package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.locks.Lock;

public class MessageReceiver extends Thread {

    BufferedReader in;
    Lock promptLock;
    Client client;

    public MessageReceiver(Client client) {
        try {
            in = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
        } catch (IOException e) {
            System.out.println("I/O Error occured, exiting...");
            e.printStackTrace();
        }
        this.client = client;
    }
    
    private long parseUserID(String message){
        char[] msg = message.toCharArray();
        String id = "";
        for(int i=0;i<msg.length;i++){
            if (msg[i] == '['){
                i++;
                while(msg[i] != ']'){
                    id += msg[i];
                    i++;
                }
                return Long.parseLong(id);
            }
        }
        return -1;
    }

	@Override
    public void run(){
        boolean firstServerMessage = true;
        while(true){
            try{
                String message = in.readLine();
                if(message != null){       
                    if(firstServerMessage){
                        firstServerMessage = false;
                        client.setUserID(parseUserID(message));
                    }      
                    System.out.println(message);
                    client.setLastMessageReceived(message);
                }
            } catch (Exception e){
                System.out.println("I/O Error occured or Server disconnected, exiting...");
                System.exit(1);
                e.printStackTrace();
            }
        }
    }
}