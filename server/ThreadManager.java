package server;

import java.util.ArrayList;
import java.util.List;

/* public class ThreadManager extends Thread {

    List<ClientConnection> threadGroup = new ArrayList<ClientConnection>();
    private MessageMap messageMap;

    public ThreadManager(MessageMap messageMap){
        this.messageMap = messageMap;
    }

    public void add(ClientConnection thread){
        threadGroup.add(thread);
    }

    @Override
    public void run(){
        while(true){
            for(int i=0; i<threadGroup.size();i++){
                if(threadGroup.get(i).isMessageAdded() == true){
                    messageMap.setMessageAdded(true);
                }
            }
            boolean allSent = false;
            while(!allSent){
                for(int i=0; i<threadGroup.size();i++){
                    if(threadGroup.get(i).isMessageSent() == false){
                        allSent = false;
                    }
                    else{
                        allSent = true;
                    }
                }
            }
            messageMap.setMessageAdded(false);
        }
    }
} */