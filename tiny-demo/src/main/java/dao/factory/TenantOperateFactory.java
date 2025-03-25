package dao.factory;


import _enum.TenantDBType;
import conf.DbRouterProperties;
import dao.Operate;
import dao.impl.TenantOperate;
import utils.DbRouterPropertiesWrapper;

/**
 * Created by lht on 2017/2/17.
 */
public class TenantOperateFactory {

    public static boolean localDebugMode;

    static {
        DbRouterProperties properties = DbRouterPropertiesWrapper.getDbRouterProperties();
        localDebugMode = properties.getTenantDbLocalDebugMode() != null && properties.getTenantDbLocalDebugMode();
    }


    public static Operate createReadableOperate(Long tenantCode, Long productCode) {
        if(localDebugMode){
            return new TenantOperate(tenantCode, productCode, TenantDBType.CUSTOM, "本地调试用_只读");
        }else{
            return new TenantOperate(tenantCode, productCode, TenantDBType.READ);
        }
    }


    public static Operate createWriteableOperate(Long tenantCode, Long productCode) {
        if(localDebugMode){
            return new TenantOperate(tenantCode, productCode, TenantDBType.CUSTOM, "本地调试用_读写");
        }else{
            return new TenantOperate(tenantCode, productCode, TenantDBType.READ_AND_WRITE);
        }
    }


    public static Operate createReportOperate(Long tenantCode, Long productCode) {
        if(localDebugMode){
            return new TenantOperate(tenantCode, productCode, TenantDBType.CUSTOM, "本地调试用_报表");
        }else{
            return new TenantOperate(tenantCode, productCode, TenantDBType.REPORT);
        }
    }
}
