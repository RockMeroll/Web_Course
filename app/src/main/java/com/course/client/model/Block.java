package com.course.client.model;

/**
 * Created by RockMeRoLL on 2016/11/29.
 */
public class Block {

    String title;
    int cid;

    public Block(String title, int cid) {
        this.title = title;
        this.cid = cid;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
