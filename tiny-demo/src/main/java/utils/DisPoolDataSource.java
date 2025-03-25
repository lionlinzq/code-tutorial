package utils;


import com.xuanwu.apaas.core._enum.TenantDBType;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 没有池概念的假连接池
 *
 * @author liangzhi
 */
public class DisPoolDataSource extends AbstractDisPoolDataSource {
    private Long tenantCode;
    private TenantDBType tenantDBType;
    private Long productCode;
    private Long tenantDbCode;
    private String tenantDbName;

    public DisPoolDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode) {
        this.tenantCode = tenantCode;
        this.tenantDBType = tenantDBType;
        this.productCode = productCode;
    }

    public DisPoolDataSource(Long tenantCode, Long tenantDbCode){
        this.tenantCode = tenantCode;
        this.tenantDbCode = tenantDbCode;
    }

    public DisPoolDataSource(Long tenantCode, TenantDBType tenantDBType, Long productCode, String tenantDbName) {
        this.tenantCode = tenantCode;
        this.tenantDBType = tenantDBType;
        this.productCode = productCode;
        this.tenantDbName = tenantDbName;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DataSourceRouterUtil.getConnection(tenantCode, tenantDBType, productCode,tenantDbCode);
    }


    public void close() {
        // 关闭连接池
    }

}

