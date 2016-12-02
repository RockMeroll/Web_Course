package com.course.client.Widget;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.util.Log;
import android.widget.Toast;

import com.course.client.Util.Constant;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RockMeRoLL on 2016/11/29.
 */
public class GetRequest {

    public GetRequest() {
        this.client = new OkHttpClient();
    }

    OkHttpClient client;

    public void run(String url, Callback callback) throws IOException {
        //创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
        //new call
        Call call = client.newCall(request);
        //请求加入调度
        call.enqueue(callback);

    }

}
