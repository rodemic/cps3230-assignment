package main;

import java.util.ArrayList;
import java.util.List;

public class Mailbox {
    String ownerId;
    private List<Message> ms;

    public Mailbox(){
        ms = new ArrayList<>();
    }

    public Message consumeNextMessage() {
        return null;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public boolean addMessage(Message m) {
        ms.add(m);
        return true;
    }
}
