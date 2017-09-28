package com.bwie.gitstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bwie.gitstudy.presenter.LoginPresenterImpl;
import com.bwie.gitstudy.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private EditText et_name;
    private EditText et_pwd;
    private Button bt_login;
    private ProgressBar progressBar;
    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		//查找组件
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginPresenter = new LoginPresenterImpl(this);
        //给按钮添加点击事件
        bt_login.setOnClickListener(this);
    }

    @Override
    public void showPrigressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePrigressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setNameError() {
        et_name.setError("name con not be empty");
    }

    @Override
    public void setPwdError() {
        et_pwd.setError("pwd con not be empty");
    }

    @Override
    public void pageJumps() {
        Toast.makeText(this,"跳转页面成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        //将view的内容关联到presenter
        loginPresenter.validatePass(this,et_name.getText().toString(),et_pwd.getText().toString());
    }

    /**
     * 用于销毁view，解决内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestory();
    }
}
