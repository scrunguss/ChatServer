package client;

import java.util.HashMap;

public class Bot extends Client {

    private String lastMessageRespondedTo;
    private HashMap<String, String> responses = new HashMap<String, String>();
    private boolean activated = false;
    boolean firstServerMessage = true;


    public Bot(){
        lastMessageRespondedTo = "";
        responses.put("who are you","I am a Bot");
        responses.put("what do you do","Not Much.");
        responses.put("what algorithm do you run","You don't want to know.");
        responses.put("if you could change anything about your algorithm, what would it be","Nothing, as long as I get the 10 marks.");
        responses.put("what, if anything, is too serious to joke about","The Holocaust.");
        responses.put("what is one detail you recall from your last chat","I have amnesia.");
        responses.put("what is string theory","In physics, string theory is a theoretical framework in which the point-like particles of particle physics are replaced by one-dimensional objects called strings. String theory aims to explain all types of observed elementary particles using quantum states of these strings. In addition to the particles postulated by the standard model of particle physics, string theory naturally incorporates gravity and so is a candidate for a theory of everything, a self-contained mathematical model that describes all fundamental forces and forms of matter. Besides this potential role, string theory is now widely used as a theoretical tool and has shed light on many aspects of quantum field theory and quantum gravity. The earliest version of string theory, bosonic string theory, incorporated only the class of particles known as bosons. It was then developed into superstring theory, which posits that a connection – a supersymmetry – exists between bosons and the class of particles called fermions. String theory requires the existence of extra spatial dimensions for its mathematical consistency. In realistic physical models constructed from string theory, these extra dimensions are typically compactified to extremely small scales."); //Source: https://www.reddit.com/r/ExplainLikeImPHD/comments/34yf6x/eliphd_string_theory/
    }

    @Override
    public String getInput() {
        while(true){
            if(firstServerMessage){
                firstServerMessage = false;
                try{
                    setUserID(UserUtility.parseUserID(messagesReceived.take()));
                } catch(InterruptedException e){
                    System.out.println("Interrupted!");
                    System.exit(1);
                }
            }
            String lastMessageReceived = "";
            try{
                lastMessageReceived = messagesReceived.take(); 
            }catch (InterruptedException e){
                System.out.println("Interrupted!");
                System.exit(1);
            }    
            if(lastMessageReceived.contains("CHAT")){
                activated = true;
                return "Hi there! You are now chatting to a bot.";
            }
            if(activated){
                if(lastMessageRespondedTo != lastMessageReceived){
                    if(userID != UserUtility.parseUserID(lastMessageReceived)){
                        lastMessageRespondedTo = lastMessageReceived;
                        if(lastMessageReceived.contains("HELP")){
                            String response = "";
                            for(String key : responses.keySet()){
                                response += key+"?, ";
                            }
                            return response;
                        }
                        if(lastMessageReceived.contains("QUIT")){
                            System.exit(1);
                        }
                        String message = UserUtility.cleanMessage(lastMessageReceived);
                        if(responses.containsKey(message)){
                            return responses.get(message);
                        }
                        else{
                            return "I don't know how to answer that. type HELP for a list of questions I can answer. type QUIT to kill me";
                        }
                    }
                }
            }
        }
    }

}