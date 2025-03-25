package _enum;

/**
 * Created by liangzhi on 2017/2/8.
 * 数据库的类型->只读,读和写，报表
 */
public enum TenantDBType {
    READ(1), READ_AND_WRITE(2), REPORT(3), CUSTOM(4);

    private final int DBType;

    TenantDBType(int DBType) {
        this.DBType = DBType;
    }

    public int getDBType() {
        return DBType;
    }

    public static TenantDBType getDBType(String typeName) {
        if (READ.name().equals(typeName)) {
            return READ;
        } else if (READ_AND_WRITE.name().equals(typeName)) {
            return READ_AND_WRITE;
        } else if (REPORT.name().equals(typeName)) {
            return REPORT;
        } else if (CUSTOM.name().equals(typeName)) {
            return CUSTOM;
        }
        throw new UnsupportedOperationException("未知类型名称：" + typeName);
    }

    public static TenantDBType getDBType(int typeOrder) {
        if (READ.getDBType() == typeOrder) {
            return READ;
        } else if (READ_AND_WRITE.getDBType() == typeOrder) {
            return READ_AND_WRITE;
        } else if (REPORT.getDBType() == typeOrder) {
            return REPORT;
        } else if (CUSTOM.getDBType() == typeOrder) {
            return CUSTOM;
        }
        throw new UnsupportedOperationException("未知类型：" + typeOrder);
    }

}

