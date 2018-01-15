package main;

public class SessionToken {
    private String sessionKey;
    private String agentID;
    private long timestamp;

    public SessionToken(String sessionKey, long timestamp, String agentID){
        this.sessionKey = sessionKey;
        this.timestamp = timestamp;
        this.agentID = agentID;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
