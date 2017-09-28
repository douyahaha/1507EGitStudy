package com.bwie.gitstudy.presenter;

import android.content.Context;

/**
 * Created by 杨圆圆 on 2017/9/27.
 */

public interface LoginPresenter {
    void validatePass(Context context,String name,String pwd);
    void onDestory();
}
