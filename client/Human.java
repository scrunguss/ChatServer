package client;

import java.io.Console;

public class Human extends Client {

    @Override
    public String getInput(){
        Console console = System.console();
        String input = "";
        input = console.readLine();
        return input;
    }

    
}