// Generated code from Butter Knife. Do not modify!
package com.king.photo.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ShowAllPhoto$$ViewBinder<T extends ShowAllPhoto> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131296290, "field 'tvHeadTitle'");
    target.tvHeadTitle = finder.castView(view, 2131296290, "field 'tvHeadTitle'");
    view = finder.findRequiredView(source, 2131296268, "field 'btnPreview' and method 'onButtonClick'");
    target.btnPreview = finder.castView(view, 2131296268, "field 'btnPreview'");
    unbinder.view2131296268 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296269, "field 'btnFinish' and method 'onButtonClick'");
    target.btnFinish = finder.castView(view, 2131296269, "field 'btnFinish'");
    unbinder.view2131296269 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296293, "field 'progressBar'");
    target.progressBar = finder.castView(view, 2131296293, "field 'progressBar'");
    view = finder.findRequiredView(source, 2131296292, "field 'gridview_photos'");
    target.gridview_photos = finder.castView(view, 2131296292, "field 'gridview_photos'");
    view = finder.findRequiredView(source, 2131296289, "method 'onButtonClick'");
    unbinder.view2131296289 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296266, "method 'onButtonClick'");
    unbinder.view2131296266 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ShowAllPhoto> implements Unbinder {
    private T target;

    View view2131296268;

    View view2131296269;

    View view2131296289;

    View view2131296266;

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
      target.tvHeadTitle = null;
      view2131296268.setOnClickListener(null);
      target.btnPreview = null;
      view2131296269.setOnClickListener(null);
      target.btnFinish = null;
      target.progressBar = null;
      target.gridview_photos = null;
      view2131296289.setOnClickListener(null);
      view2131296266.setOnClickListener(null);
    }
  }
}
