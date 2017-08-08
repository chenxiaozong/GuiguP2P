// Generated code from Butter Knife. Do not modify!
package com.example.chen.guigup2p.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyFragment$$ViewBinder<T extends com.example.chen.guigup2p.fragment.MyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558576, "field 'ivTopTitleBack'");
    target.ivTopTitleBack = finder.castView(view, 2131558576, "field 'ivTopTitleBack'");
    view = finder.findRequiredView(source, 2131558577, "field 'tvTopTitleTap'");
    target.tvTopTitleTap = finder.castView(view, 2131558577, "field 'tvTopTitleTap'");
    view = finder.findRequiredView(source, 2131558578, "field 'ivTopTitleSetting'");
    target.ivTopTitleSetting = finder.castView(view, 2131558578, "field 'ivTopTitleSetting'");
    view = finder.findRequiredView(source, 2131558575, "field 'llTopTitleMain'");
    target.llTopTitleMain = finder.castView(view, 2131558575, "field 'llTopTitleMain'");
  }

  @Override public void unbind(T target) {
    target.ivTopTitleBack = null;
    target.tvTopTitleTap = null;
    target.ivTopTitleSetting = null;
    target.llTopTitleMain = null;
  }
}
