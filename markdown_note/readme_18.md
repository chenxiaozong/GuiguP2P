重置手势密码:

# 1.initDat() 为相应文本设置重置手势密码监听
>   resetGesture();

# 2. resetGesture

```java

    /**
     * 重置手势密码:
     * 检查手势密码是否开启?
     *   > 已开启: startActivity
     *   > 未开启:toast
     */
    private void resetGesture() {
        boolean checked = toggleMore.isChecked();

        if(checked) {
            BaseActivity base = (BaseActivity) this.getContext();
            base.goToActivity(GestureEditActivity.class,null);

        }else {
            UIUtils.toast("手势密码功能未开启,请开启后设置",false);

        }

    }
```




