package com.example.chen.guigup2p.util;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.chen.guigup2p.common.MyApplication;

/**
 * Created by chen on 2017/4/13.
 * 专门提供为处理一些UI相关的问题而创建的工具类，
 * 提供资源获取的通用方法，避免每次都写重复的代码获取结果。
 */

public class UIUtils {
    public static Context getContext(){
        return MyApplication.context;
    }

    public static Handler getHandler(){
        return  MyApplication.handler;
    }

    //返回指定colorId对应的颜色值
    public static int getColor(int colorId){
        return getContext().getResources().getColor(colorId);
    }

    //加载指定viewId的视图对象，并返回
    public static View getView(int viewId){
        return View.inflate(getContext(),viewId,null);
    }

    //返回指定stringArrayId的数组
    public static String[] getStringArr(int arrId){
        return getContext().getResources().getStringArray(arrId);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);//四舍五入

    }

    public static int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);//四舍五入

    }

    /**
     * 保证runnable操作在主线程执行
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if(isMainThread()) {
            runnable.run();
        }else {
            UIUtils.getHandler().post(runnable);
        }
    }

    /**
     * 判断当前线程是否是主线程
     * @return
     */
    private static boolean isMainThread() {
        int currentTid = android.os.Process.myTid(); //当前线程ID
        int mainTid = MyApplication.mainThreadId; //主线程ID

        return  currentTid==mainTid;

    }



    //toas工具
    public static void toast(String message,boolean isLengthLong){
        Toast.makeText(UIUtils.getContext(), message,isLengthLong? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
