package com.oshaev.artclub;


import java.util.Date;

public class ChatMessage {
    private String id;
    private String text;
    private String name;
    private String imgUrl;
    private String key;
    public Date date;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ChatMessage(String name, String text) {
        this.text = text;
        this.name = name;
    }

    public ChatMessage() {
    }
}
