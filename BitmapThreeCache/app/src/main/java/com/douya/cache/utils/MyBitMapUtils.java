package com.douya.cache.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.douya.cache.R;

/**
 * Created by 杨圆圆 on 2017/11/8.
 */

public  class MyBitMapUtils {
    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public MyBitMapUtils() {
        mLocalCacheUtils=new LocalCacheUtils();
        mMemoryCacheUtils=new MemoryCacheUtils();
        mNetCacheUtils=new NetCacheUtils(mLocalCacheUtils,mMemoryCacheUtils);
    }
    public void disPlay(ImageView ivPic,String url){
        ivPic.setImageResource(R.mipmap.ic_launcher);
        Bitmap bitmap;
              bitmap  = mMemoryCacheUtils.getBitMapFromMemory(url);
        if(bitmap!=null){
            ivPic.setImageBitmap(bitmap);
            Log.i("xxx","内存走一圈");
        }else {
            bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
            if (bitmap!=null){
                ivPic.setImageBitmap(bitmap);
                Log.i("xxx","本地走一圈");
            }else{
                mNetCacheUtils.getBitmapFromNet(ivPic,url);
                Log.i("xxx","网络走一圈");
            }

        }

    }
}
