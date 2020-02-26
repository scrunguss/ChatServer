package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;

public class MessageSender extends Thread {

    PrintWriter out;
    Client client;
    Lock promptLock;

    public MessageSender(Client client, Lock promptLock) {
        this.client = client;
        try {
            out = new PrintWriter(client.getSocket().getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("I/O Error occured, exiting...");
            e.printStackTrace();
        }
        this.promptLock = promptLock;
    }

    @Override
    public void run(){
        while(true){
                String input = "$ "+client.getInput();
                out.println(input);
        }
    }
}