package com.example.chen.guigup2p.fragment.invest;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.adapter.ProductAdapter;
import com.example.chen.guigup2p.bean.Product;
import com.example.chen.guigup2p.common.AppNetConfig;
import com.example.chen.guigup2p.fragment.BaseFragment;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by chen on 2017/8/6.
 */

public class ProductListFragment extends BaseFragment {

    @Bind(R.id.tv_product_list)
    TextView tv_product_lidt;

    @Bind(R.id.lv_productlist)
    ListView lv_productlist;



    //理财产品list
    private List<Product> productslist = new ArrayList<>();

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.PRODUCT;
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
        
        
        
        //3. 解析联网请求的数据

        JSONObject jsonObject = JSON.parseObject(content);

        Boolean success = jsonObject.getBoolean("success");

        if(success) {//获取数据成功
            String data = jsonObject.getString("data");
            productslist = JSON.parseArray(data,Product.class);

            //为listvie 设置adapter
            lv_productlist.setAdapter(new ProductAdapter(productslist));

        }
        
        
    }

    @Override
    protected void initTitle() {

    }
}
