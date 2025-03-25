package domain;
import com.xuanwu.apaas.core._enum.TenantDBType;

/**
 * @author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Description:SQLTaskReq
 * @Date 2017-02-23
 * @Version 1.0
 **/
public class SQLTaskReq {

    private Long tenantCode;

    private Long productCode;

    private Long tenantDbCode;

    private TenantDBType dataBaseType;

    private String sql;
    /**
     * 全量执行
     */
    private boolean sendFullScript = false;

    public SQLTaskReq(Long tenantCode, Long productCode, TenantDBType dataBaseType, String sql, boolean sendFullScript) {
        this.tenantCode = tenantCode;
        this.productCode = productCode;
        this.dataBaseType = dataBaseType;
        this.sql = sql;
        this.sendFullScript = sendFullScript;
    }
    public SQLTaskReq(Long tenantCode, Long tenantDbCode , String sql) {
        this.tenantCode = tenantCode;
        this.tenantDbCode = tenantDbCode;
        this.sql = sql;
        this.sendFullScript = false;
    }

    public SQLTaskReq(Long tenantCode, Long productCode, TenantDBType dataBaseType, String sql) {
        this.tenantCode = tenantCode;
        this.productCode = productCode;
        this.dataBaseType = dataBaseType;
        this.sql = sql;
        this.sendFullScript = false;
    }

    public Long getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(Long tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public Long getTenantDbCode() {
        return tenantDbCode;
    }

    public void setTenantDbCode(Long tenantDbCode) {
        this.tenantDbCode = tenantDbCode;
    }

    public TenantDBType getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(TenantDBType dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public boolean isSendFullScript() {
        return sendFullScript;
    }

    public void setSendFullScript(boolean sendFullScript) {
        this.sendFullScript = sendFullScript;
    }
}
