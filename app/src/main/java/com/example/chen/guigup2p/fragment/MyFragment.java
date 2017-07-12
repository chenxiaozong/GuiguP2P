package com.example.chen.guigup2p.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chen.guigup2p.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/4/11.
 * 1. MyFragment --我的
 */

public class MyFragment extends Fragment {
    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;
    @Bind(R.id.ll_top_title_main)
    LinearLayout llTopTitleMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment_my, null);
        ButterKnife.bind(this, view);
        initTitle();
        return view;
    }


    /**
     * 1. 初始化标题
     */
    private void initTitle() {
        tvTopTitleTap.setText("我的");
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
