package com.company;

public class Message {
    private String content;
    private long id;

    public Message(String content, long id) {
        this.content = content;
        this.id = id;
    }

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
