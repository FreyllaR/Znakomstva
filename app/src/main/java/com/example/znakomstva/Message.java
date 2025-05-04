package com.example.znakomstva;

public class Message {
    private String userName;
    private String messageText;
    private int userImageResId;

    public Message(String userName, String messageText, int userImageResId) {
        this.userName = userName;
        this.messageText = messageText;
        this.userImageResId = userImageResId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getUserImageResId() {
        return userImageResId;
    }
}