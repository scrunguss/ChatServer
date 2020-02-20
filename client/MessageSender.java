package client;

import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;

public class MessageSender extends Thread{

    PrintWriter out;
    Lock promptLock;

    public MessageSender(PrintWriter out, Lock promptLock){
        this.out = out;
        this.promptLock = promptLock;
    }

    @Override
    public void run(){

    }
}