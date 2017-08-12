package com.example.chen.guigup2p.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.chen.guigup2p.MainActivity;
import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.bean.UpdateInfo;
import com.example.chen.guigup2p.common.ActivityManager;
import com.example.chen.guigup2p.common.AppNetConfig;
import com.example.chen.guigup2p.util.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 启动界面: 停留2s 后跳转到MainActivity
 */
public class SplashActivity extends Activity {


    private static final int TO_MAIN = 1;
    private static final int VERSION_ALERT_DIALOG = 2;//弹出提示框
    private static final int VERSION_UPDATE_INFO = 3; //获取update info 成功
    private static final int APK_DOWNLOAD_SUCCESS = 4; //apk下载成功
    private static final int APK_DOWNLOAD_FAIL = 5; //APK 下载失败

    @Bind(R.id.tv_splash_name)
    TextView tvSplashName;
    @Bind(R.id.iv_splash_icon)
    ImageView ivSplashIcon;
    @Bind(R.id.tv_splash_version)
    TextView tvSplashVersion;
    @Bind(R.id.rl_welcome)
    RelativeLayout rlWelcome;


    private long startTime; //启动时间
    private int animationTime = 2000;
    private File apkFile;

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

        checkApkVersion();

    }

    /**
     * 3. 设置启动界面动画
     */
    private void setAnimation() {
        //1. 设置透明动画
        AlphaAnimation animation = new AlphaAnimation(0, 1);//透明度0-1

        animation.setDuration(animationTime);
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


/* 创建handler
        //方式二: handler延时消息:动画结束后跳转
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },2000);

*/
        //启动动画
        rlWelcome.startAnimation(animation);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_MAIN://直接跳转到main
                    finish();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    break;
                case VERSION_UPDATE_INFO: //获取到最新版本 比较
                    Log.d("SplashActivity", updateInfo.version);
                    compareApkVersion(updateInfo, getVersion());
                    break;
                case VERSION_ALERT_DIALOG:
                    alertUpdateDiaolog();
                    break;

                case APK_DOWNLOAD_FAIL:
                    UIUtils.toast("下载文件失败!!",false);
                    toMain(2000);//延时2s进入main

                    break;
                case APK_DOWNLOAD_SUCCESS:
                    UIUtils.toast("下载apk文件成功",false);
                    dialog.dismiss();//取消dialog显示
                    installApk();
                    //结束当前splash activity的显示
                    finish();
                    break;

            }
        }
    };

    /**
     * 开始安装apk
     */
    private void installApk() {
        //        targetSdkVersion 23 及其以下
        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
        intent.setData(Uri.parse("file:" + apkFile.getAbsolutePath()));
        startActivity(intent);

/*      android 7.0 以上版本 需要 使用provider 访问隐私目录
        Intent i=new Intent(Intent.ACTION_VIEW, FileProvider.getUriForFile(this,));

        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(i);
*/
    }

    /**
     * G更新提示框
     */
    private void alertUpdateDiaolog() {
        new AlertDialog.Builder(SplashActivity.this)
                .setTitle("版本更新提示: ")
                .setMessage(updateInfo.desc)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        downloadApkFile();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //启动main
                        toMain(0);
                    }
                })
                .show();
    }

    /**
     * 从服务器下载apk
     * 1. 显示下载进度-主线程
     * 2. 启动下载任务-分线程
     */
    private ProgressDialog dialog;

    private void downloadApkFile() {

        //初始化水平进度条的dialog
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();


        //初始化apk 存放位置

        File fileDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            fileDir = this.getExternalFilesDir("");//sd卡存储路径:
        } else {
            fileDir = this.getFilesDir();//获取手机存储路径/storage/emulated/0/Android/data/com.example.chen.guigup2p/files
        }




        //File download = Environment.getExternalStoragePublicDirectory("download");


        apkFile = new File(fileDir,"apkFile.apk");


        //3. 启动分线程下载

        new Thread(){
            @Override
            public void run() {


                String path = updateInfo.apkUrl;
                URL url = null;

                HttpURLConnection conn=null;
                InputStream in = null;
                FileOutputStream out = null;

                try {
                    url = new URL(path);
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);

                    conn.connect();


                    if(conn.getResponseCode()==200) {

                        int contentLength = conn.getContentLength();
                        dialog.setMax(contentLength);//设置dialog最大值

                        in = conn.getInputStream();

                        out = new FileOutputStream(apkFile);


                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len=in.read(buffer))!=-1){
                            dialog.incrementProgressBy(len);//进度条增加
                            out.write(buffer,0,len);
                            SystemClock.sleep(1);
                        }

                        //下载完成
                        handler.sendEmptyMessage(APK_DOWNLOAD_SUCCESS);
                    }else {
                        handler.sendEmptyMessage(APK_DOWNLOAD_FAIL);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(conn != null) {
                        conn.disconnect();
                    }

                    if(in!=null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(out!=null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }


            }

        }.start();



    }


    /**
     * 跳转到main
     */
    private void toMain(int delay) {


        if(delay>0) {
            handler.sendEmptyMessageDelayed(TO_MAIN,delay);
            return;
        }


        long currentTime = System.currentTimeMillis();
        long delayTime = animationTime - (currentTime - startTime);
        if (delayTime < 0) {
            delayTime = 0;
        }

        handler.sendEmptyMessageDelayed(TO_MAIN, delayTime);




    }

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

    /**
     * 检查应用版本:
     * 1. 检查是否开启网络
     * 开启: 联网获取应用最新版本
     * 未开启: 取消版本更新
     */
    private void checkApkVersion() {
        //获取系统当前时间
        startTime = System.currentTimeMillis();

        boolean isConnectAvilable = isConnectAvilable();
        if (isConnectAvilable) {
            //1.获取最新版本信息
            getLaestApkVersion();
            return;
        }

        toMain(0);
        // startMainActivity();

    }

    public boolean isConnectAvilable() {
        boolean connected = false;

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
        return connected;
    }


    @Override
    protected void onDestroy() {
        //1. 移除消息
        handler.removeCallbacksAndMessages(1);
        super.onDestroy();

    }

    private UpdateInfo updateInfo;

    /**
     * 请求最新apk版本
     *
     * @return
     */
    public void getLaestApkVersion() {

        final String updateUrl = AppNetConfig.UPDATE;

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(updateUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {

                if (TextUtils.isEmpty(content)) {
                    UIUtils.toast("请求服务器数据异常", false);

                } else {
                    //返回content数据: {
                    //"version": "1.2",
                    //        "apkUrl":"http://192.168.1.104:8080/P2PInvest/app_new.apk",
                    //        "desc":"解决一些bug, 优化网络请求!"
                    updateInfo = JSON.parseObject(content, UpdateInfo.class);
                    handler.sendEmptyMessage(VERSION_UPDATE_INFO);

                }


            }

            @Override
            public void onFailure(Throwable error, String content) {
                UIUtils.toast("请求服务器数据失败", false);
            }
        });
    }

    /**
     * 比较版本
     *
     * @param updateInfo
     * @param localVersion
     */
    private void compareApkVersion(UpdateInfo updateInfo, String localVersion) {
        //1. 获取当前版本


        float currentVersion = Float.parseFloat(getVersion());
        float updateVersion = Float.parseFloat(updateInfo.version);

        if (currentVersion < updateVersion) {
            handler.sendEmptyMessage(VERSION_ALERT_DIALOG);
        } else {
            UIUtils.toast("当前版本为最新版本", false);
            // handler.sendEmptyMessage(TO_MAIN);
            toMain(0);//不需要版本更新时,跳转
        }


    }

    /**
     * 当前版本号
     *
     * @return
     */
    private String getVersion() {
        String version = "0";
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace(); //如果找不到对应的应用包信息, 就返回"未知版本"
        }
        return version;
    }


}
