package client;

import java.util.Arrays;

public class ChatClient {

    private static String DEFAULT_IP = "127.0.0.1";
    private static int DEFAULT_PORT = 14001;

    public static void main(String[] args) {
        String IP = DEFAULT_IP;
        int port = DEFAULT_PORT;
        System.out.println(Arrays.toString(args));
        if (args.length != 0) {
            boolean valid = true;

            for (int i = 0; i < args.length; i++) {
                if(!valid){
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
            if(!valid){
                System.out.println("Invalid Arguments, using defaults...");
                IP = DEFAULT_IP;
                port = DEFAULT_PORT;
            }
        }
        

        Client client = new Client();
        client.connectToServer(IP,port);
        new MessageReceiver(client.getSocket()).start();;
        new MessageSender(client).start();

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
