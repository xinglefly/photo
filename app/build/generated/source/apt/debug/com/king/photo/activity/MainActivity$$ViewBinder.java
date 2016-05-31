// Generated code from Butter Knife. Do not modify!
package com.king.photo.activity;

import android.view.View;
import android.widget.AdapterView;
import butterknife.Unbinder;
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
    view = finder.findRequiredView(source, 2131296256, "field 'activitySelectimgSend'");
    target.activitySelectimgSend = finder.castView(view, 2131296256, "field 'activitySelectimgSend'");
    view = finder.findRequiredView(source, 2131296257, "field 'noScrollgridview' and method 'onGridviewItemClick'");
    target.noScrollgridview = finder.castView(view, 2131296257, "field 'noScrollgridview'");
    unbinder.view2131296257 = view;
    ((AdapterView<?>) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onGridviewItemClick(p0, p2);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends MainActivity> implements Unbinder {
    private T target;

    View view2131296257;

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
      target.activitySelectimgSend = null;
      ((AdapterView<?>) view2131296257).setOnItemClickListener(null);
      target.noScrollgridview = null;
    }
  }
}
