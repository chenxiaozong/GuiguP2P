package com.example.chen.guigup2p.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.common.ActivityManager;

import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/8.
 */

public abstract class BaseActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将当前activity添加到activityManager 中
        ActivityManager.getInstance().addActivity(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initData();
        initTitle();

    }

    protected abstract void initTitle();

    protected abstract void initData() ;


    protected  abstract  int getLayoutId();


}
