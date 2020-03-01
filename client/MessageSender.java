package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;

public class MessageSender extends Thread {

    PrintWriter out;
    Client client;
    Lock promptLock;

    public MessageSender(Client client) {
        this.client = client;
        try {
            out = new PrintWriter(client.getSocket().getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("I/O Error occured, exiting...");
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(true){
                String input = client.getInput();
                out.println(input);
                try{
                    Thread.sleep(50);
                } catch(InterruptedException e){
                    if(isInterrupted()){
                        e.printStackTrace();
                    }
                }
        }
    }
}