package service.impl;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.xuanwu.apaas.core.dao.Operate;
import com.xuanwu.apaas.core.dao.factory.PlatformOperateFactory;
import com.xuanwu.apaas.core.domain.TenantSetting;
import com.xuanwu.apaas.core.multicache.CacheType;
import com.xuanwu.apaas.core.multicache.MultiCacheService;
import com.xuanwu.apaas.core.service.TenantSettingService;
import com.xuanwu.apaas.core.utils.DesUtil;
import com.xuanwu.apaas.core.utils.JsonUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 应用配置的实现，带多级缓存
 */
@Component
public class TenantSettingServiceImpl implements TenantSettingService {
    private final static Logger logger = LoggerFactory.getLogger(TenantSettingServiceImpl.class);
    private final static String mapperNameSpaceAccount = "com.xuanwu.apaas.core.domain.TenantSetting.";

    private final static byte[] SECURITY_KEY = {0x36, 0x31, 0x33, 0x30, 0x32, 0x32, 0x32, 0x32, 0x32};

    @Autowired(required = false)
    private MultiCacheService multiCacheService;

    @Override
    public TenantSetting getTenantSettingByTenantCode(Long tenantCode) {
        String name = "tenantsettings";
        String key = String.valueOf(tenantCode);

        if(multiCacheService != null){
          Object result = multiCacheService.get(TenantSetting.class, name, key, CacheType.BOTH);
            if(result != null) {
                return (TenantSetting) result;
            }
        }

        // 根据租户code取应用配置
        Operate operate = PlatformOperateFactory.createReadableOperate();
        try (SqlSession session = operate.openSession()) {
            TenantSetting tenantSetting = session.selectOne(mapperNameSpaceAccount + "selectTenantSetting", tenantCode);
            if(tenantSetting != null) {
                if(multiCacheService != null){
                    multiCacheService.put(tenantSetting, name, key, CacheType.BOTH);
                }
            }
            return tenantSetting;
        }
    }

    @Override
    public <T> T getSettingItem(Long tenantCode, String itemKey, T defaultValue, Class<T> clazz) {
        // 如果租户code为空，返回默认值
        if(tenantCode == null) {
            logger.debug("tenantcode未传递，无租户配置，使用默认值:{}:{}", itemKey, defaultValue);
            return defaultValue;
        }
        try {
            TenantSetting setting = this.getTenantSettingByTenantCode(tenantCode);
            if (setting != null) {
                String content = setting.getContent();

                String econfig = String.valueOf(JsonUtil.jsonToMap(content).get("econfig_serv"));
                // 解密
                String jsonConfig = DesUtil.decrypt(econfig, new String(SECURITY_KEY));
                T ret = JsonPath.parse(jsonConfig).read(itemKey, clazz);
                if(ret != null && !"".equals(ret)) {
                    return JsonPath.parse(jsonConfig).read(itemKey, clazz);
                } else {
                    logger.debug("无租户配置，使用默认值:{}:{}", itemKey, defaultValue);
                    return defaultValue;
                }
            } else {
                logger.debug("无租户配置，使用默认值:{}:{}", itemKey, defaultValue);
                return defaultValue;
            }
        } catch (PathNotFoundException ex) {
            return defaultValue;
        } catch (Exception ex) {
            throw new RuntimeException("解析租户配置信息出错");
        }
    }
}
