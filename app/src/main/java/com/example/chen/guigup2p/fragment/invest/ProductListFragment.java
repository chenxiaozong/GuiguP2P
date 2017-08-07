package com.example.chen.guigup2p.fragment.invest;

import android.widget.ListView;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.fragment.BaseFragment;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;


/**
 * Created by chen on 2017/8/6.
 */

public class ProductListFragment extends BaseFragment {

    @Bind(R.id.tv_product_list)
    TextView tv_product_lidt;

    @Bind(R.id.lv_productlist)
    ListView lv_productlist;


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_list;
    }

    @Override
    protected void initData(String content) {
/*
        //1. 实现跑马灯效果
        tv_product_lidt.setFocusable(true);
        tv_product_lidt.setFocusableInTouchMode(true);
        tv_product_lidt.requestFocus();
*/


        //2. 使用自定义MarQuenTextView
    }

    @Override
    protected void initTitle() {

    }
}
