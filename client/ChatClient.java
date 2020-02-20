package client;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatClient {

    public static void main(String[] args) {
        final Lock promptLock = new ReentrantLock();
        String DEFAULT_IP = "127.0.0.1";
        int DEFAULT_PORT = 14001;
        Client client = new Client();
        client.connectToServer(DEFAULT_IP,DEFAULT_PORT);
        new MessageReceiver(client.getIn(),promptLock).start();;
        new MessageSender(client.getOut(),promptLock).start();

    }
}
