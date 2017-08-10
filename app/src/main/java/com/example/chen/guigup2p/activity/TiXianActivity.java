package com.example.chen.guigup2p.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.guigup2p.R;
import com.example.chen.guigup2p.util.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {

    @Bind(R.id.iv_top_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_top_title_tap)
    TextView tvTitle;
    @Bind(R.id.iv_top_title_setting)
    ImageView ivTitleSetting;



    @Bind(R.id.account_zhifubao)
    TextView accountZhifubao;
    @Bind(R.id.select_bank)
    RelativeLayout selectBank;
    @Bind(R.id.chongzhi_text)
    TextView chongzhiText;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.et_input_money)
    EditText etInputMoney;
    @Bind(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @Bind(R.id.textView5)
    TextView textView5;

    @Bind(R.id.btn_tixian)
    Button btnTixian;



    @Override
    protected void initData() {
        //初始化按钮可操作:false
        btnTixian.setEnabled(false);

        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String money = etInputMoney.getText().toString().trim();
                if(TextUtils.isEmpty(money)) {
                    //button不可点击
                    btnTixian.setEnabled(false);
                    //背景
                    btnTixian.setBackgroundResource(R.drawable.btn_02);

                }else {
                    btnTixian.setEnabled(true);
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }


            }
        });

    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.INVISIBLE);
        tvTitle.setText("体现");


    }





    @Override
    protected int getLayoutId() {
        return R.layout.activity_ti_xian;
    }
    @OnClick(R.id.btn_tixian)
    public void tixianOnclick(View view){
        //将要提现的数据数额发送给后台，由后台连接第三方支付平台，完成金额的提现操作。（略）
        //提示用户信息：
        UIUtils.toast("您的提现申请已被成功受理。审核通过后，24小时内，你的钱自然会到账",false);

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                removeCurrentActivity();
            }
        },2000);
    }

    @OnClick(R.id.iv_top_title_back)
    public  void backOnclick(View view){
        removeCurrentActivity();
    }

}
