/*
 * Copyright (c) 2015 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package pers.lionlinzq.excel.utils.http;

import com.xuanwu.smart.common.utils.JsonUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Description:一个同步的HTTP请求体封装, 简化HTTP请求过程, 基于链式调用方式
 * @Date 2017-08-09
 * @Version 1.0
 **/
public class HttpSyncRequest {

    private final OkHttpClient client;

    private String url;

    private HttpUrl.Builder urlBuilder;

    private Request.Builder reqtBuilder = new Request.Builder();

    private List<Request> requests = new ArrayList<Request>();

    public HttpSyncRequest(String url, OkHttpClient client) {
        this.url = url;
        this.client = client;
    }

    /**
     * Add query parameter
     *
     * @param name  The parameter key
     * @param value The parameter value
     * @return
     */
    public HttpSyncRequest addQueryParam(String name, String value) {
        if (urlBuilder == null) {
            urlBuilder = toUrlBuilder(url);
        }
        urlBuilder.addQueryParameter(name, value);
        return this;
    }

    public List<Request> getRequests() {
        return requests;
    }

    /**
     * Add header
     *
     * @param name  The header key
     * @param value The header value
     * @return
     */
    public HttpSyncRequest addHeader(String name, String value) {
        if (name == null || value == null) {
            return this;
        }
        reqtBuilder.addHeader(name.trim(), value.trim());
        return this;
    }

    public HttpSyncRequest addHeader(String name, Long value) {
        if (value == null) {
            return this;
        }
        return addHeader(name, String.valueOf(value));
    }

    public HttpSyncRequest token(String token) {
        return addHeader("token", token);
    }

    private Request.Builder request() {
        if (urlBuilder == null) {
            reqtBuilder.url(url);
        } else {
            reqtBuilder.url(urlBuilder.build());
        }
        return reqtBuilder;
    }

    public Response doGet() throws IOException {
        Request.Builder reqt = request();
        Request request = reqt.get().build();
        this.requests.add(request);
        return execute(request);
    }

    public Response doPost(RequestBody body) throws IOException {
        Request.Builder reqt = request();
        Request request = reqt.post(body).build();
        this.requests.add(request);
        return execute(request);
    }

    public Response doDelete() throws IOException {
        Request.Builder reqt = request();
        Request request = reqt.delete().build();
        this.requests.add(request);
        return execute(request);
    }

    public Response doPostByJson(Object obj) throws Exception {
        String json = null;
        if (obj instanceof String) {
            json = (String) obj;
        } else {
            json = JsonUtils.serialize(obj);
        }
        Request.Builder reqt = request();
        Request request = reqt.post(
                RequestBody.create(MediaType.parse("application/json"), json))
                .build();
        this.requests.add(request);
        return execute(request);
    }

    public Response doPut(RequestBody body) throws IOException {
        Request.Builder reqt = request();
        Request request = reqt.put(body).build();
        this.requests.add(request);
        return execute(request);
    }

    private Response execute(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    private HttpUrl.Builder toUrlBuilder(String url) {
        HttpUrl base = HttpUrl.parse(url);
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
        urlBuilder.scheme(base.scheme());
        urlBuilder.host(base.host());
        urlBuilder.port(base.port());
        if (base.pathSize() > 0) {
            for (String pathSegment : base.pathSegments()) {
                urlBuilder.addPathSegment(pathSegment);
            }
        }
        urlBuilder.query(base.query());
        return urlBuilder;
    }

    public OkHttpClient getClient() {
        return client;
    }
}
