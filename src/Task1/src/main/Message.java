package main;

public class Message {
    private String sourceAgentId;
    private String targetAgentId;
    private long timestamp;
    private String message;

    public Message(String sourceAgentId, String targetAgentId, long timestamp, String message) {
        this.sourceAgentId = sourceAgentId;
        this.targetAgentId = targetAgentId;
        this.timestamp = timestamp;
        this.message = message;
    }
}
