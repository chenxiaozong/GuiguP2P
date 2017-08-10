package com.example.chen.guigup2p;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chen.guigup2p.activity.BaseActivity;
import com.example.chen.guigup2p.common.ActivityManager;
import com.example.chen.guigup2p.fragment.HomeFragment;
import com.example.chen.guigup2p.fragment.InvestFragment;
import com.example.chen.guigup2p.fragment.MoreFragment;
import com.example.chen.guigup2p.fragment.MyFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.fl_main)
    FrameLayout flMain;

    @Bind(R.id.iv_main_button_home)
    ImageView ivMainButtonHome;

    @Bind(R.id.ll_main_home)
    LinearLayout llMainHome;

    @Bind(R.id.iv_main_button_invest)
    ImageView ivMainButtonInvest;

    @Bind(R.id.ll_main_invest)
    LinearLayout llMainInvest;

    @Bind(R.id.iv_main_button_my)
    ImageView ivMainButtonMy;

    @Bind(R.id.ll_main_my)
    LinearLayout llMainMy;

    @Bind(R.id.iv_main_button_more)
    ImageView ivMainButtonMore;

    @Bind(R.id.ll_main_more)
    LinearLayout llMainMore;



/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将当前activity添加到activityManager 中
        ActivityManager.getInstance().addActivity(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

*//*        //模拟异常
        Log.d("MainActivity--", "模拟异常");
        String str = null;
        if (str.equals("abc")) {
            Log.e("TAG", "abc");
        }*//*

    }*/

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {



        setSelect(1);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    /**
     * tab添加点击事件     *
     *
     * @param view
     */
    @OnClick({R.id.ll_main_home, R.id.ll_main_invest, R.id.ll_main_my, R.id.ll_main_more})
    public void switchFragment(View view) {
        switch (view.getId()) {
            case R.id.ll_main_home:
                setSelect(1);
                break;
            case R.id.ll_main_invest:
                setSelect(2);
                break;
            case R.id.ll_main_my:
                setSelect(3);
                break;
            case R.id.ll_main_more:
                setSelect(4);
                break;

        }
    }

    /**
     * 2. 设置要显示的Fragment
     * <p>
     * >更新button背景图        updateButton(i,oldButton);
     *
     * @param i
     */
    private HomeFragment homefragment;
    private InvestFragment investfragment;
    private MyFragment myfragment;
    private MoreFragment morefragment;

    private int currentfragment = 1;
    private int oldButton = 1;

    private void setSelect(int i) {
        //通过事务生成并调用fragment
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transation = manager.beginTransaction();

        switch (i) {

            case 1://显示第一个fragment
                if (homefragment == null) {
                    homefragment = new HomeFragment();//创建对象后并不会马上调用生命周期方法,而是等commit后才调用生命周期方法
                    transation.add(R.id.fl_main, homefragment);
                }
                checkFragment(i, transation);
                transation.show(homefragment);
                currentfragment = i;
                break;

            case 2://显示第二个fragment
                if (investfragment == null) {
                    investfragment = new InvestFragment();
                    transation.add(R.id.fl_main, investfragment);
                }
                checkFragment(i, transation);
                transation.show(investfragment);
                currentfragment = i;//更新当前fragment

                break;
            case 3://显示第三个fragment

                if (myfragment == null) {
                    myfragment = new MyFragment();
                    transation.add(R.id.fl_main, myfragment);
                }
                checkFragment(i, transation);
                transation.show(myfragment);
                break;
            case 4://显示第四个fragment

                if (morefragment == null) {
                    morefragment = new MoreFragment();
                    transation.add(R.id.fl_main, morefragment);
                }
                checkFragment(i, transation);
                transation.show(morefragment);
                break;

        }
        transation.commit();
        updateButton(i, oldButton);
        //更新索引
        currentfragment = i;
        oldButton = i;

    }

    /**
     * 3. 检测当前显示的fragment 是否是将要显示的fragment --隐藏非当前的fragment
     *
     * @param i
     * @param transation
     */
    private void checkFragment(int i, FragmentTransaction transation) {
        if (i != currentfragment) {
            switch (currentfragment) {
                case 1: //隐藏home
                    transation.hide(homefragment);
                    break;
                case 2: //隐藏invest
                    transation.hide(investfragment);
                    break;
                case 3: //隐藏my
                    transation.hide(myfragment);
                    break;
                case 4: //隐藏more
                    transation.hide(morefragment);
                    break;

            }
        }

    }

    /**
     * 4. 更新按钮状态
     *
     * @param i
     * @param oldButton
     */
    private void updateButton(int i, int oldButton) {

        Log.d("MainActivity", i + "---" + oldButton);
        //根据oldButton 还原 背景
        switch (oldButton) {
            case 1:
                ivMainButtonHome.setImageResource(R.drawable.bottom01);
                break;
            case 2:
                ivMainButtonInvest.setImageResource(R.drawable.bottom03);
                break;
            case 3:
                ivMainButtonMy.setImageResource(R.drawable.bottom05);
                break;
            case 4:
                ivMainButtonMore.setImageResource(R.drawable.bottom07);
                break;
        }

        //根据i 设置button 背景
        switch (i) {
            case 1:
                ivMainButtonHome.setImageResource(R.drawable.bottom02);
                break;
            case 2:
                ivMainButtonInvest.setImageResource(R.drawable.bottom04);
                break;
            case 3:
                ivMainButtonMy.setImageResource(R.drawable.bottom06);
                break;
            case 4:
                ivMainButtonMore.setImageResource(R.drawable.bottom08);
                break;
        }


    }


    /**
     * 5. 连续点击两次退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    private boolean isFirsClick = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                isFirsClick = false;
            }

        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isFirsClick) {//如果是第一次点击
                Toast.makeText(this, "再点击一次退出应用", Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(1,2000);
                isFirsClick = true;
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
