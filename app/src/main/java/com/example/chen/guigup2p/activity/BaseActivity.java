package com.example.chen.guigup2p.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.chen.guigup2p.bean.User;
import com.example.chen.guigup2p.common.ActivityManager;
import com.loopj.android.http.AsyncHttpClient;

import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/12/6 0006.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //将当前activity添加到activityManager 中
        ActivityManager.getInstance().addActivity(this);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initTitle();

        initData();

    }

    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();

    public AsyncHttpClient client = new AsyncHttpClient();

    //启动新的activity
    public void goToActivity(Class Activity,Bundle bundle){
        Intent intent = new Intent(this,Activity);
        //携带数据
        if(bundle != null && bundle.size() != 0){
            intent.putExtra("data",bundle);
        }

        startActivity(intent);
    }

    //销毁当前的Activity
    public void removeCurrentActivity(){
        ActivityManager.getInstance().removeCurrent();
    }

    //销毁所有的activity
    public void removeAll(){
        ActivityManager.getInstance().removeAll();
    }

    //保存用户信息
    public void saveUser(User user){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",user.getName());
        editor.putString("imageurl",user.getImageurl());
        editor.putBoolean("iscredit", user.isCredit());
        editor.putString("phone",user.getPhone());
        editor.commit();//必须提交，否则保存不成功
    }

    //读取用户信息
    public User readUser(){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        User user = new User();
        user.setName(sp.getString("name",""));
        user.setImageurl(sp.getString("imageurl", ""));
        user.setPhone(sp.getString("phone", ""));
        user.setCredit(sp.getBoolean("iscredit",false));

        return user;
    }
}
