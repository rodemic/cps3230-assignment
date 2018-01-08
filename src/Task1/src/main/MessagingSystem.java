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

    public static boolean populateAgentList(){
        as.add("agent1");
        as.add("agent2");
        as.add("agent3");
        as.add("agent4");
        return true;
    }

    public static boolean addAgent(String agentId){
        for (String s:as) {
            if(s.equals(agentId)) return false;
        }
        as.add(agentId);
        return true;
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
        String sessionKey = "15";

        SessionToken st = new SessionToken(sessionKey,provider.getCurrTime());
        sts.add(st);

        return sessionKey;
    }

    public static boolean sendMessage(String sessionkey, String sourceAgentId, String targetAgentId, String message){
        if(message.length() > 140) return false;
        for (SessionToken s : sts) {
            if (s.getSessionKey().equals(sessionkey)) {
                long a = provider.getCurrTime();
                long b = s.getTimestamp();
                if(provider.getCurrTime() - s.getTimestamp() >= 600000) return false;
                for (String st : as) {
                    if (st.equals(targetAgentId)) {
                        for (Mailbox mb : mbs) {
                            if (mb.getOwnerId().equals(targetAgentId)) {
                                Message m = new Message(sourceAgentId, targetAgentId, provider.getCurrTime(), message);
                                mb.addMessage(m);
                                return true;
                            }
                        }
                        Mailbox nmb = new Mailbox(targetAgentId);
                        Message m = new Message(sourceAgentId, targetAgentId, provider.getCurrTime(), message);
                        nmb.addMessage(m);
                        mbs.add(nmb);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
