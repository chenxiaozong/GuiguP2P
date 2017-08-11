手势密码

[TOC]

# 1.手势密码状态保存

> 功能: 
当开启手势密码时,保存开启状态到sp,保证退出应用下次重新启动应用后从本地sp读取手势密码开启状态,并设置tooglebutton 的显示状态(开启/关闭)


## 1.创建sp保存手势状态
> MoreFragment : initData() 

```java

    @Override
    protected void initData(String content) {

        sp = getContext().getSharedPreferences("secrete_protect", Context.MODE_PRIVATE);
        getGestureState(sp); //获取手势状态

        //设置手势密码
        setGesture();//设置手势密码开关
        //为每个条目设置点击事件
        setItemOnclick();
    }

```




## 2.设置tooglebutton的状态改变监听

```java
 private void setGesture() {
        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    UIUtils.toast("开启",false);

                    sp.edit().putBoolean("isOpen",true).commit();


                }else {
                    UIUtils.toast("关闭手势密码",false);
                    //保存状态到sp
                    sp.edit().putBoolean("isOpen",false).commit();
                }
            }
        });

    }
Multi-line Code
```


## 3.读取本地sp中手势密码的状态,并更新tooglebutton 选中状态

```java
    /**
     * 获取getsture状态
     * @return
     * @param sp
     */
    public void  getGestureState(SharedPreferences sp) {

        if(this.sp !=null) {
            boolean isOpen = this.sp.getBoolean("isOpen",false);

            if(isOpen) {
                toggleMore.setChecked(true);
            }else {
                toggleMore.setChecked(false);
            }
        }
    }
Multi-line Code
```

## 4.效果

![Image Title](../markdown_image/17_1gesturesave.gif) 

# 2.使用Demo改为手势密码库
## 0.导入demo
> GestureLock

## 1.复制需要用到两个activity
> demo/GestureEditActivity.java
> demo/GestureVerifyActivity.java

## 2.将Demo改为lib库
1.删除不需要的activity(将需要的activity复制到app应用中)
2.修改manifest文件     
`    <application></application>`
3. 修改build.gradle文件
> apply plugin: 'com.android.library' 

> 删除defaultConfig 中的id
 
```java
 {
 //       applicationId "com.atguigu.gesturelock"
....
}
```










