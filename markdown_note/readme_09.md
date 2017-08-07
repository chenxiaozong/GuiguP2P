投资产品中的listview

[TOC]


# 1. 实现理财产品中全部理财产品的listview

> 效果

![Image Title](../markdown_image/09_1listview.png) 

>步骤: 

①ListView 
②Item Layout 
③集合数据 (联网获取数据）
④BaseAdapter


## 1. 编写ListView 布局文件
> 全部理财产品界面布局 添加listview: layout/fragment_product_list.xml



```xml
//跑马灯
    <com.example.chen.guigup2p.ui.MarQuenTextView
        android:id="@+id/tv_product_list"
        android:textSize="20sp"
        android:text="好运当头,尚硅谷金融,首投返现,最高到1888元"
        android:textColor="@color/product_red_common"

        android:singleLine="true"
        android:ellipsize="marquee"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <ListView
        android:id="@+id/lv_productlist"



        android:divider="@color/title_text"
        android:dividerHeight="5dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </ListView>
```



## 2. 编写listview item 布局文件
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/white"
    android:orientation="vertical">

    <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="@color/divider_line"/>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        android:orientation="vertical">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:padding="15dp">

            <TextView
                android:id="@+id/p_name"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="超级新手计划"
                android:textColor="#333333"
                android:textSize="16sp"/>

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:src="@drawable/right"/>
        </LinearLayout>

        <View
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:layout_marginLeft="15dp"
            android:layout_marginRight="15dp"
            android:background="@color/divider_line"/>
    </LinearLayout>


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:padding="15dp">


        <LinearLayout
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:orientation="vertical">


            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="left"
                    android:text="产品金额"
                    android:textColor="@color/product_detail_common"
                    android:textSize="12sp"/>

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="center"
                    android:text="预期年利率"
                    android:textColor="@color/product_detail_common"
                    android:textSize="12sp"/>

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="right"
                    android:text="锁定期"
                    android:textColor="@color/product_detail_common"
                    android:textSize="12sp"/>
            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="5dp"
                android:orientation="horizontal">


                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="left">

                    <TextView
                        android:id="@+id/p_money"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="10"
                        android:textColor="@color/product_red_common"
                        android:textSize="14sp"
                        android:textStyle="bold"/>

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="万"
                        android:textColor="@color/product_detail_common"
                        android:textSize="12sp"/>
                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="center">

                    <TextView
                        android:id="@+id/p_yearlv"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="8.00"
                        android:textColor="@color/product_red_common"
                        android:textSize="14sp"
                        android:textStyle="bold"/>

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="%"
                        android:textColor="@color/product_detail_common"
                        android:textSize="12sp"/>
                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="right">

                    <TextView
                        android:id="@+id/p_suodingdays"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="30"
                        android:textColor="@color/product_red_common"
                        android:textSize="14sp"
                        android:textStyle="bold"/>

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="天"
                        android:textColor="@color/product_detail_common"
                        android:textSize="12sp"/>
                </LinearLayout>
            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="5dp"
                android:orientation="horizontal">

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="left">

                    <TextView
                        android:layout_width="1dp"
                        android:layout_height="12dp"
                        android:background="@color/divider_line"/>

                    <TextView
                        android:id="@+id/p_minzouzi"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginLeft="4dp"
                        android:drawableLeft="@drawable/manage_list_count"
                        android:drawablePadding="4dp"
                        android:text="100起"
                        android:textColor="@color/product_detail_common"
                        android:textSize="12sp"/>
                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="center">

                    <TextView
                        android:layout_width="1dp"
                        android:layout_height="12dp"
                        android:background="@color/divider_line"/>

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginLeft="4dp"
                        android:drawableLeft="@drawable/manage_list_guarantee"
                        android:drawablePadding="4dp"
                        android:text="本息保障"
                        android:textColor="@color/product_detail_common"
                        android:textSize="12sp"/>
                </LinearLayout>

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:gravity="right">

                    <TextView
                        android:layout_width="1dp"
                        android:layout_height="12dp"
                        android:background="@color/divider_line"/>

                    <TextView
                        android:id="@+id/p_minnum"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginLeft="4dp"
                        android:drawableLeft="@drawable/manage_list_member"
                        android:drawablePadding="4dp"
                        android:text="100"
                        android:textColor="@color/product_detail_common"
                        android:textSize="12sp"/>
                </LinearLayout>
            </LinearLayout>
        </LinearLayout>

        <com.example.chen.guigup2p.ui.RoundProgress
            android:id="@+id/p_progresss"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:layout_marginLeft="20dp"

            app:roundColor="@android:color/darker_gray"
            app:roundWidth="5dp"
            app:textColor="#18b4ed"
            app:textSize="12sp">

        </com.example.chen.guigup2p.ui.RoundProgress>




    </LinearLayout>

    <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="@color/divider_line"/>
</LinearLayout>
```

> item 布局文件效果
![Image Title](../markdown_image/09_2item_layout.png) 

## 3. 理财产品数据(联网获取)
> ProductListFragment.java  中的initDate() 获取联网数据 

```java

    @Override
    protected void initData(String content) {
      
        //3. 解析联网请求的数据

        JSONObject jsonObject = JSON.parseObject(content);

        Boolean success = jsonObject.getBoolean("success");

        if(success) {//获取数据成功
            String data = jsonObject.getString("data");
            productslist = JSON.parseArray(data,Product.class);
            //为listvie 设置adapter
            lv_productlist.setAdapter(new ProductAdapter(productslist));
        }
    }

```




## 4. 创建Adapter
> 理财产品listview 的适配器


```java
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

```




# 2. 实现listvew 中显示多种item视图
>需求: 当positon==3 时 显示一个text文本 


## 1. Adapter中重写 两个方法:
```java
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
Multi-line Code
```


## 2. getView中根据itemType 确定要返回的item视图
```java

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

Multi-line Code
```


![Image Title](../markdown_image/09_3item_style.png) 



# 3. 抽取Adapter

## 1. 方式一 :抽取Adapter 但是抽取力度小

### 1. 创建MyBaseAdapter1 继承BaseAdapter

> 注意:
- MyBaseAdapter1<T>   :T 代表不同数据类型
- public List<T> list;
- getView() 提供抽象方法,实现不同adapter加载不同视图


```java

public abstract class MyBaseAdapter1<T> extends BaseAdapter {
    public List<T> list;

    public MyBaseAdapter1(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();

    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = setMyView(i, view, viewGroup);
        return convertView;
    }

    //抽象方法,根据不同list数据继承者重写
    public abstract View setMyView(int i, View view, ViewGroup viewGroup);
}
Multi-line Code
```


### 2. 创建ProductAdapter1 继承MyBaseAdapter(抽取的adapter)

> 只需要重写 

- setMyView(int i, View view, ViewGroup viewGroup) 
- ProductAdapter1
- viewHolder 

```java

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
Multi-line Code
```


### 3. ProductListFragment 中的initDate 为listview 设置adapter

```java
            ProductAdapter1  adapter1 = new ProductAdapter1(productslist);
            lv_productlist.setAdapter(adapter1);
```



## 2. 方式二: 抽取holder和adapter

### 1. 创建BaseAdapter 和BaseHolder()

> 创建BaseAdapter (MyBaseAdapter2)

```java
/**
 * Created by chen on 2017/8/7.
 * 抽取方法2 :抽取adapter和holder
 *
 */

public abstract class MyBaseAdapter2<T> extends  BaseAdapter{


    public List<T> list ;

    public MyBaseAdapter2(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    //将具体的集合数据装配到具体的一个item layout中
    //问题一：item layout的布局是不确定的。
    //问题二：将集合中指定位置的数据装配到item，是不确定的。
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        BaseHolder holder ;

        if(view==null) {
            holder =getHolder();
        }else {
            holder = (BaseHolder) view.getTag();
        }
        //装配数据
        T t = list.get(i);
        holder.setData(t);
        //返回视图
        return holder.getRootView();
    }
    public abstract BaseHolder getHolder() ;
}
```


> 创建BaseHolder 

```java
abstract class BaseHolder<T> {


    private View rootView;

    private T data;

    public BaseHolder(){
        rootView = initView();
        rootView.setTag(this);
        ButterKnife.bind(this,rootView);
    }

    //提供item的布局
    protected abstract View initView();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        refreshData();
    }
    //装配过程: 不同视图不一样,需要抽象方法
    protected abstract void refreshData();

    public View getRootView() {
        return rootView;
    }

}

Multi-line Code
```

### 2. 创建Product 对应的holder和adapter 继承上列baseholder 和baseAdapter

> MyProductHolder

```java
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


Multi-line Code
```

> MyProductAdapter

```java
/**
 * Created by chen on 2017/8/7.
 */

public class ProductAdapter2 extends MyBaseAdapter2<Product> {

    public ProductAdapter2(List<Product> list) {
        super(list);
    }

    @Override
    public BaseHolder getHolder() {
        return new MyProductHolder();
    }
}
```

### 3. ProductListFragment 中的initDate 为listview 设置adapter
> initData() 中为listview 设置adapter

```java

            //方式三：抽取了，最好的方式.（可以作为选择）
            ProductAdapter2 productAdapter2 = new ProductAdapter2(productslist);
            lv_productlist.setAdapter(productAdapter2);//显示列表
```



