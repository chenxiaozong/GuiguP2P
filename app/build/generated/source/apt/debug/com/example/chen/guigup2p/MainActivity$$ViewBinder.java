// Generated code from Butter Knife. Do not modify!
package com.example.chen.guigup2p;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.chen.guigup2p.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624080, "field 'flMain'");
    target.flMain = finder.castView(view, 2131624080, "field 'flMain'");
    view = finder.findRequiredView(source, 2131624097, "field 'ivMainButtonHome'");
    target.ivMainButtonHome = finder.castView(view, 2131624097, "field 'ivMainButtonHome'");
    view = finder.findRequiredView(source, 2131624096, "field 'llMainHome' and method 'switchFragment'");
    target.llMainHome = finder.castView(view, 2131624096, "field 'llMainHome'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.switchFragment(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624099, "field 'ivMainButtonInvest'");
    target.ivMainButtonInvest = finder.castView(view, 2131624099, "field 'ivMainButtonInvest'");
    view = finder.findRequiredView(source, 2131624098, "field 'llMainInvest' and method 'switchFragment'");
    target.llMainInvest = finder.castView(view, 2131624098, "field 'llMainInvest'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.switchFragment(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624101, "field 'ivMainButtonMy'");
    target.ivMainButtonMy = finder.castView(view, 2131624101, "field 'ivMainButtonMy'");
    view = finder.findRequiredView(source, 2131624100, "field 'llMainMy' and method 'switchFragment'");
    target.llMainMy = finder.castView(view, 2131624100, "field 'llMainMy'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.switchFragment(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624103, "field 'ivMainButtonMore'");
    target.ivMainButtonMore = finder.castView(view, 2131624103, "field 'ivMainButtonMore'");
    view = finder.findRequiredView(source, 2131624102, "field 'llMainMore' and method 'switchFragment'");
    target.llMainMore = finder.castView(view, 2131624102, "field 'llMainMore'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.switchFragment(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.flMain = null;
    target.ivMainButtonHome = null;
    target.llMainHome = null;
    target.ivMainButtonInvest = null;
    target.llMainInvest = null;
    target.ivMainButtonMy = null;
    target.llMainMy = null;
    target.ivMainButtonMore = null;
    target.llMainMore = null;
  }
}
