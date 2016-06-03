package com.xinglefly.photo.event;

public class PhotoEvent {

    private boolean isRefresh;

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    public PhotoEvent(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }
}