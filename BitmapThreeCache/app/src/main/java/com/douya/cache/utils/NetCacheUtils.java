package com.douya.cache.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络缓存
 * Created by 杨圆圆 on 2017/11/8.
 */

public class NetCacheUtils {
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils mLocalCacheUtils, MemoryCacheUtils mMemoryCacheUtils) {
        this.mLocalCacheUtils = mLocalCacheUtils;
        this.mMemoryCacheUtils = mMemoryCacheUtils;
    }
    /**
     * 从网络下载图片
     */
    public void getBitmapFromNet(ImageView ivPic,String url){
        new BitmapTask().execute(ivPic,url);
    }
    /**
     * 异步类
     */
    class BitmapTask extends AsyncTask<Object,Void,Bitmap>{
        private ImageView ivPic;
        private String url;
        @Override
        protected Bitmap doInBackground(Object... objects) {
            ivPic= (ImageView) objects[0];
            url= (String) objects[1];

            ivPic.setTag(url);
            return downLoadBitmap(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null){
                String bindUrl = (String) ivPic.getTag();
                if(url.equals(bindUrl)){
                    //确保图片设定给了正确的imageView
                    ivPic.setImageBitmap(bitmap);
                    //将图片保存在本地
                    mLocalCacheUtils.setBitmapToLocal(url,bitmap);
                    //将图片缓存在内存
                    mMemoryCacheUtils.setBitMapToMemory(url,bitmap);
                }
            }
        }
    }
    /**
     * 下载图片
     */
    private Bitmap downLoadBitmap(String url){
        HttpURLConnection connection=null;
        try {
            URL path=new URL(url);
            connection= (HttpURLConnection) path.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if(code==200){
                InputStream inputStream = connection.getInputStream();
                //图片压缩处理
                BitmapFactory.Options options=new BitmapFactory.Options();
                //宽高都压缩为原来的二分之一
                options.inSampleSize=2;
                //设置图片格式
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
