package com.course.client.model;

/**
 * Created by RockMeRoLL on 2016/11/28.
 */
public class Article {
    private int id;
    private int cid;
    private String time;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article() {
    }

    public Article(int id, int cid, String time, String title, String content) {
        this.id = id;
        this.cid = cid;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return title;
    }
}
