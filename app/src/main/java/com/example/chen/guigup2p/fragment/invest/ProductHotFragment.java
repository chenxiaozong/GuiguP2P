package com.example.chen.guigup2p.fragment.invest;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.fragment.BaseFragment;
import com.example.chen.guigup2p.ui.flowlayout.FlowLayout;
import com.example.chen.guigup2p.util.DrawUtils;
import com.example.chen.guigup2p.util.UIUtils;
import com.loopj.android.http.RequestParams;

import java.util.Random;

import butterknife.Bind;


/**
 * Created by chen on 2017/8/6.
 */

public class ProductHotFragment extends BaseFragment {

    @Bind(R.id.flow_hot)
    FlowLayout flowHot;

    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

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
        return R.layout.fragment_product_hot;
    }

    @Override
    protected void initData(String content) {
        /**
         * 将datas 中的数据解析到每个单独的textview  并天添加到FlowLayout 中
         */

        Random random = new Random();
        for (int i = 0; i < datas.length; i++) {

            final TextView tv = new TextView(getContext());


            //1. 设置文本
            tv.setText(datas[i]);


            //2设置属性
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(5);
            mp.rightMargin = UIUtils.dp2px(5);
            mp.topMargin = UIUtils.dp2px(5);
            mp.bottomMargin = UIUtils.dp2px(5);

            tv.setLayoutParams(mp);//设置边距

            int padding = UIUtils.dp2px(5);
            tv.setPadding(padding, padding, padding, padding);//设置内边距
            tv.setTextSize(UIUtils.dp2px(12));

            //3. 设置颜色

            int red = random.nextInt(211);
            int green = random.nextInt(211);
            int blue = random.nextInt(211);
            tv.setTextColor(Color.rgb(red,green,blue));


            // tv.setBackgroundResource(R.drawable.hot_product_shap);
            // 使用自定义DrawableUtils 代替shape
            //使用GradientDrawable替换shape设置TextView
            red = random.nextInt(200);
            green = random.nextInt(200);
            blue = random.nextInt(200);
            tv.setBackground(DrawUtils.getDrawable(Color.rgb(red,green,blue),UIUtils.dp2px(5)));

            //使用StateListDrawable替换selector设置TextView
            // tv 设置点击效果
            //设置textView是可点击的.如果设置了点击事件，则TextView就是可点击的。
            tv.setClickable(true);
            tv.setBackground(DrawUtils.getSelector(DrawUtils.getDrawable(Color.rgb(red, green, blue), UIUtils.dp2px(5)), DrawUtils.getDrawable(Color.WHITE, UIUtils.dp2px(5))));


            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIUtils.toast(tv.getText().toString(),false);
                }
            });

            //4. 添加到FlowLayout中
            flowHot.addView(tv);
        }


    }

    @Override
    protected void initTitle() {

    }
}
