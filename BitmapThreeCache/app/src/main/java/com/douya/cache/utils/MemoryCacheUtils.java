package com.douya.cache.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存缓存
 * Created by 杨圆圆 on 2017/11/8.
 */

public class MemoryCacheUtils {
    private LruCache<String ,Bitmap> mMemoryCache;

    public MemoryCacheUtils(){
        //获取系统分配给应用程序的最大内存,给LruCache分配1/8
        long maxMemory = Runtime.getRuntime().maxMemory() / 8;
        mMemoryCache=new LruCache<String ,Bitmap>((int) maxMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
               ////必须重写此方法，来测量Bitmap的大小
                int byteCount = value.getRowBytes() * value.getHeight();
                return byteCount;
            }
        };

    }
    /**
     * 从内存读取
     */
    public Bitmap getBitMapFromMemory(String url){
        return mMemoryCache.get(url);
    }
    /**
     * 写入内存
     */
    public void setBitMapToMemory(String url,Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
    }
}
