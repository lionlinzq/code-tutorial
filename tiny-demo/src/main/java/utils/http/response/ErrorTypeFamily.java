package utils.http.response;

/**
 * 错误类型分类定义
 */
public enum ErrorTypeFamily {

    /**
     * 对于说有需要再细分属于不同分类的，可以在此定义不同的类别
     */

    /**
     * {@code 4xxxx} ErrorType codes.
     */
    CLIENT_ERROR,
    /**
     * {@code 5xxxx} ErrorType codes.
     */
    SERVER_ERROR,
    /**
     * {@code 6xxxx} ErrorType codes.
     */
    BIZ_ERROR,
    /**
     * 轻表单应用特定的错误 {@code 67000~67500} ErrorType codes.
     */
    LITHE_SERVICE_ERROR,
    /**
     * Other, unrecognized ErrorType codes.
     */
    OTHER;

    public static ErrorTypeFamily familyOf(final int errorType) {
        switch (errorType / 10000) {
            case 4:
                return ErrorTypeFamily.CLIENT_ERROR;
            case 5:
                return ErrorTypeFamily.SERVER_ERROR;
            case 6:
                return ErrorTypeFamily.BIZ_ERROR;
            default:
                return ErrorTypeFamily.OTHER;
        }
    }
}
