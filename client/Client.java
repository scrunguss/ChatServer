package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public abstract class Client {

    private Socket socket;
    protected String lastMessageReceived;
    protected long userID;

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

    public void setLastMessageReceived(String message){
        lastMessageReceived = message;
    }

    public void setUserID(long userID){
        this.userID = userID;
    }
}