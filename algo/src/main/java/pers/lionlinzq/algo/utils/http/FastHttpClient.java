/*
 * Copyright (c) 2015 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package pers.lionlinzq.algo.utils.http;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Description:基于OkHttp的一个HTTP客户端
 * @Date 2017-08-09
 * @Version 1.0
 **/
public class FastHttpClient {

    public static final int READ_TIME_OUT_DEFAULT = 10;
    private static final OkHttpClient client;

    static {
        client = new OkHttpClient().newBuilder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    /**
     * 根据URL构建一个同步请求, 基于链式的调用方式
     *
     * @param url 完整的URL
     * @return 同步HTTP请求体
     */
    public static HttpSyncRequest newRequest(String url) {
        return new HttpSyncRequest(url, client);
    }

}
