package com.example.chattingpractice;

public class Message {
    private String text;
    private String name;
    private String sender;
    private String recipient;
    private String imgURL;

    public Message() {
    }

    public Message(String text, String name, String sender, String recipient, String imgURL) {
        this.text = text;
        this.name = name;
        this.sender = sender;
        this.recipient = recipient;
        this.imgURL = imgURL;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
