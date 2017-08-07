package com.example.chen.guigup2p.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by chen on 2017/8/7.
 * textview 实现跑马灯效果
 */

public class MarQuenTextView extends TextView {
    public MarQuenTextView(Context context) {
        super(context);
    }

    public MarQuenTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarQuenTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return  true;
    }
}
