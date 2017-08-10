// Generated code from Butter Knife. Do not modify!
package com.example.chen.guigup2p.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InvestFragment$$ViewBinder<T extends com.example.chen.guigup2p.fragment.InvestFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624188, "field 'ivTopTitleBack'");
    target.ivTopTitleBack = finder.castView(view, 2131624188, "field 'ivTopTitleBack'");
    view = finder.findRequiredView(source, 2131624189, "field 'tvTopTitleTap'");
    target.tvTopTitleTap = finder.castView(view, 2131624189, "field 'tvTopTitleTap'");
    view = finder.findRequiredView(source, 2131624190, "field 'ivTopTitleSetting'");
    target.ivTopTitleSetting = finder.castView(view, 2131624190, "field 'ivTopTitleSetting'");
    view = finder.findRequiredView(source, 2131624187, "field 'llTopTitleMain'");
    target.llTopTitleMain = finder.castView(view, 2131624187, "field 'llTopTitleMain'");
    view = finder.findRequiredView(source, 2131624134, "field 'tabpagerInvest'");
    target.tabpagerInvest = finder.castView(view, 2131624134, "field 'tabpagerInvest'");
    view = finder.findRequiredView(source, 2131624135, "field 'viewPagerInvest'");
    target.viewPagerInvest = finder.castView(view, 2131624135, "field 'viewPagerInvest'");
  }

  @Override public void unbind(T target) {
    target.ivTopTitleBack = null;
    target.tvTopTitleTap = null;
    target.ivTopTitleSetting = null;
    target.llTopTitleMain = null;
    target.tabpagerInvest = null;
    target.viewPagerInvest = null;
  }
}
