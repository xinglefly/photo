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

public class ImageFile$$ViewBinder<T extends ImageFile> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131296278, "field 'headerTitle'");
    target.headerTitle = finder.castView(view, 2131296278, "field 'headerTitle'");
    view = finder.findRequiredView(source, 2131296279, "field 'gridviewFile'");
    target.gridviewFile = finder.castView(view, 2131296279, "field 'gridviewFile'");
    view = finder.findRequiredView(source, 2131296269, "method 'onBtnClick'");
    unbinder.view2131296269 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnClick();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ImageFile> implements Unbinder {
    private T target;

    View view2131296269;

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
      target.headerTitle = null;
      target.gridviewFile = null;
      view2131296269.setOnClickListener(null);
    }
  }
}
