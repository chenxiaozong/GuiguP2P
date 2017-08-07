package com.example.chen.guigup2p.ui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.util.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by chen on 2017/8/5.
 * 1. 记录四中不同显示状态
 * 2. 提供四中页面--对应四中显示状态
 */

public abstract class LoadingPager extends FrameLayout {

    private  Context mContext;

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
        this.mContext = context;
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
            //因为四个具体fragment返回的成功页面不一样，因此没法具体提供一个view
            view_success = UIUtils.getView(layoutId());
            ////加载布局使用的时Application
            view_success = View.inflate(mContext,layoutId(),null);//

            addView(view_success,params);
        }

        view_success.setVisibility(state_current == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);

    }

    public  abstract  int layoutId(); //使用抽象方法，为四个不同fragment提供各自的成功的返回页面




    private  ResultState resultState; //封装响应结果

    /**
     * showe --根据联网请求结果， 确定要显示哪个loadingpage页面
     * 1. 使用异步联网请求
     */
     public void  show(){

         String url = url(); //获取联网操作的url

         //1. fragment 不需要联网
         if(TextUtils.isEmpty(url)) {
             //不需要联网
             resultState = ResultState.SUCCESS;
             resultState.setContent("");

             loadImage();
             return;
         }

         //2. fragment需要联网


         UIUtils.getHandler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 //若 模拟联网延时,将下列联网操作放到此处


         //-------------联网操作(开始)-------------
         AsyncHttpClient client = new AsyncHttpClient();
         client.get(url(),params(),new AsyncHttpResponseHandler(){
             @Override
             public void onSuccess(String content) {
                 //1. 请求成功，内容为空
                 if(TextUtils.isEmpty(content)) { //content=null或者content=""
//                     state_current = STATE_EMPTY;
                     resultState = ResultState.EMPTY;
                     resultState.setContent("");
                 }else {
                     //2. 请求成功，内容不为空
                     //state_current = STATE_SUCCESS;
                     resultState = ResultState.SUCCESS;
                     resultState.setContent(content);
                 }

                 //showSafePage();
                 loadImage();

             }


             @Override
             public void onFailure(Throwable error, String content) {
                 //state_current = STATE_ERR;
                 resultState = ResultState.ERROR;//使用枚举类
                 resultState.setContent("");
                 //showSafePage();// 保证在主线程中执行
                 loadImage();
             }
         });
         //-------------联网操作(结束)----------------------------------
             }
         }, 2000);


     }

    /**
     * 更新state_current 的值
     *
     * 根据联网请求后的 > 枚举类的值resultState -> current_state
     */
    private void loadImage() {
        switch (resultState){
            case ERROR:
                state_current = STATE_ERR;
                break;
            case EMPTY:
                state_current = STATE_EMPTY;

                break;
            case SUCCESS:
                state_current = STATE_SUCCESS;
                break;
        }

        showSafePage();//根据修改后的state_current 的值， 更新视图显示

        //如果state_current = success
        // > 需要传出联网请求获取的数据 content
        // > 传出对应的视图view_success
        if(state_current==STATE_SUCCESS) {
            onSuccess(resultState,view_success);
        }


    }

    /**
     * 当联网请求成功 && 得到响应内容 content
     * 1. 传出resultState.content
     * 2. 传出响应成功对应的视图： view_success: --根据四个fragment而定
     * @param resultState
     * @param view_success
     */
    protected abstract void onSuccess(ResultState resultState, View view_success);

    /**
     * 请求参数
     * @return
     */
    protected abstract RequestParams params();

    /**
     * 请求地址
     * @return
     */
    protected abstract String url();


    /**
     * 使用枚举类型封装网络请求返回的（数据和状态）
     */
    public  enum  ResultState {

        //public  static final ResultState ERROR = new ResultState(2);

        ERROR(2),EMPTY(3),SUCCESS(4);
        int state;
        ResultState(int state) {
            this.state = state;
        }

        //封装联网响应
        private  String content;


        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }






}
