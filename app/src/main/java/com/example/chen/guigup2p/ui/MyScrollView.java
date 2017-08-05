package com.example.chen.guigup2p.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by chen on 2017/8/5.
 */

public class MyScrollView extends ScrollView {

    private View childview;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            childview = getChildAt(0);
        }

    }


    private int lastY; //记录上一次y轴方向的距离
    private Rect normal = new Rect();//记录边界位置的childview 的坐标


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (childview == null) {

            return super.onTouchEvent(ev);

        }

        int eventY = (int) ev.getY();//获取事件坐标

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MyScrollView", "down");
                lastY = eventY;

                break;


            case MotionEvent.ACTION_MOVE:
                Log.d("MyScrollView", "move");
                int dy = eventY - lastY; //计算微小的移动量

                if (isNeedMove()) {
                    if(normal.isEmpty()) {//记录临界位置的左上右下
                        normal.set(childview.getLeft(),childview.getTop(),childview.getRight(),childview.getBottom());
                    }
                    childview.layout(childview.getLeft(),childview.getTop()+dy/2,childview.getRight(),childview.getBottom()+dy/2);
                }

                lastY = eventY; //重新赋值
                break;


            case MotionEvent.ACTION_UP:
                Log.d("MyScrollView", "up");
                //1. 使用平移动画 还原视图
                int translateY = childview.getBottom()-normal.bottom;

                TranslateAnimation translate = new TranslateAnimation(0,0,0,-translateY);
                translate.setDuration(1000);
                translate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        childview.clearAnimation();
                        childview.layout(normal.left,normal.top,normal.right,normal.bottom);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                childview.startAnimation(translate);
                break;


        }


        return super.onTouchEvent(ev);

    }

    /**
     * 判断scroll子视图的移动是否超出边界
     *
     * @return
     */
    private boolean isNeedMove() {
        int scrollY = this.getScrollY();//获取scroll的偏移量

        int childHight = childview.getMeasuredHeight();//获取子视图高度

        int dy = childHight - this.getMeasuredHeight();// dy = childhight - scrollHight

        if (scrollY <= 0 || scrollY >= dy) {//1. child向上移动超出边界  2. child 向下移动超出边界
            return true; // 超出边界 --使用自定义scroll方式处理
        }
        return false;// 使用默认scrollview 方式处理
    }
}
