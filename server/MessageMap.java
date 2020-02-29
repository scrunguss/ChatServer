package server;

import java.util.ArrayList;
import java.util.List;

public class MessageMap{

    private List<Triple<Long,String,Long>> messageMap = new ArrayList<Triple<Long,String,Long>>();
    private boolean messageAdded = false;
    private long MsgID = 0;

    public synchronized void add(Long UID, String message){
        messageMap.add(new Triple<Long,String,Long>(UID, message,MsgID));
        MsgID++;
    }

    public String getLast(){
        return messageMap.get(messageMap.size()-1).value;
    }
    
    public long getLastID(){
        return messageMap.get(messageMap.size()-1).id;
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