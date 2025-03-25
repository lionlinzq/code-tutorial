package pers.lionlinzq.excel.service;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;

public class IdeTest {
    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://122.9.125.247:8001/bizserv/bizmodel/pageModelLogicList";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, text/plain, */*");
        headers.add("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        headers.add("Connection", "keep-alive");
        headers.add("Content-Type", "application/json");
        headers.add("Origin", "http://ide.wxchina.com:9000");
        headers.add("Referer", "http://ide.wxchina.com:9000/");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0");
        headers.add("ctcode", "7");
        headers.add("idetoken", "eyJhbGciOiJIUzI1NiJ9.eyJMb2dpblVzZXIiOnsiYXBwSWQiOiIxIiwidGVuYW50Q29kZSI6IjE2NTgyNjEiLCJwcm9kdWN0Q29kZSI6IjEwMDAwMDAwMDAwMDAwMDAwMCIsInByb2R1Y3RWZXJzaW9uQ29kZSI6IjEwMDAwMDAwMDAwMDAwMDA5MCIsImNsaWVudFR5cGVDb2RlIjpudWxsLCJ1c2VyQ29kZSI6IjE0OTQyMjI5MDU5MDU2NDc2MTYiLCJhY2NvdW50Q29kZSI6IjE0OTQyMjI5MDU5MDU2NDc2MTYiLCJ1c2VybmFtZSI6Iuael-W_l-adgyIsInRva2VuSWQiOiIxZmFmNDkzYS00YzlmLTQwYjgtYmU2ZS04ZDQwNjhlZDJlMTciLCJhcHBDb2RlcyI6bnVsbCwiYXBwQ29kZSI6bnVsbCwicGxhdFJvbGVDb2RlcyI6WyIxNjMzMDA3MzUxMjU2NzE1MjY0Il0sIm1ldGFtb2RlbHR5cGUiOjIsIm9yZ0NvZGUiOiIxNzUxODUyMDg5ODAzNDExNDU2IiwiY2VudGVyUm9sZSI6ZmFsc2V9LCJUd29GYWN0b3JBdXRoQ29kZSI6ImYxYzdhYzQ1ZTk1MGY0YjNhNTdlNTNmYjVlMGZiOTkzIiwiZXhwIjoxNzYzNzc0NTc2fQ.pvSq71l3tSyzk9_avfFIY4FDNyX_MV0WWLtYorrxVr0");
        headers.add("metamodeltype", "2");
        headers.add("pdcode", "100000000000000000");
        headers.add("pscode", "829609747450691584");
        headers.add("pvcode", "100000000000000090");
        headers.add("req_id", "23");
        headers.add("tecode", "1658261");

        // 解码 URL 编码的 headers
        headers.add("tenantname", URLDecoder.decode("9.0%E7%A7%9F%E6%88%B7%E6%95%B0%E6%8D%AE%E5%BA%93", "UTF-8"));
        headers.add("usercode", "1494222905905647616");
        headers.add("userinfoname", URLDecoder.decode("%E6%9E%97%E5%BF%97%E6%9D%83", "UTF-8"));
        headers.add("username", "linzhiquan");

        String requestBody = "{\"rows\":80,\"page\":1,\"modelcode\":\"896206671490191451\",\"keys\":\"\",\"directorytypecode\":\"1122420444306121003\",\"keywords\":\"\",\"centercode\":\"\",\"actioncategory\":\"\",\"actiontype\":\"\",\"updateopname\":\"\",\"updatetimestart\":\"\",\"updatetimeend\":\"\",\"tenantdbname\":\"\",\"jobstatus\":null}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 注意: 由于使用了 `--insecure`，需要配置 RestTemplate 忽略 SSL 证书验证，具体方法请参考 Spring 文档。

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String body = response.getBody();
        Object parse = JSON.parse(body);
        System.out.println(parse);

    }
}
