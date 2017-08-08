package com.example.chen.guigup2p.activity;

import android.media.tv.TvContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.chen.guigup2p.MainActivity;
import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.bean.User;
import com.example.chen.guigup2p.common.AppNetConfig;
import com.example.chen.guigup2p.util.MD5Utils;
import com.example.chen.guigup2p.util.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;


    @Bind(R.id.tv_login_number)
    TextView tvLoginNumber;
    @Bind(R.id.et_login_number)
    EditText etLoginNumber;
    @Bind(R.id.rl_login)
    RelativeLayout rlLogin;
    @Bind(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initData() {


    }

    @Override
    protected void initTitle() {
        ivTopTitleBack.setVisibility(View.VISIBLE);
        tvTopTitleTap.setText("用户登录");
        ivTopTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {//登录按钮的点击事件

        //1. 获取手机号和密码
        String phone = etLoginNumber.getText().toString().trim();
        String pwd = etLoginPwd.getText().toString().trim();

        //2. 若不为空执行联网请求
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)) {
            UIUtils.toast("用户名或密码不能为空",false);
        }else {
            doLogin(phone,pwd);
        }
    }

    //登录界面点击返回按钮,取消登录,返回到主页
    @OnClick(R.id.iv_top_title_back)
    public  void back(View view){
        removeAll();
        goToActivity(MainActivity.class,null);


    }

    /**
     * 执行联网操作
     * @param phone :登录手机号
     * @param pwd   :登录密码
     */
    private void doLogin(String phone, String pwd) {
        //1. 获取联网地址
        String loginUrl = AppNetConfig.LOGIN;

        //baseActivity 中的异步请求
        RequestParams params = new RequestParams();

        params.put("phone",phone);
        params.put("password", MD5Utils.MD5(pwd));//使用md5加密密码


        client.post(loginUrl,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                //检验成功:
                /*
                content  = {
                "data": {
                    "name": "shkstart",
                            "imageurl": "http://192.168.1.104:8080/P2PInvest/images/tx.png",
                            "iscredit": "true",
                            "phone": "13012341234"
                },
                "success": true
                 */


                //检验失败:
                //content = {"success":false}


                if(TextUtils.isEmpty(content)) {//数据库未启动

                    UIUtils.toast("读取服务器数据异常",false);
                    return;
                }

                JSONObject jsonObject = JSON.parseObject(content);
                Boolean success = jsonObject.getBoolean("success");

                if(success) {
                    //登录成功--解析用户数据

                    String data = jsonObject.getString("data");
                    User user = JSON.parseObject(data, User.class);

                    //2. 保存用户登录信息 到sp

                    saveUser(user);
                    //3. 销毁所有登录activity
                    removeAll();

                    //4. 跳转到mainActivity
                    goToActivity(MainActivity.class,null);

                }else {
                    UIUtils.toast("用户名或密码错误",false);
                }


            }

            @Override
            public void onFailure(Throwable error, String content) {
                //联网失败:提示
                UIUtils.toast("联网失败",false);
            }
        });

    }

}
