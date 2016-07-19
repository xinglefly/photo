package com.xinglefly.photo.activity.actions;

import rx.functions.Action1;

public abstract class ErrAction implements Action1<Throwable> {

    @Override
    public void call(Throwable throwable) {
        onError(throwable);
    }

    protected abstract void onError(Throwable throwable);
}