package com.bwie.gitstudy.model;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.bwie.gitstudy.LoginFinishLisenter;

/**
 * Created by 杨圆圆 on 2017/9/27.
 */

public class LoginModelImpl implements LoginModel {

    @Override
    public void login(final Context context, final String name, final String pwd, final LoginFinishLisenter lisenter) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(name)){
                    lisenter.onNameError();
                    Toast.makeText(context,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    lisenter.onPwdError();
                    Toast.makeText(context,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                lisenter.onSuccess();
                Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
            }
        },2000);
    }
}
