package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangning
 * @since 2019-04-11  16:34
 **/
public class ResourceUtils {
    private final static Logger logger = LoggerFactory.getLogger(ResourceUtils.class);

    /**
     * 通过扫描路径加载资源
     *
     * @param scanLocation
     * @return
     * @throws IOException
     */
    public static Resource[] getResources(String scanLocation) throws IOException {
        logger.debug("获取mybatis资源:{}", scanLocation);
        String[] strs = scanLocation.split(Delimiters.SICOLON);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<Resource[]> rss = new ArrayList<Resource[]>();
        Resource[] resources = null;
        for (String str : strs) {
            try {
                resources = resolver.getResources(str);
            } catch (Exception e) {
                logger.error("加载mybatis资源失败{}", str, e);
                continue;
            }
            if (resources != null && resources.length > 0)
                rss.add(resources);
        }
        return ArrayUtils.addAll(new Resource[]{},
                rss.toArray(new Resource[rss.size()][]));
    }
}
