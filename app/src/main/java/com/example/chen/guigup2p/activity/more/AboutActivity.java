package com.example.chen.guigup2p.activity.more;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.activity.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {
    //标题栏
    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;

    @Override
    protected void initData() {

    }
    @Override
    protected void initTitle() {
        ivTopTitleBack.setVisibility(View.VISIBLE);
        ivTopTitleSetting.setVisibility(View.INVISIBLE);
        tvTopTitleTap.setText("关于硅谷");

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }


    @OnClick(R.id.iv_top_title_back)
    public  void back(View view){
        removeCurrentActivity();
    }
}
