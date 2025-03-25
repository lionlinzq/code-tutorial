package dao.factory;


import dao.Operate;
import dao.impl.MetadataOperate;
import utils.PoolDataSourceConfig;

/**
 * @author rongdi
 * @description 数据库的操作类工厂类
 * @date 2015年11月5日
 */
public class MetadataOperateFactory {

    public static Operate createReadableOperate() {
        return new MetadataOperate(PoolDataSourceConfig.PoolType.READ);
    }

    public static Operate createWriteableOperate() {
        return new MetadataOperate(PoolDataSourceConfig.PoolType.WRITE);
    }

    public static Operate createReadAndWriteableOperate() {
        return new MetadataOperate(PoolDataSourceConfig.PoolType.READ_AND_WRITE);
    }
}
