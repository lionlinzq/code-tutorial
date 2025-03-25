package utils;

import com.alibaba.fastjson.JSON;
import com.xuanwu.apaas.core.utils.http.FastHttpClient;
import com.xuanwu.apaas.core.utils.http.HttpSyncRequest;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author huangning
 * @since 2018-11-28  11:10
 **/
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static String doGet(String url) throws Exception {
        HttpSyncRequest httpSyncRequest = FastHttpClient.newRequest(url);
        Response response = httpSyncRequest.doGet();

        logger.debug("get请求，url : {} , response : {}", url, response.code());

        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new Exception("Unexpected code " + response);
        }

    }

    public static String get(String url, Map<String, String> headerMap) throws Exception {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.headers(Headers.of(headerMap));


        Request request = builder.build();
        logger.info("request : {}", JSON.toJSONString(request.toString()));

        Response response = client.newCall(builder.build()).execute();
        logger.info("response : {}", JSON.toJSONString(response));

        String result = null;
        if (response.isSuccessful()) {
            return response.body().string();
        }

        return result;
    }

    public static boolean doPost(String url, String bodyJson, Map<String, String> headers) throws Exception {
        logger.info("doPost，url : {} , bodyJson : {} , headers : {} ", url, bodyJson, headers);

        HttpSyncRequest httpSyncRequest = FastHttpClient.newRequest(url);

        for (Map.Entry<String, String> header : headers.entrySet()) {
            httpSyncRequest.addHeader(header.getKey(), header.getValue());
        }

        // 请求体
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, bodyJson);

        Response response = httpSyncRequest.doPost(requestBody);


        return response.isSuccessful();
    }

    public static Response doPost2(String url, String bodyJson, Map<String, String> headers) throws Exception {
        HttpSyncRequest httpSyncRequest = FastHttpClient.newRequest(url);

        // 请求头
        for (Map.Entry<String, String> header : headers.entrySet()) {
            httpSyncRequest.addHeader(header.getKey(), header.getValue());
        }

        // 请求体
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, bodyJson);

        return httpSyncRequest.doPost(requestBody);
    }

    public static Response post(String url, Map<String, String> headerMap, String body) throws Exception {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.headers(Headers.of(headerMap));
        if (body != null) {
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE, body);
            builder.post(requestBody);
        }

        Response response = client.newCall(builder.build()).execute();
        if (response.isSuccessful()) {
            return response;
        } else {
            throw new Exception("post请求异常！");
        }
    }

    public static Response okPost(String url, String bodyJson, Map<String, String> headers) throws IOException {
        logger.info("okPost , url : {} ,  bodyJson : {} ,  headers : {} ", url, bodyJson, headers);

        // request body
        RequestBody body = RequestBody.create(MEDIA_TYPE, bodyJson);

        // request
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url).post(body).build();

        // header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // send request
        Response response = client.newCall(requestBuilder.build()).execute();
        //HttpResult httpResult = new HttpResult();
        //httpResult.setCode(response.code());
        //httpResult.setResult(response.body().string());
        //try {
        //    response.body().close();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //    throw e;
        //}
        return response;
    }

}
