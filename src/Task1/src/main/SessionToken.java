package main;

public class SessionToken {
    private String sessionKey;
    private long timestamp;

    public SessionToken(String sessionKey, long timestamp, String agentID){
        this.sessionKey = sessionKey;
        this.timestamp = timestamp;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getAgentID() {
        return agentID;
    }
}
