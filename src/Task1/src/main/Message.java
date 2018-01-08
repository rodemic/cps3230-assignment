package main;

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

    public long getTimeStamp() {
        return timeStamp;
    }
}
