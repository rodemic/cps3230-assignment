package com.agentsystem;

public class LoginToken {
    private String loginkey;
    private String agentId;
    private long timestamp;

    public LoginToken(String loginkey, String agentId, long timestamp){
        this.loginkey = loginkey;
        this.agentId = agentId;
        this.timestamp = timestamp;
    }

    String getLoginKey() {
        return loginkey;
    }

    long getTimeStamp() {
        return timestamp;
    }
}
