package com.itssc.tool_network.engine;

import android.util.Log;

import com.itssc.tool_network.config.NetWorkConfig;
import com.itssc.tool_network.interceptor.CommonParamsInterceptor;
import com.itssc.tool_network.interceptor.HeadParamsInterceptor;
import com.itssc.tool_network.interceptor.LogInterceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author：dachao on 2019/3/11 18:58
 */
public class RequestEngine {

    private static volatile RequestEngine requestEngine;
    private final OkHttpClient client;
    private final Retrofit retrofit;

    private RequestEngine() {
        //网络日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    Log.e("",text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("",message);
                }
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new LogInterceptor())//添加log拦截器
                .addInterceptor(new HeadParamsInterceptor())//添加请求头参数拦截器
                .addInterceptor(new CommonParamsInterceptor())//添加公共参数拦截器
                .connectTimeout(NetWorkConfig.HTTP_REQUEST_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(NetWorkConfig.HTTP_REQUEST_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(NetWorkConfig.HTTP_REQUEST_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)//是否超时重试
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(NetWorkConfig.HOST_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    //获取Service实例
    public <T> T create(Class<T> typeClazz) {
        return retrofit.create(typeClazz);
    }

    public static RequestEngine getInstance() {
        if (requestEngine == null) {
            synchronized (RequestEngine.class) {
                if (requestEngine == null) {
                    requestEngine = new RequestEngine();
                }
            }
        }
        return requestEngine;
    }
}
