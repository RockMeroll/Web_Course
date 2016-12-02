package com.course.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.course.client.R;
import com.course.client.Util.Constant;
import com.course.client.Widget.GetRequest;
import com.course.client.Widget.WaitDialog;
import com.course.client.model.Article;
import com.course.client.model.Block;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by RockMeRoLL on 2016/11/28.
 */
public class ArticlesListActivity extends AppCompatActivity{

    ListView mListView;
    List<Article> mArticlesList;
    private final int SECOND_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String res = this.getIntent().getStringExtra("List");
        mArticlesList = JSON.parseArray(res,Article.class);
        InitView();
        setContentView(mListView);
    }

    private void InitView(){
        mListView = new ListView(this);
        mListView.setAdapter(new ArrayAdapter<Article>(this, android.R.layout.simple_expandable_list_item_1, mArticlesList));
        mListView.setOnItemClickListener(new mOnItemClickListener());
    }

    private class mOnItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int m_id = mArticlesList.get(position).getId();
            int cid = mArticlesList.get(position).getCid();
            GetRequest mRequest = new GetRequest();
            final WaitDialog mDialog = new WaitDialog(ArticlesListActivity.this);
            mDialog.show();
            try {
                mRequest.run(Constant.UrlForArticle(cid, m_id),
                        new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                ArticlesListActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ArticlesListActivity.this, "网络错误,请稍微再试", Toast.LENGTH_LONG).show();
                                        mDialog.dismiss();
                                    }
                                });
                            }

                            @Override
                            public void onResponse(Call call, final Response response) throws IOException {
                                //文章Json
                                final String Article_json = response.body().string();
                                ArticlesListActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDialog.dismiss();
                                        if (Article_json.length() <= 2) {
                                            Toast.makeText(ArticlesListActivity.this, "网络错误,请稍微再试", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                if(Article_json.length()>2)
                                    StartActivity(Article_json);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void StartActivity(String response){
        Intent intent = new Intent(ArticlesListActivity.this, ArticleActivity.class);
        intent.putExtra("Article", response);
        this.startActivityForResult(intent, SECOND_REQUEST_CODE);
    }
}
