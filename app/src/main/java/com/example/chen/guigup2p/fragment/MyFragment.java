package com.example.chen.guigup2p.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.util.UIUtils;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/4/11.
 * 1. MyFragment --我的
 */

public class MyFragment extends BaseFragment {
    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;
    @Bind(R.id.ll_top_title_main)
    LinearLayout llTopTitleMain;

/* 抽取到baseFragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment_my, null);
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
        return R.layout.fragment_my;
    }

    @Override
    protected void initData(String content) {
        //1. 判断用户是否登录?
        isLogin();

    }


    /**
     * 1. 初始化标题
     */
    protected void initTitle() {
        tvTopTitleTap.setText("我的");
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 显示页面前判断用户是否登录
     * > 登陆过: 加载本地sp
     * > 未登录: 显示登录对话框
     * @return
     */
    public void isLogin() {
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String userName = sp.getString("name","");
        
        if(TextUtils.isEmpty(userName)) {
            //提示登录对话框
            doAlertLoginDialog();
            
        }else {
            doLoadingLocalUser();//本地登录 加载本地sp 用户信息登录
        }

    }

    private void doLoadingLocalUser() {

    }

    /**
     * 提示登录对话框
     *
     */
    private void doAlertLoginDialog() {


/*
        AlertDialog.Builder builder = new AlertDialog.Builder(UIUtils.getContext());

        builder.setTitle("提示")
                .setMessage("请登录")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UIUtils.toast("正在登录",false);
                    }
                });
        AlertDialog dialog = builder.create();

        dialog.show();
*/


        new android.app.AlertDialog.Builder(this.getActivity())
                .setTitle("提示")
                .setMessage("您还没有登录哦！需要登录吗??")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            UIUtils.toast("进入登录页面",false);
                //        ((BaseActivity) MeFragment.this.getActivity()).goToActivity(LoginActivity.class, null);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UIUtils.toast("取消登录",false);
                    }
                })

//                .setCancelable(true)
                .show();


    }
}
