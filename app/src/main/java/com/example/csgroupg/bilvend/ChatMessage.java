package com.example.csgroupg.bilvend;

import java.util.Date;

public class ChatMessage {

    private String messageText;
    private String messageSender;
    private long messageTime;
    private String sentTo;

    public ChatMessage(String messageText, String  messageSender, String sentTo) {
        this.messageText = messageText;
        this.messageSender = messageSender;
        this.sentTo = sentTo;
    }

    public ChatMessage() {
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public String getSentTo() {
        return sentTo;
    }
}

