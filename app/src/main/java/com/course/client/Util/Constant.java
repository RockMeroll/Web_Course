package com.course.client.Util;

/**
 * Created by RockMeRoLL on 2016/11/29.
 */
public class Constant {

    public static final String Url = "http://192.168.0.197:8080/Api/Article/";
    public static String UrlForArticle(int cid,int id){
        return Url + id + "?cid=" + cid;
    }

    public static String UrlForCid(int cid){
        return Url + "?cid=" + cid;
    }

}
