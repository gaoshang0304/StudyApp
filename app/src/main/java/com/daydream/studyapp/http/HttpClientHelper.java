package com.daydream.studyapp.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author gjc
 * @version ;;
 * @since 2017-12-20
 */

public class HttpClientHelper {

    private static OkHttpClient mClient;
    private final String logTag = "HttpUtils";
    private LoggerInterceptor loggerInterceptor = new LoggerInterceptor(logTag);

    public HttpClientHelper() {
        /**
         * 构建OkHttpClient
         */
        OkHttpClient.Builder builder = mClient.newBuilder();
        /**
         * 设置读取的超时时间
         */
        builder.readTimeout(60, TimeUnit.SECONDS);
        /**
         * 设置写入的超时时间
         */
        builder.writeTimeout(60, TimeUnit.SECONDS);
        /**
         * 设置连接的超时时间
         */
        builder.connectTimeout(60, TimeUnit.SECONDS);
        /**
         * 设置log拦截
         */
        builder.addInterceptor(loggerInterceptor);

    }


    public static OkHttpClient getInstance() {
        if (mClient == null) {
            synchronized (HttpClientHelper.class) {
                if (mClient == null) {
                    mClient = new OkHttpClient();
                }
            }
        }
        return mClient;
    }
}
