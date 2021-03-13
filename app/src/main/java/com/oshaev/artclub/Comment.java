package com.oshaev.artclub;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class Comment {

    String commentContent;
    User sender;
    Date date;
    String key;

    String userName;
    String userSurname;
    String userId;
    String userImgUrl;

    public Comment() {
    }

    public Comment(String commentContent, User sender, Date date) {
        this.commentContent = commentContent;
        this.sender = sender;
        this.date = date;
    }



    public Comment(String commentContent, Date date, String userName, String userSurname, String userId, String userImgUrl) {
        this.commentContent = commentContent;
        this.date = date;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userId = userId;
        this.userImgUrl = userImgUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }


    public User getSender() {
        if(sender!=null) {
            return sender;
        }
        else
        {
            return null;
        }
    }



    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setCalendar(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }
}
