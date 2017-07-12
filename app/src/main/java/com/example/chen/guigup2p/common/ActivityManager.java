package com.example.chen.guigup2p.common;

/**
 * Created by chen on 2017/4/13.
 * 统一应用程序中所有的Activity的栈管理
 * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
 */

import android.app.Activity;

import java.util.Stack;


public class ActivityManager {


    /**
     * 单例模式(两种)：饿汉式-开始就造好 提供公共方法访问
     * 1. 私有化构造器
     * 2. 提供私有的,静态的, 当前对象
     * 3. 提供一个公用的方法访问
     *
     */

    private ActivityManager(){}
    private static ActivityManager activityManager = new ActivityManager();

    public static ActivityManager getInstance(){
        return activityManager;
    }

    //提供栈对象
    private Stack<Activity> activityStack = new Stack<>();


    //添加activity 到栈空间
    public void addActivity(Activity activity){
        if(activity!=null) {
            activityStack.add(activity);
        }
    }

    //删除同一个类创建的所有Activity
    public void removeActivity(Activity activity){

        if(activity!=null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity currentActivity = activityStack.get(i);

                if (currentActivity.getClass().equals(activity.getClass())) {
                    currentActivity.finish(); //销毁当前Activity
                    activityStack.remove(i); //从栈空间移除
                    break;
                }
            }
        }
    }

    //删除当前Activiy
    public void removeCurrent(){
        /*方式一:
        Activity activity = activityStack.get(activityStack.size() - 1);//获取栈顶元素
        activity.finish();//销毁
        activityStack.remove(activityStack.size()-1);
        */


        //方式二:
        Activity activity = activityStack.lastElement();
        activity.finish();//销毁
        activityStack.remove(activity);//移除
    }

    //删除所有Activiy
    public void removeAll(){
        for(int i = activityStack.size() - 1; i >= 0 ; i--) {
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
            //activityStack.remove(i);
        }
    }

    //返回栈大小
    public int size(){
        return activityStack.size();
    }
}
