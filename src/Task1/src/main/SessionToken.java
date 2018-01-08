package main;

public class SessionToken {
    String sessionKey;
    long timestamp;

    public SessionToken(String sessionKey, long timestamp){
        this.sessionKey = sessionKey;
        this.timestamp = timestamp;
    }
}
