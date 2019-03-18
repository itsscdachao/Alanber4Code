package com.itssc.tool_imageloader.listener;

import android.view.View;

/**
 * author：dachao on 2019/1/7 09:55
 */
public interface AlanberImageLoadingProgressListener {
    void onLoadingProgressUpdate(String imageUri, View view, int current, int total);
}
