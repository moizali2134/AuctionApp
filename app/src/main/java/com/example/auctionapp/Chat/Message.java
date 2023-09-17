package com.example.auctionapp.Chat;

public class Message {
    private String content;
    private String senderId;
    private long timestamp;

    // Required default constructor for Firebase
    public Message() {
    }

    public Message(String content, String senderId, long timestamp) {
        this.content = content;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

