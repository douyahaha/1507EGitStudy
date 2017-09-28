package com.bwie.gitstudy.model;

import android.content.Context;

import com.bwie.gitstudy.LoginFinishLisenter;

/**
 * Created by 杨圆圆 on 2017/9/27.
 */

public interface LoginModel {
    void login(Context context, String name, String pwd, LoginFinishLisenter lisenter);
}
