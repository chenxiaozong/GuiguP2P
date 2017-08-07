package com.example.chen.guigup2p.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class ProductAdapter extends BaseAdapter {
    private List<Product> productList;


    public ProductAdapter(List<Product> productList) {
        this.productList =productList;
    }

    @Override
    public int getCount() {
        return productList==null?0:productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }


    /**
     * 加载不同类型的item
     * 重写两个方法:
     *  getViewTypeCount()
     *   getItemViewType
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 当item 的position是3 时 ,返回另一种类型的item视图 (textView)
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if(position == 3) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;


        //1. 获取view
        if(view==null) {
            view = View.inflate(viewGroup.getContext(),R.layout.item_product_list,null);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }else {
           viewHolder = (ViewHolder) view.getTag();
        }

        //listview 中实现多种item布局
        int itemViewType = getItemViewType(i);

        if(itemViewType ==1) {

            TextView tv = new TextView(viewGroup.getContext());
            tv.setText("与子同行,奈何舟覆");
            tv.setTextSize(UIUtils.dp2px(20));
            tv.setTextColor(UIUtils.getColor(R.color.title_text));

            return tv;

        }

        if(i>3) {
            i--;
        }
        

        //2. 装配数据
        Product product = productList.get(i);

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
