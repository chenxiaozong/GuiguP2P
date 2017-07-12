package com.example.chen.guigup2p.bean;

import java.util.List;

/**
 * Created by chen on 2017/4/14.
 * index 页面返回的son对应的bean对象
 */

public class IndexJson {
    public IndexProInfo proInfo ;
    public List<IndexImage> imageArr;

    @Override
    public String toString() {
        return "IndexJson{" +
                "proInfo=" + proInfo +
                ", imageArr=" + imageArr +
                '}';
    }
}
