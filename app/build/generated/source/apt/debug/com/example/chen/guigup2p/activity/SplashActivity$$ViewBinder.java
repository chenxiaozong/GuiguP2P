// Generated code from Butter Knife. Do not modify!
package com.example.chen.guigup2p.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SplashActivity$$ViewBinder<T extends com.example.chen.guigup2p.activity.SplashActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624097, "field 'tvSplashName'");
    target.tvSplashName = finder.castView(view, 2131624097, "field 'tvSplashName'");
    view = finder.findRequiredView(source, 2131624098, "field 'ivSplashIcon'");
    target.ivSplashIcon = finder.castView(view, 2131624098, "field 'ivSplashIcon'");
    view = finder.findRequiredView(source, 2131624099, "field 'tvSplashVersion'");
    target.tvSplashVersion = finder.castView(view, 2131624099, "field 'tvSplashVersion'");
    view = finder.findRequiredView(source, 2131624096, "field 'rlWelcome'");
    target.rlWelcome = finder.castView(view, 2131624096, "field 'rlWelcome'");
  }

  @Override public void unbind(T target) {
    target.tvSplashName = null;
    target.ivSplashIcon = null;
    target.tvSplashVersion = null;
    target.rlWelcome = null;
  }
}
