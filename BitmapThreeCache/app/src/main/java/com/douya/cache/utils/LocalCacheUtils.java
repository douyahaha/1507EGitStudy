package com.douya.cache.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 本地缓存
 * Created by 杨圆圆 on 2017/11/8.
 */

public class LocalCacheUtils {
    //Sdcard路径
    private static final String CACHE_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+"/NetImage";
    /**
     * 从本地读取图片
     */
    public Bitmap getBitmapFromLocal(String url){
        try {
            String fileName = MD5Encoder.encode(url);
            File file=new File(CACHE_PATH,fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 从网络获取的图片保存到本地缓存
     */
    public void setBitmapToLocal(String url,Bitmap bitmap){
        try {
            String fileName = MD5Encoder.encode(url);
            //把图片的url当做文件名，并进行MD5加密
            File file=new File(CACHE_PATH,fileName);
            //得到文件的父文件，判断父文件是否存在
            File parentFile = file.getParentFile();
            //如果不存在将创建
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            //把图片保存至本地,类型，压缩质量，保存路径
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
