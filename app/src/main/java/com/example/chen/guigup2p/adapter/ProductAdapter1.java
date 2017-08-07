package com.example.chen.guigup2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.bean.Product;
import com.example.chen.guigup2p.ui.RoundProgress;
import com.example.chen.guigup2p.util.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/8/7.
 */

public class ProductAdapter1 extends  MyBaseAdapter1<Product> {

    public ProductAdapter1(List<Product> list) {
        super(list);
    }

    @Override
    public View setMyView(int i, View view, ViewGroup viewGroup) {
        ProductAdapter.ViewHolder viewHolder ;


        //1. 获取view
        if(view==null) {
            view = View.inflate(viewGroup.getContext(),R.layout.item_product_list,null);

            viewHolder = new ProductAdapter.ViewHolder(view);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ProductAdapter.ViewHolder) view.getTag();
        }

        //2. 装配数据
        Product product = list.get(i);

        viewHolder.pMinnum.setText(product.memberNum);
        viewHolder.pMinzouzi.setText(product.minTouMoney);
        viewHolder.pMoney.setText(product.money);
        viewHolder.pName.setText(product.name);

        float roundProgress = Float.parseFloat(product.progress);
        viewHolder.pProgresss.setRoundProgress(roundProgress);
        viewHolder.pSuodingdays.setText(product.suodingDays);
        viewHolder.pYearlv.setText(product.yearRate);

        return view;
    }

    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
