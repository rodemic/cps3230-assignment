package main;

import java.util.ArrayList;
import java.util.List;

public class Mailbox {
    private String ownerId;
    private List<Message> ms;

    public Mailbox(){
        ms = new ArrayList<>();
    }

    Mailbox(String ownerId){
        this.ownerId = ownerId;
        ms = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return ms;
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

    public void addMessage(Message m) {
        ms.add(m);
    }

    public Boolean hasMessages() {
        return ms.size() > 0;
    }

    void setMessages(List<Message> messages) {
        this.ms = messages;
    }
}
