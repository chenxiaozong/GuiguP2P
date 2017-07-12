[TOC]

## 1. App软件框架搭建
![软件首页](../markdown_image/01_app_home.gif)

### 1.0软件基本架构
![软件基本架构](../markdown_image/01_app_framwork.bmp)

- - -

### 1.1创建MainActivity并设置布局文件
布局文件如下:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
        <FrameLayout

            android:id="@+id/fl_main"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            >
        </FrameLayout>

        <include  layout="@layout/buttom_button_main"/>
    </LinearLayout>

```
>其中: buttom_button_main.xml文件如下:(此处底部tab使用layout+image+text  可使用radiobutton更为简单)

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="55dp"
    android:orientation="vertical">

    <TextView
        android:background="@color/home_back_selected"
        android:layout_width="match_parent"
        android:layout_height="1dp" />
    <LinearLayout
        android:paddingTop="2dp"
        android:layout_width="match_parent"
        android:layout_height="54dp"
        android:orientation="horizontal">
        <LinearLayout
            android:id="@+id/ll_main_home"
            android:gravity="center"
            android:orientation="vertical"
            android:layout_weight="1"
            android:layout_width="50dp"

            android:layout_height="50dp">
            <ImageView
                android:id="@+id/iv_main_button_home"
                android:src="@drawable/bottom01"
                android:layout_width="30dp"
                android:layout_height="30dp" />
            <TextView
                android:layout_marginTop="2sp"
                android:text="首页"
                android:textSize="14sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
        </LinearLayout>

        <LinearLayout

            android:id="@+id/ll_main_invest"
            android:layout_weight="1"
            android:gravity="center"
            android:orientation="vertical"
            android:layout_width="50dp"
            android:layout_height="50dp">
            <ImageView
                android:id="@+id/iv_main_button_invest"
                android:src="@drawable/bottom03"
                android:layout_width="30dp"
                android:layout_height="30dp" />
            <TextView
                android:layout_marginTop="2sp"
                android:text="投资"
                android:textSize="14sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
        </LinearLayout>

        <LinearLayout

            android:id="@+id/ll_main_my"
            android:gravity="center"
            android:orientation="vertical"
            android:layout_weight="1"
            android:layout_width="50dp"
            android:layout_height="50dp">
            <ImageView
                android:id="@+id/iv_main_button_my"

                android:src="@drawable/bottom05"
                android:layout_width="30dp"
                android:layout_height="30dp" />
            <TextView
                android:layout_marginTop="2sp"
                android:text="我的"
                android:textSize="14sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
        </LinearLayout>
        <LinearLayout

            android:id="@+id/ll_main_more"
            android:layout_weight="1"
            android:gravity="center"
            android:orientation="vertical"
            android:layout_width="50dp"
            android:layout_height="50dp">
            <ImageView
                android:id="@+id/iv_main_button_more"
                android:src="@drawable/bottom07"
                android:layout_width="30dp"
                android:layout_height="30dp" />
            <TextView
                android:layout_marginTop="2sp"
                android:text="更多"
                android:textSize="14sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
        </LinearLayout>
    </LinearLayout>
</LinearLayout>

```

- - - -


### 1.2 创建四个对应的Fragment 并加载对应的四布局文件
![Fragment](../markdown_image/01_main_fragments.png)
1) HomeFragment对应类
>com.example.chen.guigup2p.fragment.HomeFragment

```
/**

 * 1. HomeFragment --首页
 */

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment_home,null);
        return  view;
    }
}
```
2)HomeFragment对应布局文件  
>layout/fragment_home.xml  

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
       android:layout_height="match_parent">

    <TextView
        android:text="首页"
        android:textSize="20sp"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>

```
其余三个fragment类似

- - - -

### 1.3 实现不同Fragment间切换
#### 1.3.1 ButterKnife 框架使用
>1 安装插件

![ButterKnife](../markdown_image/01_butterKnife_plugins.png)

>2 导入到gradle文件

```
dependencies {
... ...
/*使用butterknife*/
    compile 'com.jakewharton:butterknife:7.0.1'
}
```

>3 MainActiviy中使用ButterKnife
使用注解初始化视图对象

```
public class MainActivity extends FragmentActivity {

    @Bind(R.id.fl_main)
    FrameLayout flMain;

    @Bind(R.id.iv_main_button_home)
    ImageView ivMainButtonHome;

    @Bind(R.id.ll_main_home)
    LinearLayout llMainHome;

