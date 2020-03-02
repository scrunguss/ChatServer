package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public abstract class Client {

    private Socket socket;
    protected long userID;
    protected BlockingQueue<String> messagesReceived = new ArrayBlockingQueue<String>(50);

    public void connectToServer(String IP, int port){    
        try{ 
            socket = new Socket();
            socket.connect(new InetSocketAddress(IP, port),1500);
            System.out.println("Connected to server at "+IP);
        } catch (UnknownHostException e){
            System.out.println("Host not found, exiting...");
            System.exit(1);
        } catch (SocketTimeoutException e){
            System.out.println("Connection timed out, exiting...");
            System.exit(1);
        } catch (IOException e){
            System.out.println("I/O Error occured, exiting...");
            System.exit(1);
        } 
    }

    public Socket getSocket(){
        return socket;
    }

    public abstract String getInput();

    public void addMessage(String message){
        try{
            messagesReceived.put(message);
        }catch(InterruptedException e){
            System.out.println("Interrupted!");
            System.exit(1);
        }
    }

    protected void setUserID(long userID){
        this.userID = userID;
    }
}