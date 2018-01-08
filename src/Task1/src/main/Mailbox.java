package main;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class Mailbox {
    String ownerId;
    private List<Message> ms;

    public Mailbox(String ownerId){
        this.ownerId = ownerId;
        ms = new ArrayList<>();
    }

    public Message consumeNextMessage() {
        if(hasMessages()){
            Message m = ms.get(0);
            ms.remove(0);
            return m;
        }
        return null;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public boolean addMessage(Message m) {
        ms.add(m);
        return true;
    }

    public Boolean hasMessages() {
        for (Message m:ms) {
            if(m.getTimeStamp()-currentTimeMillis() < 1800000)
                return true;
            else{
                ms.remove(m);
            }
        }
        return false;
    }
}
