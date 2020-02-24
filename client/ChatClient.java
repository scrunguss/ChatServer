package client;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatClient {

    private static String DEFAULT_IP = "127.0.0.1";
    private static int DEFAULT_PORT = 14001;
    private static int MAX_ARGS = 4;

    public static void main(String[] args) {
        final Lock promptLock = new ReentrantLock();
        String IP = DEFAULT_IP;
        int port = DEFAULT_PORT;
        System.out.println(Arrays.toString(args));
        if (args.length != 0) {
            boolean valid = true;

            for (int i = 0; i < args.length; i++) {
                if(!valid){
                    System.out.print("Invalid Arguments, using defaults...");
                    IP = DEFAULT_IP;
                    port = DEFAULT_PORT;
                    break;
                }

                if (args[i].equals("-ccp") && args.length >= i + 1) {
                    valid = checkPort(args[i+1]);
                    if(valid){
                        port = Integer.parseInt(args[i+1]);
                    }
                    i++;                 
                }
                else if (args[i].equals("-cca") && args.length >= i + 1) {
                    valid = checkIP(args[i+1]);
                    if(valid){
                        IP = args[i+1];
                    }
                    i++;
                }
                else{             
                    valid = false;                    
                }
            }
        }

        Client client = new Client();
        client.connectToServer(IP,port);
        //new MessageReceiver(client.getSocket(),promptLock).start();;
        new MessageSender(client,promptLock).start();

    }

    public static boolean checkIP(String ipAddr){
        String[] parts = ipAddr.split("\\.");
        if(parts.length != 4){
            return false;
        }
        for(int i = 0; i<4; i++){
            int part = -1;
            try{
                part = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid IP");
                return false;
            }
            if (part <0 || part >255){
                return false;
            }
        }
        return true;
    }

    public static boolean checkPort(String port){
        int tmpPort = -1;
        try {
            tmpPort = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            System.out.println("Invalid port identifier");
            return false;
        }
        if (tmpPort >= 0 && tmpPort <= 65535) {
            return true;
        } else {
            return false;
        }
    }
}
