package com.douya.cache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.douya.cache.adapter.MyAdapter;
import com.douya.cache.bean.Bean;
import com.douya.cache.utils.MyBitMapUtils;
import com.douya.okhttplibrary.utils.GsonObjectCallback;
import com.douya.okhttplibrary.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MyBitMapUtils myBitMapUtils=new MyBitMapUtils();
        myBitMapUtils.disPlay(iv,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510149924424&di=1afdcac961a4594388369241acc2851d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F11385343fbf2b2110b707177c18065380cd78ea9.jpg");

        OkHttp3Utils.doGet("http://api.kkmh.com/v1/daily/comic_lists/1487606400?since=0&gender=0", new GsonObjectCallback<Bean>() {
            @Override
            public void onUi(Bean bean) {
                List<Bean.DataBean.ComicsBean> comics = bean.getData().getComics();
                MyAdapter adapter=new MyAdapter(MainActivity.this,comics);
                recycle.setAdapter(adapter);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recycle.setLayoutManager(manager);
    }
}
