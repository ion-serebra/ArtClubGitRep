package com.oshaev.artclub.usertasks;

import java.util.ArrayList;
import java.util.Date;


public class UserTask {

    String key; // id задания
    int coinsReward; // награда за задание в АртКоинах
    int exp; // опыт за задание
    ArrayList<Performance> performances; // лист характеристик, которые улучшает задание
    int accessLevel; // уровень, с коротого доступно задание
    String name; // название задания
    String description; // описание задания
    String personalUID; // id пользователя для выдачи персонального задания
    int timeRecharge; // время перезарядки задания
    Date date;


    public UserTask(String name, ArrayList<Performance> performances,
                    Integer exp, Integer coinsReward, Integer accessLevel,  Integer timeRecharge) {
        this.coinsReward = coinsReward;
        this.exp = exp;
        this.performances = performances;
        this.accessLevel = accessLevel;
        this.name = name;
        this.timeRecharge = timeRecharge;
    }

    public UserTask(String name, ArrayList<Performance> performances,
                    Integer exp, Integer coinsReward, Integer accessLevel,  Integer timeRecharge, Date date) {
        this.coinsReward = coinsReward;
        this.exp = exp;
        this.performances = performances;
        this.accessLevel = accessLevel;
        this.name = name;
        this.timeRecharge = timeRecharge;
        this.date = date;
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }



    /*
    public UserTask(String name,HashMap<Performance, Integer> performanceIntegerHashMap, int reward, int difficulty, int priority, int fear) {
        this.name = name;
        this.reward = reward;
        this.difficulty = difficulty;
        this.priority = priority;
        this.fear = fear;
        this.performanceIntegerHashMap = performanceIntegerHashMap;
    }

     */

    public UserTask() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCoinsReward() {
        return coinsReward;
    }

    public void setCoinsReward(int coinsReward) {
        this.coinsReward = coinsReward;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getPersonalUID() {
        return personalUID;
    }

    public void setPersonalUID(String personalUID) {
        this.personalUID = personalUID;
    }

    public int getTimeRecharge() {
        return timeRecharge;
    }

    public void setTimeRecharge(int timeRecharge) {
        this.timeRecharge = timeRecharge;
    }
}


