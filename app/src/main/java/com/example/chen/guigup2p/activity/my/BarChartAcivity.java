package com.example.chen.guigup2p.activity.my;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.activity.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class BarChartAcivity extends BaseActivity {

    @Bind(R.id.iv_top_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTitle;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTitleSetting;

    @Bind(R.id.bar_chart)
    BarChart barChart;

    private Typeface mTf;


    @Override
    protected void initData() {



        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");


        // apply styling
        barChart.setDescription("");//图表描述
        barChart.setDrawGridBackground(false);//图表背景
        barChart.setDrawBarShadow(false);//是否绘制阴影

        //获取 X轴
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        //获取左侧Y轴对象
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(20f);

        //获取右侧Y轴对象
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(20f);

        //柱状图数据:
        BarData barData = generateDataBar(1);

        barData.setValueTypeface(mTf);

        // set data
        barChart.setData((BarData) barData);

        // do not forget to refresh the chart
    //barChart.invalidate();
        barChart.animateY(700);

    }



    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
        tvTitle.setText("柱状图");
    }

    @OnClick(R.id.iv_top_title_back)
    public  void backOnclick(View view){
        removeCurrentActivity();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_bar_chart_acivity;
    }




    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);//数据描述
        d.setBarSpacePercent(20f);//设置柱状图数据 距离
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);//设置颜色
        d.setHighLightAlpha(255);//设置高亮透明度
        d.setDrawValues(false);//是否显示数据值

        BarData cd = new BarData(getMonths(), d);
        return cd;
    }


    private ArrayList<String> getMonths() {

        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");

        return m;
    }

}
