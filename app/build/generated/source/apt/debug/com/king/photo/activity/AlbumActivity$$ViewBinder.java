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

public class AlbumActivity$$ViewBinder<T extends AlbumActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131296260, "field 'btnPreview' and method 'onButtonClick'");
    target.btnPreview = finder.castView(view, 2131296260, "field 'btnPreview'");
    unbinder.view2131296260 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296261, "field 'btnFinish' and method 'onButtonClick'");
    target.btnFinish = finder.castView(view, 2131296261, "field 'btnFinish'");
    unbinder.view2131296261 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296262, "field 'gridViewAlbum'");
    target.gridViewAlbum = finder.castView(view, 2131296262, "field 'gridViewAlbum'");
    view = finder.findRequiredView(source, 2131296263, "field 'tvNone'");
    target.tvNone = finder.castView(view, 2131296263, "field 'tvNone'");
    view = finder.findRequiredView(source, 2131296257, "method 'onButtonClick'");
    unbinder.view2131296257 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296258, "method 'onButtonClick'");
    unbinder.view2131296258 = view;
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

  protected static class InnerUnbinder<T extends AlbumActivity> implements Unbinder {
    private T target;

    View view2131296260;

    View view2131296261;

    View view2131296257;

    View view2131296258;

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
      view2131296260.setOnClickListener(null);
      target.btnPreview = null;
      view2131296261.setOnClickListener(null);
      target.btnFinish = null;
      target.gridViewAlbum = null;
      target.tvNone = null;
      view2131296257.setOnClickListener(null);
      view2131296258.setOnClickListener(null);
    }
  }
}
