package utils;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.*;
import java.util.Properties;

/**
 * @author huangning
 * @since 2019-04-09  10:29
 **/
public class PropertiesUtils {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    public static final String CONFIGURATION_FILENAME = "application.properties";
    public static final String BOOTSTARP_FILENAME = "bootstrap.properties";

    public static Properties loadProperties(String resource) {
        //初始化对象
        Properties properties = new Properties();

        InputStream in = null;
        try {
            //从配置文件中读取配置信息,先读外部的配置文件，如果没有就读classpath中的
            String absolutePath = new File("").getAbsolutePath();
            String filePath = absolutePath + File.separator + resource;

            // 资源 ---》 文件输入流
            File file = new File(filePath);
            if (!file.exists()) {
                in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            } else {
                in = new FileInputStream(file);
            }

            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return properties;
    }

    /**
     * 优先load外部的资源文件，load不到再load jar包内的资源文件
     *
     * @param resource
     * @return
     */
    public static Properties loadProperties2(String resource) {
        Properties props = new Properties();

        // 优先load外部的资源文件，load不到再load jar包内的资源文件
        InputStream is = null;
        try {
            logger.debug("Loading properties from external resource: " + resource);
            String contextPath = System.getProperty("user.dir");
            String externalResource = contextPath + File.separator + resource;
            is = new BufferedInputStream(new FileInputStream(externalResource));
        } catch (FileNotFoundException ex) {
            logger.error("Can not find the external application properties resource, load nested application properties");
        }

        try {
            if (null == is) {
                // 没有外部的资源，则读取内部的资源
                logger.debug("Loading properties from nested resource: " + resource);
                ResourceLoader resourceLoader = new DefaultResourceLoader();
                Resource loadResource = resourceLoader.getResource(resource);
                is = loadResource.getInputStream();
            }
            props.load(is);
        } catch (Exception ex) {
            logger.error("Could not load properties from path:" + resource + ", errorMsg: " + ex.getMessage());
        } finally {
            IOUtils.closeQuietly(is);
        }

        return props;
    }


    public static Properties loadProperties(String dataId, String group) {
        Properties localProperties = loadProperties(CONFIGURATION_FILENAME);

        String endpoint = localProperties.getProperty("nacos.endpoint");
        String accessKey = localProperties.getProperty("nacos.access-key");
        String secretKey = localProperties.getProperty("nacos.secret-key");
        String namespace = localProperties.getProperty("nacos.namespace");
        String serverAddr = localProperties.getProperty("nacos.server-addr");
        String username = localProperties.getProperty("nacos.username");
        String password = localProperties.getProperty("nacos.password");

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ENDPOINT, StringUtils.isBlank(endpoint) ? "" : endpoint);
        properties.put(PropertyKeyConst.ACCESS_KEY, StringUtils.isBlank(accessKey) ? "" : accessKey);
        properties.put(PropertyKeyConst.SECRET_KEY, StringUtils.isBlank(secretKey) ? "" : secretKey);
        properties.put(PropertyKeyConst.SERVER_ADDR, StringUtils.isBlank(serverAddr) ? "" : serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, StringUtils.isBlank(namespace) ? "" : namespace);
        properties.put(PropertyKeyConst.USERNAME, StringUtils.isBlank(username) ? "" : username);
        properties.put(PropertyKeyConst.PASSWORD, StringUtils.isBlank(password) ? "" : password);

        String content = null;
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            content = configService.getConfig(dataId, group, 5000);
        } catch (Exception e) {
            logger.error("获取配置信息异常！", e);
        }

        if (!StringUtil.hasLength(content)) {
            return null;
        }
        return parse(content);
    }

    public static Properties parse(String content) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(content));
            return properties;
        } catch (IOException e) {
            logger.error("转换异常！", e);
        }
        return null;
    }

    public static String getScanLocation(String type) {
        Properties properties = loadProperties(BOOTSTARP_FILENAME);

        String scanLocation = null;
        if (type.equalsIgnoreCase("tenant")) {
            scanLocation = properties.getProperty("xw.tenant.scanLocation");
        } else if (type.equalsIgnoreCase("metadata")) {
            scanLocation = properties.getProperty("xw.metadata.scanLocation");
        } else if (type.equalsIgnoreCase("platform")) {
            scanLocation = properties.getProperty("xw.platform.scanLocation");
        }

        return scanLocation;
    }

    public static Resource getConfigResources() throws IOException {
        String configLocation = "classpath:mybatis-config.xml";
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();


        Resource[] resources = resolver.getResources(configLocation);
        if (resources == null || resources.length == 0) {
            return null;
        }
        return resources[0];
    }

    public static Properties loadAll(Class clazz) {
        logger.info("加载所有配置信息，开始 。。。。。。{}", clazz.getName());

        boolean flag = clazz.isAnnotationPresent(NacosPropertySources.class);
        if (!flag) {
            logger.error("是否存在注解NacosPropertySources ，flag : {}", flag);
            return null;
        }


        NacosPropertySources nacosPropertySources = (NacosPropertySources) clazz.getAnnotation(NacosPropertySources.class);
        NacosPropertySource[] sources = nacosPropertySources.value();

        Properties all = new Properties();
        for (NacosPropertySource nacosPropertySource : sources) {
            String dataId = nacosPropertySource.dataId();
            String groupId = nacosPropertySource.groupId();
            logger.info("dataId : {} , groupId : {}", dataId, groupId);

            Properties properties = loadProperties(dataId, groupId);
            all.putAll(properties);
        }

        logger.info("加载所有配置信息，结束 。。。。。。{}", clazz.getName());
        return all;
    }


    public static Properties loadImpExpProperties() {

        return null;
    }
}
