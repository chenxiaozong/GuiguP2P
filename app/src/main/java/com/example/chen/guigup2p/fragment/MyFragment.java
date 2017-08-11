package com.example.chen.guigup2p.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.activity.BaseActivity;
import com.example.chen.guigup2p.activity.ChongZhiActivity;
import com.example.chen.guigup2p.activity.LoginActivity;
import com.example.chen.guigup2p.activity.TiXianActivity;
import com.example.chen.guigup2p.activity.UserInfoActivity;
import com.example.chen.guigup2p.activity.more.gesture.GestureVerifyActivity;
import com.example.chen.guigup2p.activity.my.BarChartAcivity;
import com.example.chen.guigup2p.activity.my.LineChartActivity;
import com.example.chen.guigup2p.activity.my.PieChartActivity;
import com.example.chen.guigup2p.bean.User;
import com.example.chen.guigup2p.util.BitmapUtils;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 2017/4/11.
 * 1. MyFragment --我的
 */

public class MyFragment extends BaseFragment {


    //标题栏
    @Bind(R.id.iv_top_title_back)
    ImageView ivTopTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTopTitleTap;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTopTitleSetting;

    @Bind(R.id.ll_top_title_main)
    LinearLayout llTopTitleMain;



    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;

    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;





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
     *  >
     *
     * @return
     */
    public void isLogin() {
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String userName = sp.getString("name", "");

        if (TextUtils.isEmpty(userName)) {//没有登录过
            //提示登录对话框
            doAlertLoginDialog();//弹出对话框提示登录并获取数据

        } else {//已经登录
            doLoadingLocalUser();//本地登录 加载本地sp 用户信息登录
        }

    }

    private void doLoadingLocalUser() {
        BaseActivity mainActivity = (BaseActivity) getActivity();
        User user = mainActivity.readUser();//BaseActivity 抽象方法 readUser


        //判断是否开启手势密码? 开启(输入手势密码 ):没开启(直接显示)
        SharedPreferences setcretsp = this.getContext().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);
        boolean isOpen = setcretsp.getBoolean("isOpen", false);
        showUserInfo(user);

        if(isOpen) {
            BaseActivity baseActivity = (BaseActivity) this.getContext();
            baseActivity.goToActivity(GestureVerifyActivity.class,null);
            return;
        }
    }

    /**
     * 加载user用户资料
     * 显示用户头像
     * 显示用户名
     * @param user
     */
    private void showUserInfo(User user) {


        //显示用户名:
        tvMeName.setText(user.getName());

        String imgurl = user.getImageurl();

        //判断是否存在本地图像,存在则不需要联网加载,不存在则需要联网加载

        boolean isExist = readLocalIcon();
        if(isExist) {
            return;
        }


        //2. 显示用户头像: 未经过圆形处理
/*
        Picasso.with(getContext())
                .load(imgurl)
                .into(ivMeIcon);
*/

     /*   //2.显示用户头像: 圆形处理:未压缩
        Picasso.with(getContext())
                .load(imgurl)
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        Bitmap bitmap = BitmapUtils.circleBitmap(source);//使用Utils 对头像圆形处理(截取)
                        source.recycle();//回收资源--否则会有异常
                        return bitmap;
                    }

                    @Override
                    public String key() {
                        return "";//必须不能为null
                    }
                })
                .into(ivMeIcon);

*/
        //2. 显示用户头像: 圆形处理:压缩处理




        Picasso.with(getContext())
                .load(imgurl)
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        //1. 压缩处理
                        Bitmap zoom = BitmapUtils.zoom(source, source.getWidth(), source.getHeight());

                        //2. 圆形处理
                        Bitmap bitmap = BitmapUtils.circleBitmap(zoom);
                        source.recycle();

                        return bitmap;
                    }

                    @Override
                    public String key() {
                        return "";//返回值保证不能为null
                    }
                })
                .into(ivMeIcon);




    }

    /**
     * 提示登录对话框
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
                        startLoginActivity();
                    }
                })
              /*  .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UIUtils.toast("取消登录",false);

                        //跳转到主页
                        BaseActivity mainActivity = (BaseActivity) MyFragment.this.getActivity();
                        mainActivity.goToActivity(MainActivity.class,null);

                    }
                })*/
                .setCancelable(false)
                .show();


    }

    private void startLoginActivity() {

/*
        Intent intent = new Intent(MyFragment.this.getContext(), LoginActivity.class);
        startActivity(intent);
*/
        //方式二: 使用BaseActivty 中的gotoActivity() 启动
        // ((BaseActivity) MyFragment.this.getActivity()).goToActivity(LoginActivity.class, null);


        //启动登录activity
        BaseActivity mainActivity = (BaseActivity) MyFragment.this.getActivity();
        mainActivity.goToActivity(LoginActivity.class,null);

    }


    /**
     * 头像点击事件
     */
    @OnClick(R.id.iv_me_icon)
    public  void meIconClick(View view){
        startUserInfoAcrivity();
    }

    /**
     * 点击设置
     * @param view
     */
    @OnClick(R.id.iv_top_title_setting)
    public  void titleSettingClick(View view){
        startUserInfoAcrivity();
    }


    /**
     * 充值点击按钮
     * @param view
     */

    @OnClick(R.id.recharge)
    public  void rechargeOnclick(View view){
        //跳转到充值页面

        BaseActivity baseActivity = (BaseActivity) this.getActivity();
        baseActivity.goToActivity(ChongZhiActivity.class,null);

    }

    /**
     * 提现
     * @param view
     */
    @OnClick(R.id.withdraw)
    public  void tiXian(View view){
        BaseActivity baseActivity = (BaseActivity) this.getActivity();
        baseActivity.goToActivity(TiXianActivity.class,null);
    }


    @OnClick(R.id.ll_touzi)
    public  void touziOnclick(View view){

        ((BaseActivity)this.getActivity()).goToActivity(LineChartActivity.class,null);

    }

    @OnClick(R.id.ll_touzi_zhiguan)
    public  void zhiguanOnclick(View view){
        ((BaseActivity)this.getActivity()).goToActivity(BarChartAcivity.class,null);

    }

    @OnClick(R.id.ll_zichan)
    public  void zichanOnclick(View view){
        ((BaseActivity)this.getActivity()).goToActivity(PieChartActivity.class,null);

    }



    /**
     * 启动用户信息设置activity
     */
    private void startUserInfoAcrivity() {
        BaseActivity mainActivity = (BaseActivity) getContext();
        mainActivity.goToActivity(UserInfoActivity.class,null);

    }


    @Override
    public void onResume() {
        super.onResume();
        readLocalIcon();

    }

    private boolean readLocalIcon() {

        String externalStorageState = Environment.getExternalStorageState();
        String mediaMounted = Environment.MEDIA_MOUNTED;

        File filesDir ;
        if(externalStorageState.equals(mediaMounted)) {
            filesDir = this.getActivity().getExternalFilesDir("");
        }else {
            filesDir = this.getActivity().getFilesDir();
        }


        
            File file = new File(filesDir, "icon.png");
        if(file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ivMeIcon.setImageBitmap(bitmap);
            return true;
        }
        return false;

    }
}
