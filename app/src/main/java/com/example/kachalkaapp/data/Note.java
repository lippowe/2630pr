package com.example.kachalkaapp.data;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String date;
    private String action;
    private String countAction;
    private String time;

    public Note(String date, String action, String countAction, String time) {
        this.date = date;
        this.action = action;
        this.countAction = countAction;
        this.time = time;
    }

    public Note(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCountAction() {
        return countAction;
    }

    public void setCountAction(String countAction) {
        this.countAction = countAction;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
