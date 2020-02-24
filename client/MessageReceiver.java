package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.locks.Lock;

public class MessageReceiver extends Thread {

    BufferedReader in;
    Socket socket;
    Lock promptLock;

    public MessageReceiver(Socket socket, Lock promptLock) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("I/O Error occured, exiting...");
            e.printStackTrace();
        }
        this.socket = socket;
        this.promptLock = promptLock;
	}

	@Override
    public void run(){
        while(true){
            try{
                System.out.println(in.readLine());
            } catch (IOException e){
                System.out.println("I/O Error occured, exiting...");
                e.printStackTrace();
            }
        }
    }
}