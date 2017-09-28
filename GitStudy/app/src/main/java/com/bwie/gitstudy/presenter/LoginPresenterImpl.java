package com.bwie.gitstudy.presenter;

import android.content.Context;

import com.bwie.gitstudy.LoginFinishLisenter;
import com.bwie.gitstudy.model.LoginModelImpl;
import com.bwie.gitstudy.view.LoginView;

/**
 * Created by 杨圆圆 on 2017/9/27.
 */

public class LoginPresenterImpl implements LoginPresenter,LoginFinishLisenter {
    private LoginView loginView;
    private final LoginModelImpl loginModel;

    /**
     * 有参构造获取view
     * 多态获取presenter
     * @param loginView
     */
    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    /**
     * 此方法将从view层获取上下文，用户名和密码
     * 展示PrigressBar
     * 将view和model进行关联
     * @param context
     * @param name
     * @param pwd
     */
    @Override
    public void validatePass(Context context,String name,String pwd) {

        if(loginView!=null){
            loginView.showPrigressBar();
        }
        //将view和model进行关联
        loginModel.login(context,name,pwd,this);
    }

    /**
     * 此方法解决内存泄漏问题
     */
    @Override
    public void onDestory() {
        if(loginView!=null){
            loginView=null;
        }
    }
    /**
     * 此方法为接口回调，隐藏PrigressBar和设置EditText的name错误信息
     */
    @Override
    public void onNameError() {
        if(loginView!=null){
            loginView.setNameError();
            loginView.hidePrigressBar();
        }

    }
    /**
     * 此方法为接口回调，隐藏PrigressBar和设置EditText的pwd错误信息
     */
    @Override
    public void onPwdError() {
        if(loginView!=null){
            loginView.setPwdError();
            loginView.hidePrigressBar();
        }
    }
    /**
     * 此方法为接口回调，隐藏PrigressBar和进行页面跳转
     */
    @Override
    public void onSuccess() {
        if(loginView!=null){
            loginView.pageJumps();
            loginView.hidePrigressBar();
        }
    }
}
