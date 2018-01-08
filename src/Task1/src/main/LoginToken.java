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
}
