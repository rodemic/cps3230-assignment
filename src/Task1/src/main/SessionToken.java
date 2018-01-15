package main;

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

    public String getSessionKey() {
        return sessionKey;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void incrMsgLim(){
        messageLimit++;
    }

    public String getAgentID() {
        return agentID;
    }

    public boolean checkMessageLimit(){
        return messageLimit < 25;
    }
}
