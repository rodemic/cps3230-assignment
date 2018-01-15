package main;

public class SessionToken {
    private String sessionKey;
    private long timestamp;
    private String agentID;

    public SessionToken(String sessionKey, long timestamp, String agentID){
        this.sessionKey = sessionKey;
        this.timestamp = timestamp;
        this.agentID = agentID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getAgentID() {
        return agentID;
    }
}
