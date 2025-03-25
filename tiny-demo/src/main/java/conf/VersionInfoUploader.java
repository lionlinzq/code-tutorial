package conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 版本信息上传
 *
 * @since: 2023/6/19
 * @author: hongdahao
 */
@Component
public class VersionInfoUploader implements ApplicationListener<ApplicationReadyEvent> {

    private final static Logger logger = LoggerFactory.getLogger(VersionInfoUploader.class);

    @Value("${serv.bizengine.url}")
    private String url;
    @Value("${server.port:8080}")
    private String port;
    @Value("${serv.autoversion.enabled:false}")
    private Boolean enabled;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(enabled != null && enabled){
            new Thread(this::upload).start();
        }
    }


    private void upload() {
        try {
            Thread.currentThread().setName("VersionInfoUploaderThread");
            //等待业务引擎启动完成
            Thread.sleep(5000);
            //读取版本文件
            String versionFile = new String(FileCopyUtils.copyToByteArray(
                    VersionInfoUploader.class.getClassLoader().getResourceAsStream("apaasversion.properties")
            ), StandardCharsets.UTF_8);
            Map<String, Object> requestBody = new HashMap<>();
            for (String kv : versionFile.split("\\s")) {
                if(StringUtils.isEmpty(kv)){
                    continue;
                }
                requestBody.put(kv.split("=")[0], kv.split("=")[1]);
            }
            requestBody.put("ip", getIp());
            requestBody.put("port", port);
            //发送请求
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("token", "#VersionInfoUploaderToken#");
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            RestTemplate restTemplate = new RestTemplate();
            long wait = 3 * 1000;
            while (true) {
                try {
                    ResponseEntity<String> response = restTemplate.postForEntity(url + "/biz/version", requestEntity, String.class);
                    if (response.getStatusCode().is2xxSuccessful()) {
                        String responseBody = response.getBody();
                        logger.info("版本信息上传响应结果：" + responseBody);
                        break;
                    }
                } catch (Exception e) {
                    logger.warn("版本信息上传请求失败，错误信息：" + e);
                    Thread.sleep(wait);
                    wait *= 2;
                    if (wait > 3600 * 1000) {
                        wait = 3600 * 1000;
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("版本信息上传失败：" + e.getMessage(), e);
        }
    }


    private String getIp() throws SocketException {
        // 获取所有网络接口
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            // 排除回环地址和虚拟接口
            if (ni.isLoopback() || ni.isVirtual()) {
                continue;
            }
            // 获取该接口上的所有 IP 地址
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                // 排除 IPv6 地址和回环地址
                if (addr instanceof Inet6Address || addr.isLoopbackAddress()) {
                    continue;
                }
                // 输出 IPv4 地址
                logger.info("Local IP address: " + addr.getHostAddress());
                return addr.getHostAddress();
            }
        }
        return null;
    }

}