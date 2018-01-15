package main;

public class LoginToken {
    String loginkey;
    String agentId;
    long timestamp;

    public LoginToken(String loginkey, String agentId, long timestamp){
        this.loginkey = loginkey;
        this.agentId = agentId;
        this.timestamp = timestamp;
    }

    public String getLoginKey() {
        return loginkey;
    }

    public long getTimeStamp() {
        return timestamp;
    }
}
