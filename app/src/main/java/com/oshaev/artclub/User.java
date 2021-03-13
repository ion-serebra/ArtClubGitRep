package com.oshaev.artclub;

import com.oshaev.artclub.usertasks.Performance;
import com.oshaev.artclub.usertasks.UserTask;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private String name;
    private String surname;
    private String group;
    private String email;
    private String id;
    private String imgUrl;
    private String userKey;
    private String userAvatarPath;
    private int level;
    private int coins;

    public static final int LEVEL_0 = 0;
    public static final int LEVEL_1 = 10;
    public static final int LEVEL_2 = 20;
    public static final int LEVEL_3 = 30;
    public static final int LEVEL_4 = 40;
    public static final int LEVEL_5 = 50;
    public static final int LEVEL_6 = 60;
    public static final int LEVEL_7 = 70;
    public static final int LEVEL_8 = 80;
    public static final int LEVEL_9 = 90;
    public static final int LEVEL_10 = 100;
    public static final int LEVEL_11 = 120;
    public static final int LEVEL_12 = 140;
    public static final int LEVEL_13 = 160;
    public static final int LEVEL_14 = 180;
    public static final int LEVEL_15 = 200;
    public static final int LEVEL_16 = 220;
    public static final int LEVEL_17 = 240;
    public static final int LEVEL_18 = 260;
    public static final int LEVEL_19 = 280;
    public static final int LEVEL_20 = 300;

    private int exp;
    private int currentLevelExp;

    private int imageResource;

    private int accessLevel;

    ArrayList<Performance> performances;


    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }



  private  ArrayList<UserTask> completedTasks;


    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setCompletedTasks(ArrayList<UserTask> completedTasks1)
    {
        completedTasks = completedTasks1;
    }

   // public ArrayList<UserTask> getCompletedTasks() {
   //     return completedTasks;
   // }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    public User(String email, String id) {
        this.email = email;
        this.id = id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public User(String name, String surname, String group, String email, String id, String imgUrl, int imageResource, int accessLevel) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.email = email;
        this.id = id;
        this.imgUrl = imgUrl;
        this.imageResource = imageResource;
        this.accessLevel = accessLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public User()
    {

        performances = new ArrayList<>();


        performances.add(new Performance("Мудрость"));
        performances.add(new Performance("Здоровье"));
        performances.add(new Performance("Интеллект"));
        performances.add(new Performance("Сила воли"));
        performances.add(new Performance("Харизма"));
        performances.add(new Performance("Сила"));
        performances.add(new Performance("Память"));
        performances.add(new Performance("Личностный рост"));



    }

    public User(String name, String surname, String group, String email, String id, String imgUrl) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.email = email;
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public int getCurrentLevel()
    {
        if(exp>=LEVEL_0 && exp<LEVEL_1){return 0;}
        else if(exp>=LEVEL_1 && exp<LEVEL_2){return 1;}
        else if(exp>=LEVEL_2 && exp<LEVEL_3){return 2;}
        else if(exp>=LEVEL_3 && exp<LEVEL_4){return 3;}
        else if(exp>=LEVEL_4 && exp<LEVEL_5){return 4;}
        else if(exp>=LEVEL_5 && exp<LEVEL_6){return 5;}
        else if(exp>=LEVEL_6 && exp<LEVEL_7){return 6;}
        else if(exp>=LEVEL_7 && exp<LEVEL_8){return 7;}
        else if(exp>=LEVEL_8 && exp<LEVEL_9){return 8;}
        else if(exp>=LEVEL_9 && exp<LEVEL_10){return 9;}
        else if(exp>=LEVEL_10 && exp<LEVEL_11){return 10;}
        else if(exp>=LEVEL_11 && exp<LEVEL_12){return 11;}
        else if(exp>=LEVEL_12 && exp<LEVEL_13){return 12;}
        else if(exp>=LEVEL_13 && exp<LEVEL_14){return 13;}
        else if(exp>=LEVEL_14 && exp<LEVEL_15){return 14;}
        else if(exp>=LEVEL_15 && exp<LEVEL_16){return 15;}
        else if(exp>=LEVEL_16 && exp<LEVEL_17){return 16;}
        else if(exp>=LEVEL_17 && exp<LEVEL_18){return 17;}
        else if(exp>=LEVEL_18 && exp<LEVEL_19){return 18;}
        else if(exp>=LEVEL_19 && exp<LEVEL_20){return 19;}
        else if(exp>=LEVEL_20){return 20;}
        else return -1;

    }

    public String getCurrentLevelString()
    {
        if(exp>=LEVEL_0 && exp<LEVEL_1){return 0+"";}
        else if(exp>=LEVEL_1 && exp<LEVEL_2){return 1+"";}
        else if(exp>=LEVEL_2 && exp<LEVEL_3){return 2+"";}
        else if(exp>=LEVEL_3 && exp<LEVEL_4){return 3+"";}
        else if(exp>=LEVEL_4 && exp<LEVEL_5){return 4+"";}
        else if(exp>=LEVEL_5 && exp<LEVEL_6){return 5+"";}
        else if(exp>=LEVEL_6 && exp<LEVEL_7){return 6+"";}
        else if(exp>=LEVEL_7 && exp<LEVEL_8){return 7+"";}
        else if(exp>=LEVEL_8 && exp<LEVEL_9){return 8+"";}
        else if(exp>=LEVEL_9 && exp<LEVEL_10){return 9+"";}
        else if(exp>=LEVEL_10 && exp<LEVEL_11){return 10+"";}
        else if(exp>=LEVEL_11 && exp<LEVEL_12){return 11+"";}
        else if(exp>=LEVEL_12 && exp<LEVEL_13){return 12+"";}
        else if(exp>=LEVEL_13 && exp<LEVEL_14){return 13+"";}
        else if(exp>=LEVEL_14 && exp<LEVEL_15){return 14+"";}
        else if(exp>=LEVEL_15 && exp<LEVEL_16){return 15+"";}
        else if(exp>=LEVEL_16 && exp<LEVEL_17){return 16+"";}
        else if(exp>=LEVEL_17 && exp<LEVEL_18){return 17+"";}
        else if(exp>=LEVEL_18 && exp<LEVEL_19){return 18+"";}
        else if(exp>=LEVEL_19 && exp<LEVEL_20){return 19+"";}
        else if(exp>=LEVEL_20){return 20+"";}
        else return -1+"";

    }

    public int getCurrentLevelExp(int level)
    {
        if(level == 0) { return LEVEL_0;}
        else if(level == 1) { return LEVEL_1;}
        else if(level == 2) { return LEVEL_2;}
        else if(level == 3) { return LEVEL_3;}
        else if(level == 4) { return LEVEL_4;}
        else if(level == 5) { return LEVEL_5;}
        else if(level == 6) { return LEVEL_6;}
        else if(level == 7) { return LEVEL_7;}
        else if(level == 8) { return LEVEL_8;}
        else if(level == 9) { return LEVEL_9;}
        else if(level == 10) { return LEVEL_10;}
        else if(level == 11) { return LEVEL_11;}
        else if(level == 12) { return LEVEL_12;}
        else if(level == 13) { return LEVEL_13;}
        else if(level == 14) { return LEVEL_14;}
        else if(level == 15) { return LEVEL_15;}
        else if(level == 16) { return LEVEL_16;}
        else if(level == 17) { return LEVEL_17;}
        else if(level == 18) { return LEVEL_18;}
        else if(level == 19) { return LEVEL_19;}
        else if(level == 20) { return LEVEL_20;}
        else return 0;
    }


    public int getPreviousLevelExp(int level)
    {
        if(level == 0) { return LEVEL_0;}
        else if(level == 1) { return LEVEL_0;}
        else if(level == 2) { return LEVEL_1;}
        else if(level == 3) { return LEVEL_2;}
        else if(level == 4) { return LEVEL_3;}
        else if(level == 5) { return LEVEL_4;}
        else if(level == 6) { return LEVEL_5;}
        else if(level == 7) { return LEVEL_6;}
        else if(level == 8) { return LEVEL_7;}
        else if(level == 9) { return LEVEL_8;}
        else if(level == 10) { return LEVEL_9;}
        else if(level == 11) { return LEVEL_10;}
        else if(level == 12) { return LEVEL_11;}
        else if(level == 13) { return LEVEL_12;}
        else if(level == 14) { return LEVEL_13;}
        else if(level == 15) { return LEVEL_14;}
        else if(level == 16) { return LEVEL_15;}
        else if(level == 17) { return LEVEL_16;}
        else if(level == 18) { return LEVEL_17;}
        else if(level == 19) { return LEVEL_18;}
        else if(level == 20) { return LEVEL_19;}
        else return 0;
    }

    public Integer getThisLevelExp()
    {
        return exp-getCurrentLevelExp(getCurrentLevel());
    }

    public Integer getExpSegment()
    {
        return getCurrentLevelExp(level)-getPreviousLevelExp(level);
    }

    public String getUserAvatarPath() {
        return userAvatarPath;
    }

    public void setUserAvatarPath(String userAvatarPath) {
        this.userAvatarPath = userAvatarPath;
    }
}
