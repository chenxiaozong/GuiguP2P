package com.example.chen.guigup2p.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.guigup2p.MainActivity;
import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.common.ActivityManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 启动界面: 停留2s 后跳转到MainActivity
 */
public class SplashActivity extends Activity {


    @Bind(R.id.tv_splash_name)
    TextView tvSplashName;
    @Bind(R.id.iv_splash_icon)
    ImageView ivSplashIcon;
    @Bind(R.id.tv_splash_version)
    TextView tvSplashVersion;
    @Bind(R.id.rl_welcome)
    RelativeLayout rlWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将当前的activity添加到ActivityManager中
        ActivityManager.getInstance().addActivity(this);//使用栈管理activity


        //1. 设置spalsh页面 全屏显示
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏顶部的状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        /*
        2.发送延时消息,启动MainActivity
        handler.sendEmptyMessageDelayed(1, 2000);
        */
        //3. 设置动画
        setAnimation();


    }

    /**
     * 3. 设置启动界面动画
     */
    private void setAnimation() {
        //1. 设置透明动画
        AlphaAnimation animation = new AlphaAnimation(0, 1);//透明度0-1
        animation.setDuration(2000);
        animation.setInterpolator(new AccelerateInterpolator());//设置变化率

        //结束动画后跳转页面
        //方式一: 动画监听
        /*animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/


        //方式二: handler延时消息
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },2000);

        //启动动画
        rlWelcome.startAnimation(animation);
    }

private Handler handler = new Handler();

    /*handler 使用一;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    startMainActivity();
                    break;
            }
        }
    };*/


    private void startMainActivity() {
        //1. 启动MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //2. 关闭当前视图
        this.finish();//关闭当前视图

        //2. 使用 栈空间管理 结束当前activiy 并从栈空间移除
        //ActivityManager.getInstance().removeActivity(SplashActivity.this);

    }


    @Override
    protected void onDestroy() {
        //1. 移除消息
        handler.removeCallbacksAndMessages(1);
        super.onDestroy();

    }
}
