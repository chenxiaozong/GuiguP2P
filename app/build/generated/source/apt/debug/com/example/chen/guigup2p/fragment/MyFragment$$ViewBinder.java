// Generated code from Butter Knife. Do not modify!
package com.example.chen.guigup2p.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyFragment$$ViewBinder<T extends com.example.chen.guigup2p.fragment.MyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624175, "field 'ivTopTitleBack'");
    target.ivTopTitleBack = finder.castView(view, 2131624175, "field 'ivTopTitleBack'");
    view = finder.findRequiredView(source, 2131624176, "field 'tvTopTitleTap'");
    target.tvTopTitleTap = finder.castView(view, 2131624176, "field 'tvTopTitleTap'");
    view = finder.findRequiredView(source, 2131624177, "field 'ivTopTitleSetting' and method 'titleSettingClick'");
    target.ivTopTitleSetting = finder.castView(view, 2131624177, "field 'ivTopTitleSetting'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.titleSettingClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624174, "field 'llTopTitleMain'");
    target.llTopTitleMain = finder.castView(view, 2131624174, "field 'llTopTitleMain'");
    view = finder.findRequiredView(source, 2131624133, "field 'ivMeIcon' and method 'meIconClick'");
    target.ivMeIcon = finder.castView(view, 2131624133, "field 'ivMeIcon'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.meIconClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624132, "field 'rlMeIcon'");
    target.rlMeIcon = finder.castView(view, 2131624132, "field 'rlMeIcon'");
    view = finder.findRequiredView(source, 2131624134, "field 'tvMeName'");
    target.tvMeName = finder.castView(view, 2131624134, "field 'tvMeName'");
    view = finder.findRequiredView(source, 2131624131, "field 'rlMe'");
    target.rlMe = finder.castView(view, 2131624131, "field 'rlMe'");
    view = finder.findRequiredView(source, 2131624135, "field 'recharge' and method 'rechargeOnclick'");
    target.recharge = finder.castView(view, 2131624135, "field 'recharge'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.rechargeOnclick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624136, "field 'withdraw' and method 'tiXian'");
    target.withdraw = finder.castView(view, 2131624136, "field 'withdraw'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.tiXian(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624137, "field 'llTouzi' and method 'touziOnclick'");
    target.llTouzi = finder.castView(view, 2131624137, "field 'llTouzi'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.touziOnclick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624138, "field 'llTouziZhiguan' and method 'zhiguanOnclick'");
    target.llTouziZhiguan = finder.castView(view, 2131624138, "field 'llTouziZhiguan'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.zhiguanOnclick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624139, "field 'llZichan' and method 'zichanOnclick'");
    target.llZichan = finder.castView(view, 2131624139, "field 'llZichan'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.zichanOnclick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.ivTopTitleBack = null;
    target.tvTopTitleTap = null;
    target.ivTopTitleSetting = null;
    target.llTopTitleMain = null;
    target.ivMeIcon = null;
    target.rlMeIcon = null;
    target.tvMeName = null;
    target.rlMe = null;
    target.recharge = null;
    target.withdraw = null;
    target.llTouzi = null;
    target.llTouziZhiguan = null;
    target.llZichan = null;
  }
}
