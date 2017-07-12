/*
package com.example.chen.guigup2p.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.bean.IndexImage;
import com.example.chen.guigup2p.bean.IndexJson;
import com.example.chen.guigup2p.bean.IndexProInfo;
import com.example.chen.guigup2p.common.AppNetConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

*/
/**
 * Created by chen on 2017/4/11.
 * 1. HomeFragment --首页
 *//*


public class HomeFragment_copy extends Fragment {


    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;
    @Bind(R.id.ll_top_title_main)
    LinearLayout llTopTitleMain;
    @Bind(R.id.tv_home_fragment_product)
    TextView tvHomeFragmentProduct;
    @Bind(R.id.tv_fragment_home_yearrate)
    TextView tvFragmentHomeYearrate;
    @Bind(R.id.bt_fragment_home_join)
    Button btFragmentHomeJoin;
    @Bind(R.id.vp_fragment_home)
    ViewPager vpFragmentHome;
    @Bind(R.id.vpi_home_indicator)
    CirclePageIndicator vpiHomeIndicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View view = UIUtils.getView(R.layout.fragment_home);
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);

        initTitle();

        initData();
        return view;
    }

    */
/**
     * 初始化数据:联网获取数据
     * 1. 使用 android-async-http-master.jar 框架联网
     * 2. 使用app/libs/fastjson-1.2.4.jar 框架解析json数据
     *//*

    private void initData() {
        String url = AppNetConfig.INDEX; //首页面url

        Log.d("HomeFragment", url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                //解析请求到的json格式数据
                parseJsonWithFJ(content);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                Toast.makeText(getContext(), "联网失败:" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    */
/**
     * 使用FastJson解析json数据
     *
     * @param content
     *//*

    private IndexJson indexJson;

    private void parseJsonWithFJ(String content) {
        //创建bean对象
        indexJson = new IndexJson();

        JSONObject jsonObject = JSON.parseObject(content);
        String strProInfo = jsonObject.getString("proInfo");
        //1. 解析proinfo 对象
        IndexProInfo proInfo = JSON.parseObject(strProInfo, IndexProInfo.class);

        //2. 解析image数组
        String strImageArr = jsonObject.getString("imageArr");
        List<IndexImage> imageArr = JSON.parseArray(strImageArr, IndexImage.class);

        //3. 封装到indexbean对象中
        indexJson.proInfo = proInfo;
        indexJson.imageArr = imageArr;

        //4. 更新页面数据
        tvHomeFragmentProduct.setText(indexJson.proInfo.name);
        tvFragmentHomeYearrate.setText(indexJson.proInfo.yearRate + "%");


        //5. 设置viewpager
        vpFragmentHome.setAdapter(new ViewPagerAdapter());

        //6. 设置viewpagerindicator

        vpiHomeIndicator.setViewPager(vpFragmentHome);

    }


    */
/**
     * 1. 初始化标题
     *//*

    private void initTitle() {
        ivTopTitleBack.setVisibility(View.INVISIBLE);
        tvTopTitleTap.setText("首页");
        ivTopTitleSetting.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    */
/**
     * viewpager Adapter
     *//*

    private class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return indexJson.imageArr.size() == 0 ? 0 : indexJson.imageArr.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //1. 实例化imageview
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置图片剧中显示
            IndexImage indexImage = indexJson.imageArr.get(position);
            String imgurl = indexImage.IMAURL;  //获取图片地址
            //2) 使用picasso 根据url获取图片
            Picasso.with(getContext())
                    .load(imgurl)
                    .into(imageView);


            //2. 将imageview 添加到container中
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
}
*/
