package com.example.chen.guigup2p.bean;

/**
 * Created by shkstart on 2016/11/12 0012.
 */
public class Product {

    public String id;
    public String memberNum;
    public String minTouMoney;
    public String money;
    public String name;
    public String progress;
    public String suodingDays;
    public String yearRate;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", memberNum='" + memberNum + '\'' +
                ", minTouMoney='" + minTouMoney + '\'' +
                ", money='" + money + '\'' +
                ", name='" + name + '\'' +
                ", progress='" + progress + '\'' +
                ", suodingDays='" + suodingDays + '\'' +
                ", yearRate='" + yearRate + '\'' +
                '}';
    }
}
