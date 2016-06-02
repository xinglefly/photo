// Generated code from Butter Knife. Do not modify!
package com.king.photo.activity;

import android.view.View;
import android.widget.AdapterView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MainActivity$$ViewBinder<T extends MainActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131296285, "field 'noScrollgridview' and method 'onGridviewItemClick'");
    target.noScrollgridview = finder.castView(view, 2131296285, "field 'noScrollgridview'");
    unbinder.view2131296285 = view;
    ((AdapterView<?>) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onGridviewItemClick(p0, p2);
      }
    });
    view = finder.findRequiredView(source, 2131296284, "method 'onUploadClick'");
    unbinder.view2131296284 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onUploadClick();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends MainActivity> implements Unbinder {
    private T target;

    View view2131296285;

    View view2131296284;

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
      ((AdapterView<?>) view2131296285).setOnItemClickListener(null);
      target.noScrollgridview = null;
      view2131296284.setOnClickListener(null);
    }
  }
}
