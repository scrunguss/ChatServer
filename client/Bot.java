package client;

import java.util.HashMap;

public class Bot extends Client {

    private String lastMessageRespondedTo;
    private HashMap<String, String> responses = new HashMap<String, String>();


    public Bot(){
        lastMessageRespondedTo = "";

    }

    @Override
    public String getInput() {
        return null;
    }

}