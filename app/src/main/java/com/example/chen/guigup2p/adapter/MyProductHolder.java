package com.example.chen.guigup2p.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.bean.Product;
import com.example.chen.guigup2p.ui.RoundProgress;
import com.example.chen.guigup2p.util.UIUtils;

import butterknife.Bind;

/**
 * Created by chen on 2017/8/7.
 */

public class MyProductHolder extends BaseHolder<Product> {

    @Bind(R.id.p_name)
    TextView pName;
    @Bind(R.id.p_money)
    TextView pMoney;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.p_suodingdays)
    TextView pSuodingdays;
    @Bind(R.id.p_minzouzi)
    TextView pMinzouzi;
    @Bind(R.id.p_minnum)
    TextView pMinnum;
    @Bind(R.id.p_progresss)
    RoundProgress pProgresss;


    @Override
    protected View initView() {
        View view  = View.inflate(UIUtils.getContext(), R.layout.item_product_list,null);

        return view;

    }

    @Override
    protected void refreshData() {
        Product data = this.getData();

        //装数据
        pMinnum.setText(data.memberNum);
        pMinzouzi.setText(data.minTouMoney);
        pMoney.setText(data.money);
        pName.setText(data.name);
        pProgresss.setRoundProgress(Float.parseFloat(data.progress));
        pSuodingdays.setText(data.suodingDays);
        pYearlv.setText(data.yearRate);

    }
}
