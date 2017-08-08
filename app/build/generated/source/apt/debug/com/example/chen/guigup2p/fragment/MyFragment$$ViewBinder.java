// Generated code from Butter Knife. Do not modify!
package com.example.chen.guigup2p.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyFragment$$ViewBinder<T extends com.example.chen.guigup2p.fragment.MyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624161, "field 'ivTopTitleBack'");
    target.ivTopTitleBack = finder.castView(view, 2131624161, "field 'ivTopTitleBack'");
    view = finder.findRequiredView(source, 2131624162, "field 'tvTopTitleTap'");
    target.tvTopTitleTap = finder.castView(view, 2131624162, "field 'tvTopTitleTap'");
    view = finder.findRequiredView(source, 2131624163, "field 'ivTopTitleSetting' and method 'titleSettingClick'");
    target.ivTopTitleSetting = finder.castView(view, 2131624163, "field 'ivTopTitleSetting'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.titleSettingClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624160, "field 'llTopTitleMain'");
    target.llTopTitleMain = finder.castView(view, 2131624160, "field 'llTopTitleMain'");
    view = finder.findRequiredView(source, 2131624119, "field 'ivMeIcon' and method 'meIconClick'");
    target.ivMeIcon = finder.castView(view, 2131624119, "field 'ivMeIcon'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.meIconClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624118, "field 'rlMeIcon'");
    target.rlMeIcon = finder.castView(view, 2131624118, "field 'rlMeIcon'");
    view = finder.findRequiredView(source, 2131624120, "field 'tvMeName'");
    target.tvMeName = finder.castView(view, 2131624120, "field 'tvMeName'");
    view = finder.findRequiredView(source, 2131624117, "field 'rlMe'");
    target.rlMe = finder.castView(view, 2131624117, "field 'rlMe'");
    view = finder.findRequiredView(source, 2131624121, "field 'recharge'");
    target.recharge = finder.castView(view, 2131624121, "field 'recharge'");
    view = finder.findRequiredView(source, 2131624122, "field 'withdraw'");
    target.withdraw = finder.castView(view, 2131624122, "field 'withdraw'");
    view = finder.findRequiredView(source, 2131624123, "field 'llTouzi'");
    target.llTouzi = finder.castView(view, 2131624123, "field 'llTouzi'");
    view = finder.findRequiredView(source, 2131624124, "field 'llTouziZhiguan'");
    target.llTouziZhiguan = finder.castView(view, 2131624124, "field 'llTouziZhiguan'");
    view = finder.findRequiredView(source, 2131624125, "field 'llZichan'");
    target.llZichan = finder.castView(view, 2131624125, "field 'llZichan'");
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
