package com.oshaev.artclub.usertasks;

public class Performance {
    String name;
    int experience;
    int level;
    int imageResource;

    public Performance() {
    }

    public Performance(String name) {
        this.name = name;
    }

    public Performance(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    public Performance(String name, int experience, int level) {
        this.name = name;
        this.experience = experience;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
