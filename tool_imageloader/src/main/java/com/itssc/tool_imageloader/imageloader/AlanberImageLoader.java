package com.itssc.tool_imageloader.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.itssc.tool_imageloader.config.AlanberImageLoaderConfig;
import com.itssc.tool_imageloader.listener.AlanberImageLoadingListener;
import com.itssc.tool_imageloader.listener.AlanberImageLoadingProgressListener;
import com.itssc.tool_imageloader.manager.GlideImageLoader;
import com.itssc.tool_imageloader.manager.FrescoImageLoader;
import com.itssc.tool_imageloader.proxy.AlanberImageLoaderProxy;

/**
 * author：dachao on 2019/1/7 10:31
 */
public class AlanberImageLoader implements AlanberImageLoaderProxy {

    private AlanberImageLoaderProxy alanberImageLoaderProxy;
    private static volatile AlanberImageLoader alanberImageLoader;

    public static AlanberImageLoader getInstance() {
        if (alanberImageLoader == null) {
            synchronized (AlanberImageLoader.class) {
                if (alanberImageLoader == null) {
                    alanberImageLoader = new AlanberImageLoader();
                }
            }
        }
        return alanberImageLoader;
    }

    public AlanberImageLoader() {
        if (AlanberImageLoaderConfig.getImageLoaderManager() == AlanberImageLoaderConfig.TYPE_GLIDE) {
            alanberImageLoaderProxy = new GlideImageLoader();
        } else if (AlanberImageLoaderConfig.getImageLoaderManager() == AlanberImageLoaderConfig.TYPE_FRESCO) {
            alanberImageLoaderProxy = new FrescoImageLoader();
        }
    }

    @Override
    public void initImageLoader(Context context) {
        alanberImageLoaderProxy.initImageLoader(context);
    }

    @Override
    public void displayImage(String uri, int defaultImage, ImageView imageView, AlanberImageLoadingListener listener, AlanberImageLoadingProgressListener progressListener) {
        alanberImageLoaderProxy.displayImage(uri, defaultImage, imageView, listener, progressListener);
    }
}
