package domain;

public class TenantSetting {
    private Long tenantSettingCode;
    private Long tenantCode;
    private String content;

    public Long getTenantSettingCode() {
        return tenantSettingCode;
    }

    public void setTenantSettingCode(Long tenantSettingCode) {
        this.tenantSettingCode = tenantSettingCode;
    }

    public Long getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(Long tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
