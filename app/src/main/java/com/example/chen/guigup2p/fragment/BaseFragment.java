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
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/5.
 */

public abstract class BaseFragment extends Fragment {


    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

/*       // View view = UIUtils.getView(getLayoutId());// 使用自定义UIUtils 工具类加载view视图
        View view = View.inflate(getContext(),getLayoutId(), null);
        ButterKnife.bind(this, view);

        initTitle();

        initData();
        return view;
*/

        /**
         * 使用loadingpage
         */

        //确定loadingpage要显示的页面
        //请求成功后执行操作：
        //1. 返回响应结果（封装在resultState中的content中）
        //2. 返回对应的要显示的视图（四个fragment对应）
        //bind ButterKnife
        //初始化标题
        //初始化数据
        //联网请求参数
        //联网请求地址--暴露到具体fragment实现
        loadingPager = new LoadingPager(container.getContext()) {
            @Override
            public int layoutId() {//确定loadingpage要显示的页面
                return getLayoutId();
            }

            //请求成功后执行操作：
            //1. 返回响应结果（封装在resultState中的content中）
            //2. 返回对应的要显示的视图（四个fragment对应）
            @Override
            protected void onSuccess(ResultState resultState, View view_success) {

                ButterKnife.bind(BaseFragment.this,view_success); //bind ButterKnife


                initTitle();//初始化标题
                initData(resultState.getContent()); //初始化数据

            }

            //联网请求参数
            @Override
            protected RequestParams params() {
                return getParams();
            }

            //联网请求地址--暴露到具体fragment实现
            @Override
            protected String url() {
                return getUrl();
            }
        };

        return loadingPager;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingShowPage();//调用联网操作
    }

    //设置联网请求参数：具体fragment实现
    protected abstract RequestParams getParams();

    //设置请求地址--具体fragment实现
    protected abstract String getUrl();

    protected abstract int  getLayoutId();



    /**
     *
     * @param content : 联网请求的响应数据
     */
    protected abstract  void initData(String content) ;


    protected abstract void initTitle();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 调用loadingPage 的show方法
     */
    public  void loadingShowPage(){ //在MainActivity中调用
        loadingPager.show();
    }
}
