package server;

import java.util.ArrayList;
import java.util.List;

public class MessageMap{

    private List<Pair<Long,String>> messageMap = new ArrayList<Pair<Long,String>>();
    private boolean messageAdded = false;

    public synchronized void add(Long UID, String message){
        messageMap.add(new Pair<Long,String>(UID, message));
    }

    public String getLast(){
        return messageMap.get(messageMap.size()-1).value;
    }

    public String getLast(Long UID){
        for(int i=messageMap.size()-1;i>=0;i--){
            if(messageMap.get(i).key.equals(UID)){
                return messageMap.get(i).value;
            }
        }
        return null;
    }

    public synchronized boolean isMessageAdded(){
        return messageAdded;
    }

    public synchronized void setMessageAdded(boolean value){
        messageAdded = value;
    }
}