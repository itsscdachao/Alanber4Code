package com.itssc.tool_imageloader.manager;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itssc.tool_imageloader.listener.AlanberImageLoadingListener;
import com.itssc.tool_imageloader.listener.AlanberImageLoadingProgressListener;
import com.itssc.tool_imageloader.proxy.AlanberImageLoaderProxy;

/**
 * author：dachao on 2019/1/7 10:31
 */
public class GlideImageLoader implements AlanberImageLoaderProxy {
    @Override
    public void initImageLoader(Context context) {

    }

    @Override
    public void defaultDisplayImage(Context context, String uri, ImageView imageView, int placeholderImage) {
        Glide.with(context).load(uri).into(imageView);
    }

    @Override
    public void displayImage(Context context, String uri, int defaultImage, ImageView imageView, AlanberImageLoadingListener listener, AlanberImageLoadingProgressListener progressListener) {

    }
}
