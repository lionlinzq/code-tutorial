package service;

import com.xuanwu.apaas.core.domain.TenantSetting;

public interface TenantSettingService {
    TenantSetting getTenantSettingByTenantCode(Long tenantCode);
    <T> T getSettingItem(Long tenantCode, String itemKey, T defaultValue, Class<T> clazz);
}
