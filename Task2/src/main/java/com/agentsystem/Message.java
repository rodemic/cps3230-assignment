package com.agentsystem;

public class Message {
    private String sourceAgentId;
    private String targetAgentId;
    private long timeStamp;
    private String message;

    public Message(String sourceAgentId, String targetAgentId, long timeStamp, String message) {
        this.sourceAgentId = sourceAgentId;
        this.targetAgentId = targetAgentId;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    long getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }
}
