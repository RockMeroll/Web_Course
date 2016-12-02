package com.course.client.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.course.client.R;
import com.course.client.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RockMeRoLL on 2016/11/28.
 */
public class ArticleActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("ArticleActivity", "OnCreate");
        super.onCreate(savedInstanceState);
        String article = getIntent().getStringExtra("Article");
        List<Article> tmp = JSON.parseArray(article, Article.class);
        Article mArticle = tmp.get(0);
        if(mArticle!=null){
            this.setTitle(mArticle.getTitle());
            InitView(mArticle.getContent());
        }
        else
            InitView("网络错误，请稍后重试");
        setContentView(mTextView);
    }

    private void InitView(String Content){
        mTextView = new TextView(this);
        mTextView.setTextSize(15);
        mTextView.setText(Content);

        mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