    @Bind(R.id.iv_main_button_invest)
    ImageView ivMainButtonInvest;

    @Bind(R.id.ll_main_invest)
    LinearLayout llMainInvest;

    @Bind(R.id.iv_main_button_my)
    ImageView ivMainButtonMy;

    @Bind(R.id.ll_main_my)
    LinearLayout llMainMy;

    @Bind(R.id.iv_main_button_more)
    ImageView ivMainButtonMore;

    @Bind(R.id.ll_main_more)
    LinearLayout llMainMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSelect(1);

    }

}
```

>添加底部tab点击事件

```
@OnClick({R.id.ll_main_home, R.id.ll_main_invest, R.id.ll_main_my, R.id.ll_main_more})
    public void switchFragment(View view) {
        switch (view.getId()) {
            case R.id.ll_main_home:
                Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
                setSelect(1);
                break;
            case R.id.ll_main_invest:
                Toast.makeText(this, "投资", Toast.LENGTH_SHORT).show();
                setSelect(2);
                break;
            case R.id.ll_main_my:
                Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();
                setSelect(3);
                break;
            case R.id.ll_main_more:
                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                setSelect(4);
                break;

        }
    }
```


>切换fragment试图并更新按钮状态

```
    /**
     * 2. 设置要显示的Fragment
     * >更新button背景图        updateButton(i,oldButton);
     * @param i
     */
    private HomeFragment homefragment;
    private InvestFragment investfragment;
    private MyFragment myfragment;
    private MoreFragment morefragment;

    private int currentfragment = 1;  //标识当前显示的fragment的标号
    private int oldButton = 1;        //标识当前选中的button的标号

    private void setSelect(int i) {
        //通过事务生成并调用fragment
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transation = manager.beginTransaction();

        switch (i) {
            case 1://显示第一个fragment
                if (homefragment == null) {
                    homefragment = new HomeFragment();
                    transation.add(R.id.fl_main, homefragment);
                }
                checkFragment(i, transation);
                transation.show(homefragment);
                currentfragment = i;
                break;

            case 2://显示第二个fragment
                if (investfragment == null) {
                    investfragment = new InvestFragment();
                    transation.add(R.id.fl_main, investfragment);
                }
                checkFragment(i, transation);
                transation.show(investfragment);
                currentfragment = i;//更新当前fragment

                break;
            case 3://显示第三个fragment

                if (myfragment == null) {
                    myfragment = new MyFragment();
                    transation.add(R.id.fl_main, myfragment);
                }
                checkFragment(i, transation);
                transation.show(myfragment);
                break;
            case 4://显示第四个fragment

                if (morefragment == null) {
                    morefragment = new MoreFragment();
                    transation.add(R.id.fl_main, morefragment);
                }
                checkFragment(i, transation);
                transation.show(morefragment);
                break;

        }
        transation.commit();

        updateButton(i, oldButton);
        //更新索引
        currentfragment = i;
        oldButton = i;

    }

    /**
     * 3. 检测当前显示的fragment 是否是将要显示的fragment --隐藏非当前的fragment
     * @param i
     * @param transation
     */
    private void checkFragment(int i, FragmentTransaction transation) {
        if (i != currentfragment) {
            switch (currentfragment) {
                case 1: //隐藏home
                    transation.hide(homefragment);
                    break;
                case 2: //隐藏home
                    transation.hide(investfragment);
                    break;
                case 3: //隐藏home
                    transation.hide(myfragment);
                    break;
                case 4: //隐藏home
                    transation.hide(morefragment);
                    break;

            }
        }

    }

    /**
     * 4. 更新按钮状态
     *
     * @param i
     * @param oldButton
     */
    private void updateButton(int i, int oldButton) {

        Log.d("MainActivity", i + "---" + oldButton);
        //根据oldButton 还原 背景
        switch (oldButton) {
            case 1:
                ivMainButtonHome.setImageResource(R.drawable.bottom01);
                break;
            case 2:
                ivMainButtonInvest.setImageResource(R.drawable.bottom03);
                break;
            case 3:
                ivMainButtonMy.setImageResource(R.drawable.bottom05);
                break;
            case 4:
                ivMainButtonMore.setImageResource(R.drawable.bottom07);
                break;
        }

        //根据i 设置button 背景
        switch (i) {
            case 1:
                ivMainButtonHome.setImageResource(R.drawable.bottom02);
                break;
            case 2:
                ivMainButtonInvest.setImageResource(R.drawable.bottom04);
                break;
            case 3:
                ivMainButtonMy.setImageResource(R.drawable.bottom06);
                break;
            case 4:
                ivMainButtonMore.setImageResource(R.drawable.bottom08);
                break;
        }


    }

