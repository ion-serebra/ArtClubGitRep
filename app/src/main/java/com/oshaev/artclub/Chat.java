package com.oshaev.artclub;

import java.util.ArrayList;

public class Chat {
    private String name;
    private String DBChild;
    private String id;
    private int accessLevel;
    private ArrayList<User> chatMembers = new ArrayList<>();

    public ArrayList<User> getChatMembers() {
        return chatMembers;
    }

    public void setChatMembers(ArrayList<User> chatMembers) {
        this.chatMembers = chatMembers;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Chat() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDBChild() {
        return DBChild;
    }

    public void setDBChild(String DBChild) {
        this.DBChild = DBChild;
    }

    public Chat(String name, String DBChild) {
        this.name = name;
        this.DBChild = DBChild;
    }
}
