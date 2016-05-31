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
    view = finder.findRequiredView(source, 2131296270, "field 'btnPreview' and method 'onButtonClick'");
    target.btnPreview = finder.castView(view, 2131296270, "field 'btnPreview'");
    unbinder.view2131296270 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296271, "field 'btnFinish' and method 'onButtonClick'");
    target.btnFinish = finder.castView(view, 2131296271, "field 'btnFinish'");
    unbinder.view2131296271 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296272, "field 'gridViewAlbum'");
    target.gridViewAlbum = finder.castView(view, 2131296272, "field 'gridViewAlbum'");
    view = finder.findRequiredView(source, 2131296273, "field 'tvNone'");
    target.tvNone = finder.castView(view, 2131296273, "field 'tvNone'");
    view = finder.findRequiredView(source, 2131296267, "method 'onButtonClick'");
    unbinder.view2131296267 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131296268, "method 'onButtonClick'");
    unbinder.view2131296268 = view;
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

    View view2131296270;

    View view2131296271;

    View view2131296267;

    View view2131296268;

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
      view2131296270.setOnClickListener(null);
      target.btnPreview = null;
      view2131296271.setOnClickListener(null);
      target.btnFinish = null;
      target.gridViewAlbum = null;
      target.tvNone = null;
      view2131296267.setOnClickListener(null);
      view2131296268.setOnClickListener(null);
    }
  }
}
