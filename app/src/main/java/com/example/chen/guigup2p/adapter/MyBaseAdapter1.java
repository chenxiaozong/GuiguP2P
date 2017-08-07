package com.example.chen.guigup2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by chen on 2017/8/7.
 * adapter 抽取 一
 *
 */

public abstract class MyBaseAdapter1<T> extends BaseAdapter {
    public List<T> list;

    public MyBaseAdapter1(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();

    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View convertView = setMyView(i, view, viewGroup);

        return convertView;
    }

    public abstract View setMyView(int i, View view, ViewGroup viewGroup);


}
