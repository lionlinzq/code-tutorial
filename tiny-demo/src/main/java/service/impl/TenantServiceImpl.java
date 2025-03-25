package service.impl;

import com.xuanwu.apaas.core._enum.TenantDBType;
import com.xuanwu.apaas.core.domain.SQLTaskReq;
import com.xuanwu.apaas.core.engine.InitDBTask;
import com.xuanwu.apaas.core.service.TenantService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Description:TenantServiceImpl
 * @Date 2017-02-22
 * @Version 1.0
 **/
@Service("sdkTenantService")
public class TenantServiceImpl implements TenantService {

    private static final Logger logger = LoggerFactory.getLogger(TenantService.class);

    @Autowired
    private InitDBTask dbTask;

    @Override
    public boolean initDB(Long tenantCode, Long productCode, String sql) throws Exception {
        return initDB(tenantCode, productCode, TenantDBType.READ_AND_WRITE, sql, false);
    }

    @Override
    public boolean initDB(Long tenantCode, Long productCode, String sql, boolean sendFullScript) throws Exception {
        return initDB(tenantCode, productCode, TenantDBType.READ_AND_WRITE, sql, sendFullScript);
    }

    @Override
    public boolean initDB(Long tenantCode, Long productCode, TenantDBType dataBaseType, String sql) throws Exception {
        return initDB(tenantCode, productCode, dataBaseType, sql, false);
    }

    @Override
    public boolean initDB(Long tenantCode, Long productCode, TenantDBType dataBaseType, String sql, boolean sendFullScript) throws Exception {
        if (tenantCode == null) {
            throw new Exception("tenantCode is empty!");
        }
        if (productCode == null) {
            throw new Exception("productCode is empty!");
        }
        if (dataBaseType == null) {
            throw new Exception("dataBaseType is empty!");
        }
        if (StringUtils.isBlank(sql)) {
            throw new Exception("sql is empty!");
        }
        //暂不加入队列处理，直接执行
        return dbTask.executeSql(new SQLTaskReq(tenantCode, productCode, dataBaseType, sql, sendFullScript));
    }

    @Override
    public boolean initDB(String sql, Long tenantCode, Long tenantdbCode) throws Exception {
        if (tenantCode == null) {
            throw new Exception("tenantCode is empty!");
        }
        if (tenantdbCode == null) {
            throw new Exception("productCode is empty!");
        }

        if (StringUtils.isBlank(sql)) {
            throw new Exception("sql is empty!");
        }
        return dbTask.executeSql( new SQLTaskReq(tenantCode,tenantdbCode,sql));
    }


}
