package client;

import java.io.Console;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Client {

    private Socket socket;

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

    public String getInput(){
        Console console = System.console();
        String input = "";
        input = console.readLine();
        return input;
    }

    public Socket getSocket(){
        return socket;
    }
}