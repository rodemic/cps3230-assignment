package main;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class MessagingSystem {

    private static List<String> as = new ArrayList<>();
    private static List<LoginToken> lts = new ArrayList<>();
    private static List<Mailbox> mbs = new ArrayList<>();
    private static List<SessionToken> sts = new ArrayList<>();

    private static TimeProvider provider = new RealTimeProvider();

    public static void setTimeProvider(TimeProvider p){
        provider = p;
    }

    public static void populateAgentList(){
        as.add("agent1");
        as.add("agent2");
        as.add("agent3");
        as.add("agent4");
    }

    public static boolean registerLoginKey(String loginkey, String agentId){
        if(loginkey.length() == 10){
            LoginToken lt = new LoginToken(loginkey,agentId,provider.getCurrTime());
            lts.add(lt);
            return true;
        }
        else return false;
    }

    public static String login(String loginkey, String agentId){
        String sessionKey = "";

        return sessionKey;
    }

    public static boolean sendMessage(String sessionkey, String sourceAgentId, String targetAgentId, String message){
        for (Mailbox mb:mbs) {
            if(mb.getOwnerId().equals(targetAgentId)){
                Message m = new Message(sourceAgentId,targetAgentId,currentTimeMillis(),message);
                mb.addMessage(m);
            }
        }

        return true;
    }



}
