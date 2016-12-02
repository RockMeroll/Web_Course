package com.course.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.course.client.Util.Constant;
import com.course.client.Widget.GetRequest;
import com.course.client.Widget.WaitDialog;
import com.course.client.model.Block;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    private final int FIRST_REQUEST_CODE = 1;
    ListView mListView;
    List<Block> mBlocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBlocks();
        InitView();
        setContentView(mListView);
    }

    private void InitView(){
        mListView = new ListView(this);
        mListView.setAdapter(new ArrayAdapter<Block>(this, android.R.layout.simple_expandable_list_item_1, mBlocks));
        mListView.setOnItemClickListener(new mOnItemClickListener());
    }

    private void setBlocks(){
        mBlocks = new ArrayList<Block>();;
        mBlocks.add(new Block("区块链",11));
        mBlocks.add(new Block("大数据",10));
        mBlocks.add(new Block("云计算",22));
        mBlocks.add(new Block("软件开发",21));
        mBlocks.add(new Block("网站建设",19));
        mBlocks.add(new Block("APP开发",20));
    }

    private class mOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            GetRequest mRequest = new GetRequest();
            final WaitDialog mDialog = new WaitDialog(MainActivity.this);
            mDialog.show();
            try {
                mRequest.run(Constant.UrlForCid(mBlocks.get(position).getCid()),
                        new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"网络错误,请稍微再试", Toast.LENGTH_LONG).show();
                                mDialog.dismiss();
                            }
                        });
                    }


                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String res = response.body().string();
                        //文章列表

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDialog.dismiss();
                                if (res.length() <= 2) {
                                    Toast.makeText(MainActivity.this, "网络错误,请稍微再试", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        if(res.length()>2)
                            StartActivity(res);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void StartActivity(String response){
        Intent intent = new Intent(this, ArticlesListActivity.class);
        intent.putExtra("List", response);
        this.startActivityForResult(intent, FIRST_REQUEST_CODE);
    }
}
