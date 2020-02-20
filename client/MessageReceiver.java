package client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;

public class MessageReceiver extends Thread{

    BufferedReader in;
    Lock promptLock;

    public MessageReceiver(BufferedReader in, Lock promptLock) {
        this.in = in;
        this.promptLock = promptLock;
	}

	@Override
    public void run(){

    }
}