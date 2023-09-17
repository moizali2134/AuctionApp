package com.example.auctionapp.Chat;

public class Conversation {
    private String id;
    private String recipientId;
    private String lastMessage;

    // Required default constructor for Firebase
    public Conversation() {
    }

    public Conversation(String id, String recipientId, String lastMessage) {
        this.id = id;
        this.recipientId = recipientId;
        this.lastMessage = lastMessage;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
