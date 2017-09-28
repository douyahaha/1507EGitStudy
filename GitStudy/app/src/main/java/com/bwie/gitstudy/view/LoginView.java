package com.bwie.gitstudy.view;

/**
 * Created by 杨圆圆 on 2017/9/27.
 */

public interface LoginView {
    //显示进度条
    void showPrigressBar();
    //隐藏进度条
    void hidePrigressBar();
    //用户名输入错误
    void setNameError();
    //密码输入错误
    void setPwdError();
    //登录成功跳转
    void  pageJumps();
}
