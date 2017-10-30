package com.douya.retrofits.inter;

import com.douya.retrofits.bean.Info;
import com.douya.retrofits.bean.News;
import com.douya.retrofits.bean.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 杨圆圆 on 2017/10/30.
 */

public interface RetrofitService {
    /**
     * 无参get请求
     * http://service.meiyinkeqiu.com/service/ads/cptj
     * @return
     */
    @GET("service/ads/cptj")
    Call<News> getData();

    /**
     * 有参get请求
     * 拼接参数 /形式
     *
     * @return https://api.github.com/users/baiiu
     */
    @GET("users/{user}")
    Call<User> getArgumentData(@Path("user")String user);
    /**
     * http://www.93.gov.cn/93app/data.do
     * channelId
     * startNum
     * 拼接 ? &
     * 为主
     */
    @GET("data.do")
    Call<Info> getArgumentData2(@Query("channelId")int channelId,@Query("startNum")int startNum);
}
