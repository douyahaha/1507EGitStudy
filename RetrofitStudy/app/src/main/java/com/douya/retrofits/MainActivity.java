package com.douya.retrofits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.douya.retrofits.api.API;
import com.douya.retrofits.bean.Info;
import com.douya.retrofits.bean.News;
import com.douya.retrofits.bean.User;
import com.douya.retrofits.inter.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getData();
//        getArgumentData();
        getArgumentData2();
    }

    private void getArgumentData2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.baseUrl3)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<Info> call = retrofitService.getArgumentData2(1, 0);
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info info = response.body();
                System.out.println("结果Info "+info.toString());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });
    }

    private void getArgumentData() {
        //得到Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.baseUrl2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //得到网络接口
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<User> call = retrofitService.getArgumentData("douya");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                System.out.println("结果user："+user.toString()+"\n");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void getData() {
        //得到Retrofit对象 建造者模式
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.baseUrl1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //得到网络接口 通过动态代理的方式获取
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<News> call = retrofitService.getData();
        //执行异步请求
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
                List<News.AdsBean> ads = news.getAds();
                for (News.AdsBean b:ads) {
                    System.out.println("结果news："+b.toString()+"\n");
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }



}
