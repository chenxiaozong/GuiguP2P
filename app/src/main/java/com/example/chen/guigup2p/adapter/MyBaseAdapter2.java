package com.example.chen.guigup2p.adapter;

import android.content.pm.ProviderInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.chen.guigup2p.bean.Product;

import java.util.List;

/**
 * Created by chen on 2017/8/7.
 * 抽取方法2 :抽取adapter和holder
 *
 */

public abstract class MyBaseAdapter2<T> extends  BaseAdapter{


    public List<T> list ;

    public MyBaseAdapter2(List<T> list) {
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


    //将具体的集合数据装配到具体的一个item layout中
    //问题一：item layout的布局是不确定的。
    //问题二：将集合中指定位置的数据装配到item，是不确定的。
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        BaseHolder holder ;

        if(view==null) {
            holder =getHolder();
        }else {
            holder = (BaseHolder) view.getTag();
        }

        //装配数据

        T t = list.get(i);

        holder.setData(t);

        //返回视图
        return holder.getRootView();

    }

    public abstract BaseHolder getHolder() ;

}
