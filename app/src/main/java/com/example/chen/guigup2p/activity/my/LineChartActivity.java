package com.example.chen.guigup2p.activity.my;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.activity.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class LineChartActivity extends BaseActivity {

    @Bind(R.id.iv_top_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTitle;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTitleSetting;

    @Bind(R.id.line_chart)
    LineChart lineChart;


    //字体
    private Typeface mTf;


    @Override
    protected void initData() {

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");


        // apply styling
        // holder.chart.setValueTypeface(mTf);
        //设置图表描述
        lineChart.setDescription("我的资产");

        //绘制网格背景
        lineChart.setDrawGridBackground(false);


        //获取图表X 轴
        XAxis xAxis = lineChart.getXAxis();

        //x 轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //字体
        xAxis.setTypeface(mTf);
        //x轴网格线
        xAxis.setDrawGridLines(false);
        //是否绘制x 轴
        xAxis.setDrawAxisLine(true);


        //获取 左Y 轴
        YAxis leftAxis = lineChart.getAxisLeft();
        //Y轴字体
        leftAxis.setTypeface(mTf);
        //Y轴的区间个数 参数1:区间个数,参数2: 区间是否平分(false:均匀,true:不均匀)
        leftAxis.setLabelCount(15, false);

        //获取右侧 Y 轴
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5, false);
        rightAxis.setDrawGridLines(false);

        // set data
        LineData lineData = generateDataLine(1);
        lineChart.setData((LineData) lineData);

        // do not forget to refresh the chart
        //lineChart.invalidate();
        lineChart.animateX(750);//设置动画时间


    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
        tvTitle.setText("折线图");
    }

    @OnClick(R.id.iv_top_title_back)
    public void backOnclick(View view) {
        removeCurrentActivity();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_line_chart;
    }


    /**
     * generates a random ChartData object with just one DataSet
     * 提供折线数据: 将服务器数据转换为绘制图表需要的数据类型
     *
     * @return
     */
    private LineData generateDataLine(int cnt) {

        //折线1
        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e1.add(new Entry((int) (Math.random() * 65) + 40, i));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");
        d1.setLineWidth(2.5f);
        d1.setCircleSize(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);//数据标注


        //折线二
        ArrayList<Entry> e2 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e2.add(new Entry(e1.get(i).getVal() - 30, i));
        }

        LineDataSet d2 = new LineDataSet(e2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleSize(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);
        sets.add(d2);

        LineData cd = new LineData(getMonths(), sets);
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
