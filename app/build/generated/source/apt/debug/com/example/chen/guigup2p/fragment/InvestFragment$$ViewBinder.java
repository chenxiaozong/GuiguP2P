// Generated code from Butter Knife. Do not modify!
package com.example.chen.guigup2p.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InvestFragment$$ViewBinder<T extends com.example.chen.guigup2p.fragment.InvestFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493018, "field 'ivTopTitleBack'");
    target.ivTopTitleBack = finder.castView(view, 2131493018, "field 'ivTopTitleBack'");
    view = finder.findRequiredView(source, 2131493019, "field 'tvTopTitleTap'");
    target.tvTopTitleTap = finder.castView(view, 2131493019, "field 'tvTopTitleTap'");
    view = finder.findRequiredView(source, 2131493020, "field 'ivTopTitleSetting'");
    target.ivTopTitleSetting = finder.castView(view, 2131493020, "field 'ivTopTitleSetting'");
    view = finder.findRequiredView(source, 2131493017, "field 'llTopTitleMain'");
    target.llTopTitleMain = finder.castView(view, 2131493017, "field 'llTopTitleMain'");
  }

  @Override public void unbind(T target) {
    target.ivTopTitleBack = null;
    target.tvTopTitleTap = null;
    target.ivTopTitleSetting = null;
    target.llTopTitleMain = null;
  }
}
