package com.example.chen.guigup2p.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.chen.guigup2p.ui.RoundProgress;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/4/11.
 * 1. HomeFragment --首页
 */

public class HomeFragment extends Fragment {
    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;
    @Bind(R.id.ll_top_title_main)
    LinearLayout llTopTitleMain;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_fragment_product)
    TextView tvHomeFragmentProduct;
    @Bind(R.id.diy_roundProgress)
    RoundProgress diyRoundProgress;
    @Bind(R.id.tv_fragment_home_yearrate)
    TextView tvFragmentHomeYearrate;
    @Bind(R.id.bt_fragment_home_join)
    Button btFragmentHomeJoin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View view = UIUtils.getView(R.layout.fragment_home); 使用自定义UIUtils 工具类加载view视图
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);

        initTitle();

        initData();
        return view;
    }

    /**
     * 初始化数据:联网获取数据
     * 1. 使用 android-async-http-master.jar 框架联网
     * 2. 使用app/libs/fastjson-1.2.4.jar 框架解析json数据
     */
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

    /**
     * 使用FastJson解析json数据
     *
     * @param content
     */
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

        diyRoundProgress.setRoundProgress(Float.parseFloat(proInfo.progress));

/*        //5. 设置viewpager
        vpFragmentHome.setAdapter(new ViewPagerAdapter());

        //6. 设置viewpagerindicator

        vpiHomeIndicator.setViewPager(vpFragmentHome);*/

        //7.设置banner
        setBanner(2); //int  1: 精简  2: 详细
    }

    /**
     * 设置banner
     */
    private void setBanner(int mode) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置图片地址集合
        List<String> imagesUrl = new ArrayList<String>(indexJson.imageArr.size());
        for (int i = 0; i < indexJson.imageArr.size(); i++) {
            imagesUrl.add(indexJson.imageArr.get(i).IMAURL);
        }

        switch (mode) {
            case 1: //1. 简单轮番
                banner.setImages(imagesUrl)
                        .setImageLoader(new GlideImageLoader())
                        .start();
                break;
            case 2: //2. 详细轮番
                String titles[] = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};
                banner.setImages(imagesUrl)                             //设置图片(集合)
                        .setBannerAnimation(Transformer.CubeOut)      //设置动画
                        .setBannerTitles(Arrays.asList(titles))         //设置标题
                        .isAutoPlay(true)                               //设置自动轮播，默认为true
                        .setDelayTime(1500)                             //设置轮播时间
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setIndicatorGravity(BannerConfig.CENTER)       //设置指示器位置（当banner模式中有指示器时）
                        .start();                                       //banner设置方法全部调用完毕时最后调用

                break;
        }

    }


    /**
     * 1. 初始化标题
     */
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


    /**
     * 使用banner:重写图片加载器
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}
