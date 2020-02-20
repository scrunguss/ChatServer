package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private BufferedReader in;
    private PrintWriter out;

    public void connectToServer(String IP, int port){
        
        Socket socket = null;
        try{ 
            socket = new Socket(IP, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException e){
            System.out.println("Host not found, exiting...");
            System.exit(1);
        } catch (IOException e){
            System.out.println("I/O Error occured, exiting...");
            System.exit(1);
        }
        

    }

    public BufferedReader getIn(){
        return in;
    }

    public PrintWriter getOut(){
        return out;
    }
}