```


- - -

###1.4 标题栏
####1.4.1 标题栏对应的布局文件 layout/top_title_main.xml
![标题栏](../markdown_image/01_top_title_main.png)
####1.4.2 四个帧布局Fragment中引用top_title_main.xml
>layout/fragment_home.xml
>layout/fragment_invest.xml
>layout/fragment_my.xml
>layout/fragment_more.xml

```
    <include  layout="@layout/top_title_main"/>
```
####1.4.3 四个帧布局Fragment.class中分别初始化标题栏视图对象
>使用butterknife 注解

```
    /**
     * 1. 初始化标题
     */
    private void initTitle() {
        ivTopTitleBack.setVisibility(View.INVISIBLE);
        tvTopTitleTap.setText("首页");
        ivTopTitleSetting.setVisibility(View.INVISIBLE);
    }

```


- - -


###1.5 双击退出界面(2s内)

####1.5.1 重写onKeyUp方法

```
    //当第一次点击返回键时设置为true 两秒后未按下第二次返回键,还原状态
    private boolean isFirsClick = false;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isFirsClick) {//如果是第一次点击
                Toast.makeText(this, "再点击一次退出应用", Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(1,2000);
                isFirsClick = true;
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
```

####1.5.2 使用handler发送延时消息

>当超出两秒后,将点击状态清空

```
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                isFirsClick = false;
            }

        }
    };
```

>销毁时移除handler消息

```
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
```

>效果图:
![TitleBar & DoubleBack](../markdown_image/01_top_title_back.gif)



### 1.6 欢迎界面
![欢迎界面](../markdown_image/01_splash.png)

#### 1.6.1 布局文件:
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/start_background"
    tools:context="com.example.chen.guigup2p.activity.SplashActivity">


    <TextView
        android:id="@+id/tv_splash_name"
        android:layout_centerHorizontal="true"

        android:layout_marginTop="100dp"
        android:textSize="35sp"
        android:textColor="@color/title_text"
        android:text="硅谷金融"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />

    <ImageView
        android:id="@+id/iv_splash_icon"
        android:layout_centerInParent="true"
        android:layout_centerHorizontal="true"
        android:src="@drawable/splash_icon"
        android:layout_width="100dp"
        android:layout_height="100dp" />




    <TextView
        android:id="@+id/tv_splash_version"
        android:layout_centerHorizontal="true"

        android:layout_alignParentBottom="true"
        android:layout_marginBottom="80dp"
        android:textSize="20sp"
        android:textColor="@color/white"
        android:text="版本号:v1.0"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />

</RelativeLayout>

```

#### 1.6.2 发送延时消息 2s 后启动MainActivity
>com/example/chen/guigup2p/activity/SplashActivity.java


```

/**
 * 启动界面: 停留2s 后跳转到MainActivity
 */
public class SplashActivity extends Activity {

    @Bind(R.id.tv_splash_name)
    TextView tvSplashName;
    @Bind(R.id.iv_splash_icon)
    ImageView ivSplashIcon;
    @Bind(R.id.tv_splash_version)
    TextView tvSplashVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1. 设置spalsh页面 全屏显示
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏顶部的状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        //2.发送延时消息,启动MainActivity
        handler.sendEmptyMessageDelayed(1, 2000);

    }

}
```


> 1使用handler发送延时消息

```
private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    startMainActivity();
                    break;
            }
        }
    };


    private void startMainActivity() {
        //1. 启动MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //2. 关闭当前视图
        this.finish();//关闭当前视图
    }



```

> 2 回收消息

```
    @Override
    protected void onDestroy() {
        //1. 移除消息
        handler.removeCallbacksAndMessages(1);
        super.onDestroy();

    }

```

#### 1.6.3 设置为全屏显示方式
>方式一：动态编码的方式:

```
// 去掉窗口标题
requestWindowFeature(Window.FEATURE_NO_TITLE);
// 隐藏顶部的状态栏
getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
setContentView();
```

>方式二：功能清单文件中 更改主题

```
android:theme="@android:style/Theme.Black.NoTitleBar"
android:theme="@android:style/Theme.Black.NoTitleBar.Fullscreen"

```

>方式三: 类似自定义主题

```
<style name="AppTheme" parent="AppBaseTheme">
<item name="android:windowNoTitle">true</item><!-- 没有标题 -->
</style>
```





