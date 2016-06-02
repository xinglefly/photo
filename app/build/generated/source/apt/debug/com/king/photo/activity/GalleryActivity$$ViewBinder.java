// Generated code from Butter Knife. Do not modify!
package com.king.photo.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class GalleryActivity$$ViewBinder<T extends GalleryActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131296261, "field 'btn_finish' and method 'onGalleryClicks'");
    target.btn_finish = finder.castView(view, 2131296261, "field 'btn_finish'");
    unbinder.view2131296261 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGalleryClicks(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296281, "field 'pager' and method 'onPageSelected'");
    target.pager = finder.castView(view, 2131296281, "field 'pager'");
    unbinder.view2131296281 = view;
    ((ViewPager) view).setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageSelected(int p0) {
        target.onPageSelected(p0);
      }

      @Override
      public void onPageScrolled(int p0, float p1, int p2) {
      }

      @Override
      public void onPageScrollStateChanged(int p0) {
      }
    });
    view = finder.findRequiredView(source, 2131296279, "method 'onGalleryClicks'");
    unbinder.view2131296279 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGalleryClicks(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296280, "method 'onGalleryClicks'");
    unbinder.view2131296280 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGalleryClicks(p0);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends GalleryActivity> implements Unbinder {
    private T target;

    View view2131296261;

    View view2131296281;

    View view2131296279;

    View view2131296280;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      view2131296261.setOnClickListener(null);
      target.btn_finish = null;
      ((ViewPager) view2131296281).setOnPageChangeListener(null);
      target.pager = null;
      view2131296279.setOnClickListener(null);
      view2131296280.setOnClickListener(null);
    }
  }
}
