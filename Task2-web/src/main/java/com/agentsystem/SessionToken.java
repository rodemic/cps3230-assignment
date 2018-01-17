package main.java.com.agentsystem;

public class SessionToken {
    private String sessionKey;
    private long timestamp;
    private String agentID;
    private int messageLimit;

    public SessionToken(String sessionKey, long timestamp, String agentID){
        this.sessionKey = sessionKey;
        this.timestamp = timestamp;
        this.agentID = agentID;
        messageLimit = 0;
    }

    long getTimestamp() {
        return timestamp;
    }

    void incrMsgLim(){
        messageLimit++;
    }

    String getAgentID() {
        return agentID;
    }

    boolean checkMessageLimit(){
        return messageLimit < 25;
    }

    public String getSessionKey() {
        return sessionKey;
    }
}
