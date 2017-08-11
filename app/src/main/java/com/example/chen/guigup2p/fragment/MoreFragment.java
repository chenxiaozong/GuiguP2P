package com.example.chen.guigup2p.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.activity.BaseActivity;
import com.example.chen.guigup2p.activity.more.UserRegisterActivity;
import com.example.chen.guigup2p.activity.more.gesture.GestureEditActivity;
import com.example.chen.guigup2p.util.UIUtils;
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

    private SharedPreferences sp; //保存手势开关状态的sp

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

        sp = getContext().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);


        getGestureState(sp);

        //设置手势密码
        setGesture();



        //为每个条目设置点击事件
        setItemOnclick();
    }

    private void setGesture() {
        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    UIUtils.toast("开启",false);
                    sp.edit().putBoolean("isOpen",true).commit(); //此处设置,下面可能会更改
                    toggleMore.setChecked(true);

                    //判断之前是否开启过
                    String inputCode = sp.getString("inputCode", "");
                    if(TextUtils.isEmpty(inputCode)) {//之前没设置过手势密码:
                        //启动alert 提示是否设置手势密码

                        new AlertDialog.Builder(MoreFragment.this.getContext()).setTitle("提示")
                                .setMessage("之前没有使用过手势密码,需要现在设置手势密码吗?")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //现在设定: 启动ACTIVIT
                                        BaseActivity base = (BaseActivity) MoreFragment.this.getContext();
                                        base.goToActivity(GestureEditActivity.class,null);

                                        //sp.edit().putBoolean("isOpen",true);
                                        //toggleMore.setChecked(true);

                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //取消设置手势密码
                                        //1.
                                        sp.edit().putBoolean("isOpen",false).commit();

                                        toggleMore.setChecked(false);
                                    }
                                })
                                .show();

                    }else {
                        //之前设置过手势密码:开启
                        sp.edit().putBoolean("inOpen",true).commit();
                        //toggleMore.setChecked(true);
                    }




                }else {
                    UIUtils.toast("关闭手势密码",false);
                    //保存状态到sp
                    sp.edit().putBoolean("isOpen",false).commit();
                }



            }
        });

    }

    /**
     * 设置不同视图的点击事件
     *
     */
    private void setItemOnclick() {
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

    /**
     * 获取getsture状态
     * @return
     * @param sp
     */
    public void  getGestureState(SharedPreferences sp) {

        if(this.sp !=null) {
            boolean isOpen = this.sp.getBoolean("isOpen",false);

            if(isOpen) {
                toggleMore.setChecked(true);
            }else {
                toggleMore.setChecked(false);
            }
        }


    }
/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }*/
}
