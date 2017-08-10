package com.example.chen.guigup2p.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.activity.BaseActivity;
import com.example.chen.guigup2p.activity.more.UserRegisterActivity;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;

/**
 * Created by chen on 2017/4/11.
 * 4. MoreFragment --更多
 */

public class MoreFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;
    @Bind(R.id.ll_top_title_main)
    LinearLayout llTopTitleMain;


    @Bind(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @Bind(R.id.toggle_more)
    ToggleButton toggleMore;
    @Bind(R.id.tv_more_reset)
    TextView tvMoreReset;
    @Bind(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @Bind(R.id.tv_more_fankui)
    TextView tvMoreFankui;
    @Bind(R.id.tv_more_share)
    TextView tvMoreShare;
    @Bind(R.id.tv_more_about)
    TextView tvMoreAbout;
    @Bind(R.id.tv_more_phone)
    TextView tvMorePhone;




/*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment_more, null);
        ButterKnife.bind(this, view);
        initTitle();
        return view;
    }
*/


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
        return R.layout.fragment_more;
    }

    @Override
    protected void initData(String content) {

        //为每个条目设置点击事件
        tvMoreRegist.setOnClickListener(this);

        tvMoreReset.setOnClickListener(this);

        rlMoreContact.setOnClickListener(this);
        tvMoreFankui.setOnClickListener(this);
        tvMoreShare.setOnClickListener(this);

        tvMoreAbout.setOnClickListener(this);

    }


    /**
     * 1. 初始化标题
     */
    protected void initTitle() {
        ivTopTitleBack.setVisibility(View.INVISIBLE);
        tvTopTitleTap.setText("更多");
        ivTopTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_more_regist :
                Log.d("MoreFragment", "用户注册");
                BaseActivity baseActivity = (BaseActivity) this.getActivity();
                baseActivity.goToActivity(UserRegisterActivity.class,null);



                break;
            case R.id.tv_more_reset:
                Log.d("MoreFragment", "重置密码");
                break;
            case R.id.tv_more_about:
                Log.d("MoreFragment", "关于");
                break;
            case R.id.rl_more_contact:
                Log.d("MoreFragment", "客服");
                break;
            case R.id.tv_more_fankui:
                Log.d("MoreFragment", "反馈");
                break;
            case R.id.tv_more_share:
                Log.d("MoreFragment", "分享");
                break;
        }
    }
/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }*/
}
