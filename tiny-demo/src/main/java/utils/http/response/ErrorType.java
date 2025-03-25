package utils.http.response;

public enum ErrorType implements IErrorType {

    /**
     * 未授权
     */
    UNAUTHORIZED (40101, "UnAuthorized", "未授权"),

    /**
     * 授权数据状态非启用
     */
    AUTH_DATA_STATUS(40102, "auth data status enable","状态非启用状态"),

    /**
     * 参数为空
     */
    PARAM_EMPTY (40600, "Param is Empty","参数为空"),

    /**
     * 参数不正确
     */
    PARAM_ERROR (40601, "param error", "参数不正确"),

    /**
     * 服务端未捕捉出错
     */
    SERVICE_UNKNOWN(50099, "Server Unknown Error","服务未知错误"),

    /**
     * 数据链接出错
     */
    DATA_LINK_ERROR (50100, "Data Access Exception","数据库连接出错"),

    /**
     * 数据存取出错
     */
    DATA_ACCESS_ERROR (50101, "Data Access Exception","数据库执行出错"),

    /**
     * 数据未发现
     */
    DATA_ACCESS_NOT_FOUND (50102, "Data Access not Found","未找到数据记录"),

    /**
     * 数据库主键冲突
     */
    DATA_ACCESS_PRIKEY (50103, "Data PriKey error", "数据库主键冲突"),

    /**
     * 数据删除存在关联数据
     */
    DATA_DELETE_EXIST_RELATION(50104, "Data delete exist relation datas", "数据删除存在关联数据"),

    /**
     * 业务数据提交出错
     */
    BIZ_SUBMIT_ERROR(60200, "biz submit error","业务数据提交错误"),

    /**
     * 业务数据字段唯一性检查
     */
    BIZ_DATA_FIELD_UNIQUE(60201, "data field is unique","字段数据唯一性错误");

    private final int errorType;

    private final String reason;

    private final String key;

    private final ErrorTypeFamily errorTypeFamily;

    ErrorType(final int errorType, final String reason) {
        this.errorType = errorType;
        this.reason = reason;
        this.key = reason;
        this.errorTypeFamily = ErrorTypeFamily.familyOf(errorType);
    }

    ErrorType(final int errorType, final String key, final String reason) {
        this.errorType = errorType;
        this.key = key;
        this.reason = reason;
        this.errorTypeFamily = ErrorTypeFamily.familyOf(errorType);
    }

    ErrorType(final int errorType, final String key, final String reason, final ErrorTypeFamily errorTypeFamily) {
        this.errorType = errorType;
        this.reason = reason;
        this.key = key;
        this.errorTypeFamily = errorTypeFamily;
    }

    @Override
    public int getErrorType() {
        return errorType;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public ErrorTypeFamily getErrorTypeFamily() {
        return errorTypeFamily;
    }

    @Override
    public String getKey() {
        return key;
    }
}
