package com.siyuan.enjoyreading.entity;

public class OrderMovie extends MultipleEntity {
    public String actors;
    public String filmName;
    public String grade;
    public String info;
    public String picaddr;
    public String shortinfo;

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPicaddr() {
        return picaddr;
    }

    public void setPicaddr(String picaddr) {
        this.picaddr = picaddr;
    }

    public String getShortinfo() {
        return shortinfo;
    }

    public void setShortinfo(String shortinfo) {
        this.shortinfo = shortinfo;
    }
}
