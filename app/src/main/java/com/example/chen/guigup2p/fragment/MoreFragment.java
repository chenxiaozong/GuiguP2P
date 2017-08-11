package com.example.chen.guigup2p.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.activity.BaseActivity;
import com.example.chen.guigup2p.activity.more.UserRegisterActivity;
import com.example.chen.guigup2p.activity.more.gesture.GestureEditActivity;
import com.example.chen.guigup2p.common.AppNetConfig;
import com.example.chen.guigup2p.util.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
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
                if (isChecked) {
                    UIUtils.toast("开启", false);
                    sp.edit().putBoolean("isOpen", true).commit(); //此处设置,下面可能会更改
                    toggleMore.setChecked(true);

                    //判断之前是否开启过
                    String inputCode = sp.getString("inputCode", "");
                    if (TextUtils.isEmpty(inputCode)) {//之前没设置过手势密码:
                        //启动alert 提示是否设置手势密码

                        new AlertDialog.Builder(MoreFragment.this.getContext()).setTitle("提示")
                                .setMessage("之前没有使用过手势密码,需要现在设置手势密码吗?")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //现在设定: 启动ACTIVIT
                                        BaseActivity base = (BaseActivity) MoreFragment.this.getContext();
                                        base.goToActivity(GestureEditActivity.class, null);

                                        //sp.edit().putBoolean("isOpen",true);
                                        //toggleMore.setChecked(true);

                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //取消设置手势密码
                                        //1.
                                        sp.edit().putBoolean("isOpen", false).commit();

                                        toggleMore.setChecked(false);
                                    }
                                })
                                .show();

                    } else {
                        //之前设置过手势密码:开启
                        sp.edit().putBoolean("inOpen", true).commit();
                        //toggleMore.setChecked(true);
                    }


                } else {
                    UIUtils.toast("关闭手势密码", false);
                    //保存状态到sp
                    sp.edit().putBoolean("isOpen", false).commit();
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
        switch (view.getId()) {
            case R.id.tv_more_regist:
                Log.d("MoreFragment", "用户注册");
                BaseActivity baseActivity = (BaseActivity) this.getActivity();
                baseActivity.goToActivity(UserRegisterActivity.class, null);

                break;
            case R.id.tv_more_reset:
                Log.d("MoreFragment", "重置密码");
                resetGesture();
                break;
            case R.id.tv_more_about:
                Log.d("MoreFragment", "关于");
                break;
            case R.id.rl_more_contact:
                Log.d("MoreFragment", "客服");
                contactService();
                break;
            case R.id.tv_more_fankui:
                setFeedBack();
                break;
            case R.id.tv_more_share:
                Log.d("MoreFragment", "分享");
                break;
        }
    }

    /**
     * 向后台发送反馈信息
     * alertdialog
     */
    private String department = "不明确";

    private void setFeedBack() {
        View feedbackll = View.inflate(MoreFragment.this.getActivity(), R.layout.view_feedback, null);
        final RadioGroup rg_fankui  = feedbackll.findViewById(R.id.rg_fankui);
        final EditText etContent = feedbackll.findViewById(R.id.et_fankui_content);//文本框

        rg_fankui.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checked) {
                RadioButton radioButton = rg_fankui.findViewById(checked);
                department = radioButton.getText().toString();
            }

        });




        new AlertDialog.Builder(MoreFragment.this.getActivity())
                .setView(feedbackll)
                .setPositiveButton("发送", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String feedBackContent = etContent.getText().toString().trim();
                        postFeedBackClien(feedBackContent, department);

                    }
                })
                .setNegativeButton("取消",null)
                .show();



    }

    /**
     * 启用异步请求,发送反馈信息
     *
     *
     * @param feedBackContent
     * @param department
     */
    private void postFeedBackClien(String feedBackContent, String department) {
        AsyncHttpClient client = new AsyncHttpClient();

        String url = AppNetConfig.FEEDBACK;




        RequestParams params = new RequestParams();
        params.put("department",department);
        params.put("content",feedBackContent);

        client.post(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                UIUtils.toast("反馈成功!!",false);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                UIUtils.toast("联网请求失败!!",false);
            }
        });


    }


    /**
     * 联系客服
     */
    private void contactService() {

        //1. 获取客服号码:
        final String phone = tvMorePhone.getText().toString().trim();

        //2. dialog

        new AlertDialog.Builder(MoreFragment.this.getActivity())
                .setMessage("拨打客服电话:" + phone)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        //1. 使用隐式意图启动intent
                        Intent intent = new Intent(Intent.ACTION_CALL);


                        //2.
                        intent.setData(Uri.parse("tel:" + phone));
                        if (ActivityCompat.checkSelfPermission(MoreFragment.this.getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        MoreFragment.this.getActivity().startActivity(intent);

                    }
                })
                .setNegativeButton("取消",null)
                .show();




    }

    /**
     * 重置手势密码:
     * 检查手势密码是否开启?
     *   > 已开启: startActivity
     *   > 未开启:toast
     */
    private void resetGesture() {
        boolean checked = toggleMore.isChecked();

        if(checked) {
            BaseActivity base = (BaseActivity) this.getContext();
            base.goToActivity(GestureEditActivity.class,null);

        }else {
            UIUtils.toast("手势密码功能未开启,请开启后设置",false);

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
