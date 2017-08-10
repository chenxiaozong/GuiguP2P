package com.example.chen.guigup2p.activity.more;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.activity.BaseActivity;
import com.example.chen.guigup2p.common.AppNetConfig;
import com.example.chen.guigup2p.util.UIUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;

public class UserRegisterActivity extends BaseActivity {
    //标题栏
    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;

    @Bind(R.id.et_register_number)
    EditText etRegisterNumber;
    @Bind(R.id.et_register_name)
    EditText etRegisterName;
    @Bind(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @Bind(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @Bind(R.id.btn_register)
    Button btnRegister;


    @Override
    protected void initData() {

    }


    @OnClick(R.id.iv_top_title_back)
    public void backOnclick(View view){
        removeCurrentActivity();
    }


    /**
     * 点击注册按钮事件:
     */
    @OnClick(R.id.btn_register)
    public void registerOnclick(View view) {

        //获取用户注册信息

        final String phone = etRegisterNumber.getText().toString().trim();
        final String name = etRegisterName.getText().toString().trim();

        final String pwd1 = etRegisterPwd.getText().toString().trim();
        final String pwd2 = etRegisterPwdagain.getText().toString().trim();

        //判断数据格式是否适配
        final boolean isDataRight = dataCheck(phone, name, pwd1, pwd2);

        if (isDataRight) {
            doRegister(phone,name,pwd1,pwd2);
        }


    }

    /**
     * 执行注册功能
     * @param phone :手机号
     * @param name  :用户名
     * @param pwd1  :首次输入的密码
     * @param pwd2  :重复输入的密码
     */
    private void doRegister(String phone, String name, String pwd1, String pwd2) {
        //向后台发送注册请求--执行注册请求
        String url = AppNetConfig.USERREGISTER;
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("name", name);
        params.put("password", pwd2);


        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {

                if (!TextUtils.isEmpty(content)) {//若数据库未开,返回null

                    //请求成功: 1. 注册成功, 2. 注册失败
                    JSONObject jsonObject = JSON.parseObject(content);

                    boolean isExist = jsonObject.getBoolean("isExist");

                    if (isExist) {
                        UIUtils.toast("当前手机号已经存在", false);
                    } else {
                        UIUtils.toast("出册成功,两秒后关闭此页面", false);

                        UIUtils.getHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                removeCurrentActivity();

                            }
                        },2000);
                    }
                } else {
                    UIUtils.toast("请求数据库失败", false);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {

                UIUtils.toast("连接服务器失败", false);
            }
        });
    }


    /**
     * 本地数据校验
     *
     * @param phone :必须为数字,不为空
     * @param name  : 不为空
     * @param pwd1: pwd不为空,且 pwd1==pwd2
     * @param pwd2
     */
    private boolean dataCheck(String phone, String name, String pwd1, String pwd2) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(pwd2)) {
            UIUtils.toast("填写信息不能为空", false);
            return false;

        } else if (!pwd1.equals(pwd2)) {
            //判断两次密码是否一致
            UIUtils.toast("两次密码不一致", false);

            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void initTitle() {
        ivTopTitleBack.setVisibility(View.VISIBLE);
        ivTopTitleSetting.setVisibility(View.INVISIBLE);
        tvTopTitleTap.setText("用户注册");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_register;
    }
}
