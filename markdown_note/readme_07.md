[TOC]

抽取baseFragment

## 1. 创建baseFragment集成Fragment (v4包)

```java
/**
 * Created by chen on 2017/8/5.
 */

public abstract class BaseFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       // View view = UIUtils.getView(getLayoutId());// 使用自定义UIUtils 工具类加载view视图
        View view = View.inflate(getContext(),getLayoutId(), null);
        ButterKnife.bind(this, view);

        initTitle();

        initData();
        return view;
    }

    protected abstract int  getLayoutId();

    protected abstract  void initData() ;

    protected abstract void initTitle();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
```


## 2. HomeFragment

```java


/*  抽取BaseFragment

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View view = UIUtils.getView(R.layout.fragment_home); 使用自定义UIUtils 工具类加载view视图
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);

        initTitle();

        initData();
        return view;
    }*/

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    /**
     * 初始化数据:联网获取数据
     * 1. 使用 android-async-http-master.jar 框架联网
     * 2. 使用app/libs/fastjson-1.2.4.jar 框架解析json数据
     */
    public void initData() {
        String url = AppNetConfig.INDEX; //首页面url

        Log.d("HomeFragment", url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                //解析请求到的json格式数据
                parseJsonWithFJ(content);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                Toast.makeText(getContext(), "联网失败:" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 1. 初始化标题
     */
    public void initTitle() {
        ivTopTitleBack.setVisibility(View.INVISIBLE);
        tvTopTitleTap.setText("首页");
        ivTopTitleSetting.setVisibility(View.INVISIBLE);
    }


```




## 3. 其它Fragment 以 investFragment为例

```java
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData() {

    }

    /**
     * 1. 初始化标题
     */
    protected void initTitle() {
        ivTopTitleBack.setVisibility(View.GONE);
        tvTopTitleTap.setText("投资");
        ivTopTitleSetting.setVisibility(View.GONE);
    }

/*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }*/
```

