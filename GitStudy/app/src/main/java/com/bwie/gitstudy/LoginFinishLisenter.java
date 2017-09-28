package com.bwie.gitstudy;

/**
 * Created by 杨圆圆 on 2017/9/27.
 */

public interface LoginFinishLisenter {
    void onNameError();
    void onPwdError();
    void onSuccess();
}
