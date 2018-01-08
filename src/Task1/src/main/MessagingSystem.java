package main;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class MessagingSystem {

    private List<LoginToken> lts;
    private List<Mailbox> mbs;
    private List<SessionToken> sts;

    public MessagingSystem(){
        lts = new ArrayList<>();
        mbs = new ArrayList<>();
        sts = new ArrayList<>();
    }

    public boolean registerLoginKey(String loginkey, String agentId){
        if(loginkey.length() == 10){
            LoginToken lt = new LoginToken(loginkey,agentId,currentTimeMillis());
            lts.add(lt);
            return true;
        }
        else return false;
    }

    public String login(String loginkey, String agentId){
        String sessionKey = "";

        return sessionKey;
    }

    public boolean sendMessage(String sessionkey, String sourceAgentId, String targetAgentId, String message){
        for (Mailbox mb:mbs) {
            if(mb.getOwnerId().equals(targetAgentId)){
                Message m = new Message(sourceAgentId,targetAgentId,currentTimeMillis(),message);
                mb.addMessage(m);
            }
        }

        return true;
    }



}
