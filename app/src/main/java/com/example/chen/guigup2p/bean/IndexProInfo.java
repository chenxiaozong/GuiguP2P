package com.example.chen.guigup2p.bean;

/**
 * Created by chen on 2017/4/14.
 */

public class IndexProInfo {

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
        return "IndexProInfo{" +
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
