package main;

public class SessionToken {
    private String sessionKey;
    private long timestamp;

    public SessionToken(String sessionKey, long timestamp){
        this.sessionKey = sessionKey;
        this.timestamp = timestamp;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
