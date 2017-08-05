package com.example.chen.guigup2p.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.ui.LoadingPager;
import com.example.chen.guigup2p.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/5.
 */

public abstract class BaseFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

/*       // View view = UIUtils.getView(getLayoutId());// 使用自定义UIUtils 工具类加载view视图
        View view = View.inflate(getContext(),getLayoutId(), null);
        ButterKnife.bind(this, view);

        initTitle();

        initData();
        return view;*/

        /**
         * 使用loadingpage
         */

        LoadingPager loadingPager = new LoadingPager(container.getContext()) {
            @Override
            public int layoutId() {
                return getLayoutId();
            }
        };

        return  loadingPager;

    }

    protected abstract int  getLayoutId();

    protected abstract  void initData() ;

    protected abstract void initTitle();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
