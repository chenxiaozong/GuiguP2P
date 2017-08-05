package com.example.chen.guigup2p.ui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.util.UIUtils;

/**
 * Created by chen on 2017/8/5.
 * 1. 记录四中不同显示状态
 * 2. 提供四中页面--对应四中显示状态
 */

public abstract class LoadingPager extends FrameLayout {


    //记录四中不同的显示状态
    private static final int STATE_LOADING = 1;
    private static final int STATE_ERR = 2;
    private static final int STATE_EMPTY = 3;
    private static final int STATE_SUCCESS = 4;

    //当前显示状态--根据联网结果而定
    private int state_current = STATE_LOADING; //默认情况下当前状态为正在加载


    //2. 提供四种不同显示界面
    private View view_loading;
    private View view_err;
    private View view_empty;
    private View view_success;


    private LayoutParams params;


    public LoadingPager(@NonNull Context context) {
        this(context, null);
        Log.d("LoadingPager", "---1");
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        Log.d("LoadingPager", "---2");
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("LoadingPager", "---3");
        init();//初始化视图界面方法
    }


    /**
     * 实例化view
     *
     * 1. 提供布局显示参数
     * 2. 加载视图
     * 3. 添加视图到布局中
     */
    private void init() {

        //使视图填充父视图
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if (view_loading == null) {
            //2. 加载布局
            view_loading = UIUtils.getView(R.layout.page_loading);
            //3. 添加到framelayout 中
            addView(view_loading, params);//调用framelayout 的addview方法

        }

        if (view_err == null) {
            view_err = UIUtils.getView(R.layout.page_error);
            addView(view_err, params);
        }
        if (view_empty == null) {
            view_empty = UIUtils.getView(R.layout.page_empty);
            addView(view_empty, params);

        }

        showSafePage();//保证其中的操作在主线程执行

    }

    /**
     * 保证主线程执行：更新界面
     */
    private void showSafePage() {
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //保证run（） 方法在主线程执行
                showPage();
            }
        });
    }

    /**
     * 显示相应load界面:根据stata_current 决定要显示哪个页面
     */
    private void showPage() {
        view_loading.setVisibility(state_current==STATE_LOADING?View.VISIBLE:View.INVISIBLE);
        view_empty.setVisibility(state_current == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        view_err.setVisibility(state_current == STATE_ERR ? View.VISIBLE : View.INVISIBLE);

        if(view_success == null) {
            view_success = UIUtils.getView(layoutId()); //因为四个具体fragment返回的成功页面不一样，因此没法具体提供一个view
            addView(view_success,params);
        }

        view_success.setVisibility(state_current == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);

    }

    public  abstract  int layoutId(); //使用抽象方法，为四个不同fragment提供各自的成功的返回页面

}
