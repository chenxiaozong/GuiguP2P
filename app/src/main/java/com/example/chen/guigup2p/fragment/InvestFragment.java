package com.example.chen.guigup2p.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.fragment.invest.ProductHotFragment;
import com.example.chen.guigup2p.fragment.invest.ProductListFragment;
import com.example.chen.guigup2p.fragment.invest.ProductRecommondFragment;
import com.example.chen.guigup2p.util.UIUtils;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/4/11.
 * 2. InvestFragment --投资
 */

public class InvestFragment extends BaseFragment {
    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;
    @Bind(R.id.ll_top_title_main)
    LinearLayout llTopTitleMain;

    @Bind(R.id.tabpage_invest)
    TabPageIndicator tabpagerInvest;

    @Bind(R.id.viewpage_invest)
    ViewPager viewPagerInvest;




/*    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment_invest, null);
        ButterKnife.bind(this, view);
        initTitle();
        return view;
    }*/

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
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData(String content) {
        //加载显示3个不同的fragment标签:    ProductHotFragment ProductListFragment ProdoctRecommodFragemnt

        initFragment();

        //2. viewpager 设置三个Fragment
        MyAdapter adapter =  new MyAdapter(getFragmentManager());

        viewPagerInvest.setAdapter(adapter);

        //3. 将TapPagerIndicator 与viewPager关联
        tabpagerInvest.setViewPager(viewPagerInvest);

    }

    /**
     * 初始化Fragment
     */
    private  List<Fragment> fragmentList = new ArrayList<>();

    private void initFragment() {


        ProductListFragment productListFragment = new ProductListFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();
        ProductRecommondFragment productRecommondFragment = new ProductRecommondFragment();

        //添加到list中
        fragmentList.add(productListFragment);
        fragmentList.add(productHotFragment);
        fragmentList.add(productRecommondFragment);

    }



    /**
     * 1. 初始化标题
     */
    protected void initTitle() {
        ivTopTitleBack.setVisibility(View.GONE);
        tvTopTitleTap.setText("投资");
        ivTopTitleSetting.setVisibility(View.GONE);
    }

    /**
     * 提供PagerAdapter的实现
     * 如果viewpager 中加载的时fragment, 则提供的Adpter 可以继承于具体的
     * FragmentStatePagerAdapter : 如果viewpager中加载的页面过多,会使用最近最少使用算法,实现内存中fragment的清理,
     * FragmentPagerAdapter      : 如果viewpager中加载的fragment 较少
     *
     */
    class  MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList==null?0:fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {// 将TapPagerIndicator 与viewPager关联

/* 方式一
            List<String> productIitle = new ArrayList<>();
            productIitle.add("全部理财");
            productIitle.add("热门理财");
            productIitle.add("推荐理财");

            return  productIitle.get(position);
*/


/*方式二 */

            String[] stringArr = UIUtils.getStringArr(R.array.invest_tab);

            return stringArr[position];
        }
    }


/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }*/
}
