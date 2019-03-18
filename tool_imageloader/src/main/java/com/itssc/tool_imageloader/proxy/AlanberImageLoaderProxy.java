package com.itssc.tool_imageloader.proxy;

import android.content.Context;
import android.widget.ImageView;

import com.itssc.tool_imageloader.listener.AlanberImageLoadingListener;
import com.itssc.tool_imageloader.listener.AlanberImageLoadingProgressListener;

/**
 * author：dachao on 2019/1/7 09:55
 */
public interface AlanberImageLoaderProxy {
    void initImageLoader(Context context);

    void displayImage(String uri, int defaultImage, ImageView imageView, AlanberImageLoadingListener listener, AlanberImageLoadingProgressListener progressListener);
}
