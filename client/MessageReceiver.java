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

	@Override
    public void run(){
        while(true){
            try{
                String message = in.readLine();
                if(message != null){                   
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