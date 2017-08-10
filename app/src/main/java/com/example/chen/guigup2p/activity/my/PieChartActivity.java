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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class PieChartActivity extends BaseActivity{

    @Bind(R.id.iv_top_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTitle;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTitleSetting;

    @Bind(R.id.pie_chart)
    PieChart pieChart;

    //字体
    private Typeface mTf;


    @Override
    protected void initData() {

    mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        // apply styling
        //设置描述
        pieChart.setDescription("");
        pieChart.setHoleRadius(32f); //中心半径
        pieChart.setTransparentCircleRadius(37f); //中心园的包裹半径
        pieChart.setCenterText("MPChart\nAndroid"); //圆心描述
        pieChart.setCenterTextTypeface(mTf); //字体
        pieChart.setCenterTextSize(12f); //中心文字大小

        //使用百分比: true: 各部分相加等于100
        pieChart.setUsePercentValues(true);


        //生成饼状图数据
        PieData pieData = generateDataPie(1);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTypeface(mTf);
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.WHITE);
        // set data
        pieChart.setData((PieData) pieData);

        //设置图例描述:
        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);//设置图例位置
        l.setYEntrySpace(0f);//图例条目间距
        l.setYOffset(0f); //图例距顶部位置

        // do not forget to refresh the chart
        // pieChart.invalidate();
        pieChart.animateXY(900, 900);


    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
        tvTitle.setText("饼状图");
    }

    @OnClick(R.id.iv_top_title_back)
    public  void backOnclick(View view){
        removeCurrentActivity();
    }






    @Override
    protected int getLayoutId() {
        return R.layout.activity_pie_chart;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie(int cnt) {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new Entry((int) (Math.random() * 70) + 30, i));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);//设置圆饼间距
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(getQuarters(), d);
        return cd;
    }

    private ArrayList<String> getQuarters() {

        ArrayList<String> q = new ArrayList<String>();
        q.add("1st Quarter");
        q.add("2nd Quarter");
        q.add("3rd Quarter");
        q.add("4th Quarter");

        return q;
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
