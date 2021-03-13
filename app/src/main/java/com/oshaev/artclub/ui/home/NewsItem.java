package com.oshaev.artclub.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.oshaev.artclub.Comment;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class NewsItem   {

    private String key;
    private URL imageUrl;
    private String imageUrlString;
    private ArrayList<String> imageUrlStrings;
    private String source; //
    private String title; //заголовок
    private String author;
    private String summary;
    boolean registrationEnabled;

    private int timePost;
    private int viewCount;
    private Date datePost;

    private ArrayList<Comment> comments;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String paper; // текст поста

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }
    //private String imageUrl;


    public ArrayList<String> getImageUrlStrings() {
        return imageUrlStrings;
    }

    public void setImageUrlStrings(ArrayList<String> imageUrlStrings) {
        this.imageUrlStrings = imageUrlStrings;
    }

    public String getImageUrlString() {
        return imageUrlString;
    }

    public void setImageUrlString(String imageUrlString) {
        this.imageUrlString = imageUrlString;
    }

    public NewsItem() {
        imageUrlStrings = new ArrayList<>();
    }

    public NewsItem(String title, String author, String paper) {
        this.title = title;
        this.author = author;
        this.paper = paper;
    }

    public NewsItem(String title, String summary, String paper, Date timePost) {
        this.title = title;
        this.summary = summary;
        this.paper = paper;
        this.datePost = timePost;
    }

    public boolean isRegistrationEnabled() {
        return registrationEnabled;
    }

    public void setRegistrationEnabled(boolean registrationEnabled) {
        this.registrationEnabled = registrationEnabled;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public int getTimePost() {
        return timePost;
    }

    public void setTimePost(int timePost) {
        this.timePost = timePost;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }


}
