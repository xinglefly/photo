package com.xinglefly.photo.activity.actions;

import rx.functions.Action1;

public abstract class SuccessAction<T> implements Action1<T> {

    @Override
    public void call(T t) {
        success(t);
    }

    protected abstract void success(T t);

}