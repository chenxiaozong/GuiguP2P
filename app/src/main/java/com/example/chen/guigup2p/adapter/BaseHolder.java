package com.example.chen.guigup2p.adapter;

import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/7.
 */

abstract class BaseHolder<T> {


    private View rootView;

    private T data;

    public BaseHolder(){
        rootView = initView();
        rootView.setTag(this);
        ButterKnife.bind(this,rootView);
    }

    //提供item的布局
    protected abstract View initView();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        refreshData();
    }
    //装配过程: 不同视图不一样,需要抽象方法
    protected abstract void refreshData();

    public View getRootView() {
        return rootView;
    }

}
