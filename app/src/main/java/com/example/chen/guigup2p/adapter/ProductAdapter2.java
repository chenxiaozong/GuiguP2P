package com.example.chen.guigup2p.adapter;

import com.example.chen.guigup2p.bean.Product;

import java.util.List;

/**
 * Created by chen on 2017/8/7.
 */

public class ProductAdapter2 extends MyBaseAdapter2<Product> {

    public ProductAdapter2(List<Product> list) {
        super(list);
    }

    @Override
    public BaseHolder getHolder() {
        return new MyProductHolder();
    }
}
