package com.example.chen.guigup2p.bean;

/**
 * Created by shkstart on 2016/12/10 0010.
 * 应用版本信息对应的bean
 */
public class UpdateInfo {
    public String version;//服务器的最新版本值
    public String apkUrl;//最新版本的路径
    public String desc;//版本更新细节

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "version='" + version + '\'' +
                ", apkUrl='" + apkUrl + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
