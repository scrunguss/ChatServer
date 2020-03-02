package client;

public class UserUtility {

    public static synchronized long parseUserID(String message){
        char[] msg = message.toCharArray();
        String id = "";
        for(int i=0;i<msg.length;i++){
            if (msg[i] == '['){
                i++;
                while(msg[i] != ']'){
                    if(Character.isDigit(msg[i])){
                        id += msg[i];
                        i++;
                    }
                    else{
                        i++;
                    }
                }
                try{
                    return Long.parseLong(id);
                } catch(NumberFormatException e){
                }
                
            }
        }
        return -1;
    }

    public static synchronized String cleanMessage(String message){
        message = message.toLowerCase().replace('?','\0');
        char[] msg = message.toCharArray();
        for(int i=0;i<msg.length;i++){
            if(msg[i] == '['){
                while(msg[i] != ']'){
                    msg[i] = ' ';
                    i++;
                }
                msg[i] = ' ';
                return new String(msg).trim();
            }
        }
        return "";
    }
}