package com.itssc.tool_imageloader.config;

/**
 * author：dachao on 2019/1/7 09:55
 */
public class AlanberImageLoaderConfig {
    public static int TYPE_GLIDE = 1;
    public static int TYPE_PICASSO = 2;

    public static int getImageLoaderManager() {
        return TYPE_GLIDE;
    }
}
