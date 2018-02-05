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

    public static void clearLists(){
        as = new ArrayList<>();
        lts = new ArrayList<>();
        mbs = new ArrayList<>();
        sts = new ArrayList<>();
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
        cleanUp();
        SessionToken st = null;
        if(lts.contains(lt)) {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
            String hash = Long.toString(currentTimeMillis()) + lt.getLoginKey();
            md.update(hash.getBytes(), 0, hash.length());
            String sessionKey = new BigInteger(md.digest()).toString().substring(0, 50);

            st = new SessionToken(sessionKey, provider.getCurrTime(), agentId);
            sts.add(st);
            addAgent(agentId);
        }
        return st;
    }

    public static boolean sendMessage(SessionToken s, String targetAgentId, String message){
        cleanUp();
        if(message.length() > 140) return false;
        if (sts.contains(s)) {
            for (String st : as) {
                if (st.equals(targetAgentId)) {
                    Mailbox mb = null;
                    for (Mailbox currMB : mbs) {
                        if (currMB.getOwnerId().equals(targetAgentId)) {
                            mb = currMB;
                        }
                    }
                    if(mb == null){
                        mb = new Mailbox(targetAgentId);
                        mbs.add(mb);
                    }
                    message = message.replaceAll("recipe","");
                    message = message.replaceAll("nuclear","");
                    message = message.replaceAll("ginger","");
                    Message m = new Message(s.getAgentID(), targetAgentId, provider.getCurrTime(), message);
                    mb.addMessage(m);
                    for (SessionToken sToken : sts) {
                        if (sToken.getAgentID().equals(targetAgentId) || sToken == s) {
                            sToken.incrMsgLim();
                        }
                    }
                    return true;
                }
            }return false;
        }return false;
    }

    public static Mailbox sendMailBox(SessionToken s){
        cleanUp();
        if(sts.contains(s)){
            for(Mailbox mb : mbs){
                if(mb.getOwnerId().equals(s.getAgentID())){
                    List<Message> ms = mb.getMessages();
                    return mb;
                }
            }
            return null;
        }
        return null;
    }

    private static void cleanUp(){
        for (int i = 0; i < sts.size(); i++) {
            SessionToken sToken = sts.get(i);
            if (!sToken.checkMessageLimit() || checkTimeUp(sToken.getTimestamp(),2)) {
                sts.remove(sToken);
                i--;
            }
        }
        for(int i = 0;i < lts.size(); i++){
            LoginToken lToken = lts.get(i);
            if(checkTimeUp(lToken.getTimeStamp(),1)){
                lts.remove(lToken);
                i--;
            }
        }
        for(int i = 0;i < mbs.size(); i++){
            Mailbox mb = mbs.get(i);
            List<Message> ms = mb.getMessages();

            for (int j = 0; i < ms.size(); i++) {
                Message m = ms.get(i);
                if (checkTimeUp(ms.get(i).getTimeStamp(), 3)) {
                    ms.remove(m);
                }
            }
            if(ms.isEmpty()) {
                mbs.remove(mb);
                i--;
            }else
                mb.setMessages(ms);
        }
    }

    private static boolean checkTimeUp(long timeStamp, int scenario){
        switch(scenario){
            case 1: //For login timestamp
                if(provider.getCurrTime() - timeStamp >= 60000) return true;
            case 2: //For session timestamp
                if(provider.getCurrTime() - timeStamp >= 600000) return true;
            case 3: //For com.agentsystem.Mailbox timestamp
                if(provider.getCurrTime() - timeStamp >= 1800000) return true;
            default: return false;
        }
    }
}
