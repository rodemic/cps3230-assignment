package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static LoginToken registerLoginKey(String loginkey, String agentId){
        LoginToken lt = null;
        if(loginkey.length() == 10){
            lt = new LoginToken(loginkey,agentId,provider.getCurrTime());
            lts.add(lt);
        }return lt;
    }

    public static SessionToken login(LoginToken lt, String agentId){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        String hash = Long.toString(currentTimeMillis())+lt.getLoginKey();
        md.update(hash.getBytes(),0,hash.length());
        String sessionKey = new BigInteger(md.digest()).toString().substring(0,50);

        SessionToken st = new SessionToken(sessionKey,provider.getCurrTime());
        sts.add(st);

        return st;
    }

    public static boolean sendMessage(SessionToken s, String sourceAgentId, String targetAgentId, String message){
        if(message.length() > 140) return false;
            if (sts.contains(s)) {
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
        return false;
    }
}
