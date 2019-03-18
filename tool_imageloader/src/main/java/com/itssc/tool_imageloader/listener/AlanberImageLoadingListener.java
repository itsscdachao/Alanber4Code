package com.itssc.tool_imageloader.listener;

import android.graphics.Bitmap;
import android.view.View;

/**
 * author：dachao on 2019/1/7 09:55
 */
public interface AlanberImageLoadingListener {
    void onLoadingStarted(String imageUri, View view);

    void onLoadingFailed(String imageUri, View view, String failReason);

    void onLoadingComplete(String imageUri, View view, Bitmap loadedImage);

    void onLoadingCancelled(String imageUri, View view);
}
